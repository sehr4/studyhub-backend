package com.studyhub.controller;

import com.studyhub.dto.AssignmentCreateDTO;
import com.studyhub.dto.AssignmentDTO;
import com.studyhub.dto.AssignmentSubmissionDTO;
import com.studyhub.dto.AssignmentUpdateDTO;
import com.studyhub.service.AssignmentService;
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

// REST controller for handling assignment-related API requests
@RestController
@RequestMapping("/courses/{courseId}/assignments")
@Tag(name = "Assignment Controller", description = "APIs for managing course assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping
    @Operation(summary = "Create a new assignment", description = "Creates an assignment for a specified course")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Assignment created successfully",
                    content = @Content(schema = @Schema(implementation = AssignmentDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Course not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<AssignmentDTO> createAssignment(
            @PathVariable Long courseId,
            @Valid @RequestBody AssignmentCreateDTO assignmentCreateDTO) {
        AssignmentDTO assignmentDTO = assignmentService.createAssignment(courseId, assignmentCreateDTO);
        return ResponseEntity.ok(assignmentDTO);
    }

    @GetMapping
    @Operation(summary = "Get assignments by course", description = "Retrieves all assignments for a specified course")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Assignments found",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AssignmentDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No assignments found for course", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<List<AssignmentDTO>> getAssignmentsByCourse(@PathVariable Long courseId) {
        List<AssignmentDTO> assignments = assignmentService.getAssignmentsByCourse(courseId);
        return ResponseEntity.ok(assignments);
    }

    @PostMapping(value = "/{assignmentId}/submit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Submit an assignment", description = "Allows a student to submit an assignment with a file")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Assignment submitted successfully",
                    content = @Content(schema = @Schema(implementation = AssignmentDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input or deadline passed", content = @Content),
            @ApiResponse(responseCode = "404", description = "Assignment or course not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<AssignmentDTO> submitAssignment(
            @PathVariable Long courseId,
            @PathVariable Long assignmentId,
            @RequestParam Long studentId,
            @RequestParam("file") MultipartFile file) {
        // Log the file details for debugging
        System.out.println("Received file: " + (file != null ? file.getOriginalFilename() : "null"));
        System.out.println("File size: " + (file != null ? file.getSize() : "null"));

        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        AssignmentSubmissionDTO submissionDTO = new AssignmentSubmissionDTO();
        submissionDTO.setFile(file);
        AssignmentDTO assignmentDTO = assignmentService.submitAssignment(courseId, assignmentId, studentId, submissionDTO);
        return ResponseEntity.ok(assignmentDTO);
    }

    @GetMapping("/{assignmentId}/download")
    @Operation(summary = "Download a submitted assignment", description = "Downloads a student's submitted assignment file")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "File downloaded successfully",
                    content = @Content(mediaType = "application/octet-stream")),
            @ApiResponse(responseCode = "404", description = "Assignment or course not found, or no file submitted", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<byte[]> downloadSubmittedAssignment(
            @PathVariable Long courseId,
            @PathVariable Long assignmentId) {
        AssignmentService.FileContentResponse fileResponse = assignmentService.getSubmittedFile(courseId, assignmentId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileResponse.getFileName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileResponse.getContent());
    }

    @PutMapping("/{assignmentId}")
    @Operation(summary = "Update an assignment", description = "Updates an existing assignment or provides feedback")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Assignment updated successfully",
                    content = @Content(schema = @Schema(implementation = AssignmentDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Assignment or course not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<AssignmentDTO> updateAssignment(
            @PathVariable Long courseId,
            @PathVariable Long assignmentId,
            @Valid @RequestBody AssignmentUpdateDTO assignmentUpdateDTO) {
        assignmentUpdateDTO.setId(assignmentId);
        AssignmentDTO updatedAssignment = assignmentService.updateAssignment(courseId, assignmentId, assignmentUpdateDTO);
        return ResponseEntity.ok(updatedAssignment);
    }

    @DeleteMapping("/{assignmentId}")
    @Operation(summary = "Delete an assignment", description = "Deletes an assignment in a specific course")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Assignment deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Assignment or course not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<Void> deleteAssignment(
            @PathVariable Long courseId,
            @PathVariable Long assignmentId) {
        assignmentService.deleteAssignment(courseId, assignmentId);
        return ResponseEntity.noContent().build();
    }
}
