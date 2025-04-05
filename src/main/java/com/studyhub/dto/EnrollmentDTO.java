package com.studyhub.dto;

import lombok.Data;

// Simple Data Transfer Object for student enrollment
@Data
public class EnrollmentDTO {
    private Long courseId;
    private Long studentId;
}
