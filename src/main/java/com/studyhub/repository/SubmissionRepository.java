package com.studyhub.repository;

import com.studyhub.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// SubmissionRepository interface for CRUD operations on the Submission entity
@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByAssignmentId(Long assignmentId);
    List<Submission> findByAssignmentIdAndStudentId(Long assignmentId, Long studentId);
    Optional<Submission> findFirstByAssignmentIdAndStudentId(Long assignmentId, Long studentId);
}