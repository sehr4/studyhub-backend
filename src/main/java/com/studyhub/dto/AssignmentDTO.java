package com.studyhub.dto;

import lombok.Data;

import java.time.LocalDateTime;

// Data Transfer Object for assignment in API
@Data
public class AssignmentDTO {

    private Long id;
    private Long courseId;
    private Long studentId;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private LocalDateTime submissionDate;
    private String submittedFileName;
    private String feedback;
    private String grade;

    public interface CreationValidation {}
}