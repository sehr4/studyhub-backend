package com.studyhub.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// Data Transfer Object for resource in API
@Data
public class ResourceDTO {

    private Long id; // Display and update (ignored during creation)

    private Long moduleId;

    @NotBlank(message = "Title cannot be blank", groups = CreationValidation.class)
    private String title;

    @NotBlank(message = "Resource type cannot be blank", groups = CreationValidation.class)
    private String type;

    private String fileUrl; // Not used atm
    private String fileContent;
    private String originalFileName;

    private String content;

    // Validation group for creation
    public interface CreationValidation {}
}
