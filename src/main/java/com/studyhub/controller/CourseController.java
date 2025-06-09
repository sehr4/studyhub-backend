package com.studyhub.controller;

import com.studyhub.dto.course.*;
import com.studyhub.service.CourseService;
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

// REST controller for handling course-related API requests
@RestController
@RequestMapping("/courses")
@Tag(name = "Course Controller", description = "APIs for managing course-related operations")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    @Operation(summary = "Create a new course", description = "Creates a course with the provided details")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "Course created successfully",
                    content = @Content(schema = @Schema(implementation = CourseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input or duplicate code/title", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseCreateDTO courseCreateDTO) {
        CourseDTO courseDTO = courseService.createCourse(courseCreateDTO);
        return ResponseEntity.ok(courseDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get course by ID", description = "Retrieves a course by its unique ID")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "Course found",
                    content = @Content(schema = @Schema(implementation = CourseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Course not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        CourseDTO courseDTO = courseService.getCourseById(id);
        return ResponseEntity.ok(courseDTO);
    }

    @PostMapping("/enroll")
    @Operation(summary = "Enroll a student in a course", description = "Enrolls a student in a specified course")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "Enrollment successful"),
            @ApiResponse(responseCode = "400", description = "Invalid student role or input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Course or student not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<Void> enrollStudent(@Valid @RequestBody CourseEnrollmentDTO courseEnrollmentDTO) {
        courseService.enrollStudent(courseEnrollmentDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/department/{department}")
    @Operation(summary = "Get courses by department", description = "Retrieves all courses in a specified department")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "Courses found",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CourseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No courses found in department", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<List<CourseDTO>> getCoursesByDepartment(@PathVariable String department) {
        List<CourseDTO> courses = courseService.getCoursesByDepartment(department);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/department/{department}/summary")
    @Operation(summary = "Get summarized courses by department", description = "Retrieves a summarized version of all courses in a specified department")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "Courses found",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CourseSummaryDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No courses found in department", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<List<CourseSummaryDTO>> getSummarizedCoursesByDepartment(@PathVariable String department) {
        List<CourseSummaryDTO> courses = courseService.getSummarizedCoursesByDepartment(department);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get courses by student", description = "Retrieves all courses a student is enrolled in")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "Courses found",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CourseDTO.class)))),
            @ApiResponse(responseCode = "400", description = "User is not a student", content = @Content),
            @ApiResponse(responseCode = "404", description = "Student or courses not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<List<CourseDTO>> getCoursesByStudent(@PathVariable Long studentId) {
        List<CourseDTO> courses = courseService.getCoursesByStudent(studentId);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/student/{studentId}/summary")
    @Operation(summary = "Get summarized courses by student", description = "Retrieves a summarized version of all courses a student is enrolled in")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "Courses found",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CourseSummaryDTO.class)))),
            @ApiResponse(responseCode = "400", description = "User is not a student", content = @Content),
            @ApiResponse(responseCode = "404", description = "Student or courses not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<List<CourseSummaryDTO>> getSummarizedCoursesByStudent(@PathVariable Long studentId) {
        List<CourseSummaryDTO> courses = courseService.getSummarizedCoursesByStudent(studentId);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/instructor/{instructorId}/summary")
    @Operation(summary = "Get summarized courses by instructor", description = "Retrieves a summarized version of all courses an instructor has")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "Courses found",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CourseSummaryDTO.class)))),
            @ApiResponse(responseCode = "400", description = "User is not an instructor", content = @Content),
            @ApiResponse(responseCode = "404", description = "Instructor or courses not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<List<CourseSummaryDTO>> getSummarizedCoursesByInstructor(@PathVariable Long instructorId) {
        List<CourseSummaryDTO> courses = courseService.getSummarizedCoursesByInstructor(instructorId);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/user/{userId}/active")
    @Operation(summary = "Get active courses by user", description = "Retrieves active courses for a specific user (student or instructor)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Courses found",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CourseDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid user role or input", content = @Content),
            @ApiResponse(responseCode = "404", description = "User or courses not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<List<CourseDTO>> getActiveCoursesByUser(@PathVariable Long userId) {
        List<CourseDTO> courses = courseService.getActiveCoursesByUser(userId);
        return ResponseEntity.ok(courses);
    }

    // Update a course by ID
    @PutMapping("/{id}")
    @Operation(summary = "Update a course", description = "Updates an existing course by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Course updated successfully",
                    content = @Content(schema = @Schema(implementation = CourseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Course not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseUpdateDTO courseUpdateDTO) {
        courseUpdateDTO.setId(id);
        CourseDTO updatedCourse = courseService.updateCourse(courseUpdateDTO);
        return ResponseEntity.ok(updatedCourse);
    }

    // Delete a course by its ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a course", description = "Deletes a course by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Course deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
