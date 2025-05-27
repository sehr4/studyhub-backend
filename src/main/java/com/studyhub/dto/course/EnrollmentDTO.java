package com.studyhub.dto.course;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

// Simple Data Transfer Object for student enrollment
@Data
public class EnrollmentDTO {

    @NotNull(message = "Course ID cannot be null")
    @Positive(message = "Course ID must be positive")
    private Long courseId;

    @NotNull(message = "Student ID cannot be null")
    @Positive(message = "Student ID must be positive")
    private Long studentId;
}
