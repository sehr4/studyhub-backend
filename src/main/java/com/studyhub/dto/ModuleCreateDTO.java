package com.studyhub.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ModuleCreateDTO {

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 3, max = 30, message = "Title must be between 3 and 50 characters")
    private String title;

    private String description;

    @NotNull(message = "Module number cannot be null")
    @Min(value = 1, message = "Module number must be at least 1")
    private Integer moduleNumber;
}
