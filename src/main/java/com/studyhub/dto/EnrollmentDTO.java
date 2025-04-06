package com.studyhub.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

// Simple Data Transfer Object for student enrollment
@Data
public class EnrollmentDTO {

    @NotNull(message = "Course ID cannot be null")
    private Long courseId;

    @NotNull(message = "Student ID cannot be null")
    private Long studentId;
}
