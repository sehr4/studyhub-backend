package com.studyhub.controller;

import com.studyhub.dto.submission.SubmissionFileDTO;
import com.studyhub.dto.submission.SubmissionDTO;
import com.studyhub.dto.submission.SubmissionUpdateDTO;
import com.studyhub.service.SubmissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

// REST controller for handling submission-related API requests
@RestController
@RequestMapping("/courses/{courseId}/submissions")
@Tag(name = "Submission Controller", description = "APIs for managing student submissions")
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping(value = "/{assignmentId}/student/{studentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Submit an assignment", description = "Allows a student to submit an assignment with a file")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Assignment submitted successfully",
                    content = @Content(schema = @Schema(implementation = SubmissionDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input or deadline passed", content = @Content),
            @ApiResponse(responseCode = "404", description = "Assignment or student not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<SubmissionDTO> submitAssignment(
            @PathVariable Long courseId,
            @PathVariable Long assignmentId,
            @PathVariable Long studentId,
            @RequestParam("file") MultipartFile file) {
        // Log
//        System.out.println("Received file: " + (file != null ? file.getOriginalFilename() : "null"));
//        System.out.println("File size: " + (file != null ? file.getSize() : "null"));

        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        SubmissionFileDTO submissionFileDTO = new SubmissionFileDTO();
        submissionFileDTO.setFile(file);
        SubmissionDTO resultSubmissionDTO = submissionService.submitAssignment(assignmentId, studentId, submissionFileDTO);
        return ResponseEntity.ok(resultSubmissionDTO);
    }

    @GetMapping("/{assignmentId}/student/{studentId}/download")
    @Operation(summary = "Download a submitted assignment", description = "Downloads a student's submitted assignment file")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "File downloaded successfully",
                    content = @Content(mediaType = "application/octet-stream")),
            @ApiResponse(responseCode = "404", description = "Assignment or student not found, or no file submitted", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<byte[]> downloadSubmittedAssignment(
            @PathVariable Long courseId,
            @PathVariable Long assignmentId,
            @PathVariable Long studentId) {
        SubmissionService.FileContentResponse fileResponse = submissionService.getSubmittedFile(assignmentId, studentId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileResponse.getFileName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileResponse.getContent());
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get submissions by student", description = "Retrieves submissions for a specific student in a course, including feedback and grade")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Submissions found",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = SubmissionDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "No submissions found for student in course", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<List<SubmissionDTO>> getSubmissionsByStudent(
            @PathVariable Long courseId,
            @PathVariable Long studentId) {
        List<SubmissionDTO> submissions = submissionService.getSubmissionsByStudent(courseId, studentId);
        return ResponseEntity.ok(submissions);
    }

    @PutMapping("/{assignmentId}/student/{studentId}")
    @Operation(summary = "Update a submission (by instructors)", description = "Updates a student's submission with feedback and grade")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Submission updated successfully",
                    content = @Content(schema = @Schema(implementation = SubmissionDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Submission, assignment, or student not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<SubmissionDTO> updateSubmission(
            @PathVariable Long courseId,
            @PathVariable Long assignmentId,
            @PathVariable Long studentId,
            @Valid @RequestBody SubmissionUpdateDTO submissionUpdateDTO) {
        submissionUpdateDTO.setId(null);
        SubmissionDTO updatedSubmission = submissionService.updateSubmission(assignmentId, studentId, submissionUpdateDTO);
        return ResponseEntity.ok(updatedSubmission);
    }
}