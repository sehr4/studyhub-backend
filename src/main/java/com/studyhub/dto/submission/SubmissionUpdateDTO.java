package com.studyhub.dto.submission;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

// Data Transfer Object for submission update
@Data
public class SubmissionUpdateDTO {

    private Long id;

    @Size(max = 500, message = "Feedback cannot exceed 500 characters")
    private String feedback;

    @Pattern(regexp = "^[A-F]$", message = "Grade must be A, B, C, D, E, or F")
    private String grade;
}