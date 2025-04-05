package com.studyhub.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

// Data Transfer Object for course creation
@Data
public class CourseCreateDTO {

    @NotBlank(message = "Course code cannot be blank")
    private String code;

    @NotBlank(message = "Department cannot be blank")
    private String department;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private Integer credits;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<Long> instructorIds;
}
