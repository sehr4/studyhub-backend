package com.studyhub.controller;


import com.studyhub.dto.CourseCreateDTO;
import com.studyhub.dto.CourseDTO;
import com.studyhub.dto.EnrollmentDTO;
import com.studyhub.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
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
            @ApiResponse(responseCode = "200", description = "Course created successfully", content = @Content(schema = @Schema(implementation = CourseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input or duplicate code/title", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseCreateDTO courseCreateDTO) {
        CourseDTO courseDTO = courseService.createCourse(courseCreateDTO);
        return ResponseEntity.ok(courseDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get course by ID", description = "Retrieves a course by its unique ID")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "Course found", content = @Content(schema = @Schema(implementation = CourseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Course not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        CourseDTO courseDTO = courseService.getCourseById(id);
        return ResponseEntity.ok(courseDTO);
    }

    @PostMapping("/enroll")
    @Operation(summary = "Enroll a student to a course", description = "Enrolls a student to a specified course")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "Enrollment successful"),
            @ApiResponse(responseCode = "400", description = "Invalid student role or input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Course or student not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> enrollStudent(@Valid @RequestBody EnrollmentDTO enrollmentDTO) {
        courseService.enrollStudent(enrollmentDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/department/{department}")
    @Operation(summary = "Get courses by department", description = "Retrieves all courses in a specified department")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "Courses found", content = @Content(schema = @Schema(implementation = CourseDTO.class))),
            @ApiResponse(responseCode = "404", description = "No courses found in department", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<CourseDTO>> getCoursesByDepartment(@PathVariable String department) {
        List<CourseDTO> courses = courseService.getCoursesByDepartment(department);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get courses by student", description = "Retrieves all courses a student is enrolled in")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "Courses found", content = @Content(schema = @Schema(implementation = CourseDTO.class))),
            @ApiResponse(responseCode = "400", description = "User is not a student", content = @Content),
            @ApiResponse(responseCode = "404", description = "Student or courses not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<CourseDTO>> getCoursesByStudent(@PathVariable Long studentId) {
        List<CourseDTO> courses = courseService.getCoursesByStudent(studentId);
        return ResponseEntity.ok(courses);
    }
}
