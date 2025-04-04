package com.studyhub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

// Course entity, maps to 'courses' table in the db
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_instructor",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id")
    )
    private Set<User> instructors = new HashSet<>();

    @Column(name = "code", nullable = false, unique = true)
    @NotBlank(message = "Course code cannot be blank")
    private String code;

    @Column (name = "department", nullable = false)
    @NotBlank(message = "Department cannot be blank")
    private String department;

    @Column(name = "title", nullable = false, unique = true)
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @Column(name = "credits")
    private Integer credits;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_enrollment",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<User> students = new HashSet<>();
}
