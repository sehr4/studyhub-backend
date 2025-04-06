package com.studyhub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

// Course entity, maps to 'courses' table in the db
@Data
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_instructor",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id")
    )
    private Set<User> instructors = new HashSet<>();

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Course code cannot be blank")
    private String code;

    @Column (nullable = false)
    @NotBlank(message = "Department cannot be blank")
    private String department;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Title cannot be blank")
    private String title;

    private Integer credits;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_enrollment",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<User> students = new HashSet<>();

    public Course() {
    }

    public Course(Set<User> instructors, String code, String department, String title, Integer credits, String description, LocalDate startDate, LocalDate endDate, Set<User> students) {
        this.instructors = instructors;
        this.code = code;
        this.department = department;
        this.title = title;
        this.credits = credits;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.students = students;
    }
}
