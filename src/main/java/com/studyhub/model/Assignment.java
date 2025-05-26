package com.studyhub.model;

import com.studyhub.constant.Grade;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

// Assignment entity, maps to 'assignments' table in the db
@Data
@Entity
@Table(name = "assignments")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = true)
    private User student;

    @Column(nullable = false)
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    @NotBlank(message = "Description cannot be blank")
    private String description;

    @Column(nullable = false)
    @NotNull(message = "Due date cannot be null")
    private LocalDateTime dueDate;

    @Column(nullable = true)
    private LocalDateTime submissionDate;

    @Lob
    @Column(nullable = true)
    private byte[] submittedFileContent;

    @Column(nullable = true)
    private String submittedFileName;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String feedback;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Grade grade;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Assignment() {
    }

    public Assignment(Course course, String title, String description, LocalDateTime dueDate) {
        this.course = course;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }
}