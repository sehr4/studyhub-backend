package com.studyhub.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

// Data Transfer Object for assignment submission
@Data
public class AssignmentSubmissionDTO {

    @NotNull(message = "File cannot be null for submission")
    private MultipartFile file;
}