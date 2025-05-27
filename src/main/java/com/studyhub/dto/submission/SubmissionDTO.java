package com.studyhub.dto.submission;

import lombok.Data;

import java.time.LocalDateTime;

// Data transfer object for submission API
@Data
public class SubmissionDTO {

    private Long id;
    private Long assignmentId;
    private Long studentId;
    private String submittedFileName;
    private LocalDateTime submissionDate;
    private String feedback;
    private String grade;
}
