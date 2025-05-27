package com.studyhub.dto.assignment;

import lombok.Data;

import java.time.LocalDateTime;

// Data Transfer Object for assignment in API
@Data
public class AssignmentDTO {

    private Long id;
    private Long courseId;
    private String title;
    private String description;
    private LocalDateTime dueDate;

    public interface CreationValidation {}
}
