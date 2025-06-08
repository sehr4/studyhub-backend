package com.studyhub.controller;

import com.studyhub.dto.assignment.AssignmentCreateDTO;
import com.studyhub.dto.assignment.AssignmentDTO;
import com.studyhub.dto.assignment.AssignmentUpdateDTO;
import com.studyhub.service.AssignmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{assignmentId}")
    @Operation(summary = "Get assignment by ID", description = "Retrieves an assignment by its ID for a specified course")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Assignment found",
                    content = @Content(schema = @Schema(implementation = AssignmentDTO.class))),
            @ApiResponse(responseCode = "404", description = "Assignment or course not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<AssignmentDTO> getAssignmentById(
            @PathVariable Long courseId,
            @PathVariable Long assignmentId) {
        AssignmentDTO assignmentDTO = assignmentService.getAssignmentById(courseId, assignmentId);
        return ResponseEntity.ok(assignmentDTO);
    }

    @PutMapping("/{assignmentId}")
    @Operation(summary = "Update an assignment", description = "Updates an existing assignment")
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
