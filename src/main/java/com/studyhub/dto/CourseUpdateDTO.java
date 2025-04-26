package com.studyhub.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

// Data Transfer Object for updating a course
@Data
public class CourseUpdateDTO {

    private Long id;

    @Size(min = 6, max = 6, message = "Course code must be exactly 6 characters")
    private String code;

    @Size(min = 4, max = 50, message = "Department must be between 4 and 50 characters")
    private String department;

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
