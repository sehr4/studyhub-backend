package com.studyhub.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

// Data Transfer Object for assignment update
@Data
public class AssignmentUpdateDTO {

    private Long id;

    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    private LocalDateTime dueDate;

    @Size(max = 500, message = "Feedback cannot exceed 500 characters")
    private String feedback;

    @Pattern(regexp = "^[A-F]$", message = "Grade must be A, B, C, D, E, or F")
    private String grade;
}