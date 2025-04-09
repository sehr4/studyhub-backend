package com.studyhub.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

// Data Transfer Object for course in API
@Data
public class CourseDTO {

    private Long id;

    @NotBlank(message = "Course code cannot be blank")
    @Size(min = 6, max = 6)
    private String code;

    @NotBlank(message = "Department cannot be blank")
    @Size(min = 2, max = 50)
    private String department;

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 4, max = 100)
    private String title;

    @Min(1) @Max(30)
    private Integer credits;

    @Size(max = 500)
    private String description;

    private LocalDate startDate;
    private LocalDate endDate;
    private Set<Long> instructorIds;
    private Set<Long> studentIds;
}
