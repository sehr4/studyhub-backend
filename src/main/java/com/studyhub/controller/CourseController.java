package com.studyhub.controller;


import com.studyhub.dto.CourseCreateDTO;
import com.studyhub.dto.CourseDTO;
import com.studyhub.dto.EnrollmentDTO;
import com.studyhub.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// REST controller for handling course-related API requests
@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseCreateDTO courseCreateDTO) {
        CourseDTO courseDTO = courseService.createCourse(courseCreateDTO);
        return ResponseEntity.ok(courseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        CourseDTO courseDTO = courseService.getCourseById(id);
        return ResponseEntity.ok(courseDTO);
    }

    @PostMapping("/enroll")
    public ResponseEntity<Void> enrollStudent(@Valid @RequestBody EnrollmentDTO enrollmentDTO) {
        courseService.enrollStudent(enrollmentDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/department/{depratment}")
    public ResponseEntity<List<CourseDTO>> getCoursesByDepartment(@PathVariable String depratment) {
        List<CourseDTO> courses = courseService.getCourseByDepartment(depratment);
        return ResponseEntity.ok(courses);
    }
}
