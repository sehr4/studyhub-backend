package com.studyhub.model;

import com.studyhub.constant.Grade;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

// Submission entity, maps to 'submissions' table in the db
@Data
@Entity
@Table(name = "submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @Lob
    @Column(nullable = true)
    private byte[] submittedFileContent;

    @Column(nullable = true)
    private String submittedFileName;

    @Column(nullable = true)
    private LocalDateTime submissionDate;

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

    public Submission() {
    }

    public Submission(Assignment assignment, User student) {
        this.assignment = assignment;
        this.student = student;
    }
}