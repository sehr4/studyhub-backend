package com.studyhub.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

// Data Transfer Object for course creation
@Data
public class CourseCreateDTO {

    @NotBlank(message = "Course code cannot be blank")
    @Size(min = 6, max = 6, message = "Course code must be exactly 6 characters")
    private String code;

    @NotBlank(message = "Department cannot be blank")
    @Size(min = 2, max = 50, message = "Department must be between 2 and 50 characters")
    private String department;

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 4, max = 100, message = "Title must be between 4 and 100 characters")
    private String title;

    @Min(value = 1, message = "Credits must be at least 1")
    @Max(value = 30, message = "Credits cannot exceed 30")
    private Integer credits;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    private LocalDate startDate;
    private LocalDate endDate;
    private Set<Long> instructorIds;
}
