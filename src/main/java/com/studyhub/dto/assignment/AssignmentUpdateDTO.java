package com.studyhub.dto.assignment;

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
}
