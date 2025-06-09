package com.studyhub.service;

import com.studyhub.constant.RoleConstant;
import com.studyhub.dto.submission.SubmissionFileDTO;
import com.studyhub.dto.submission.SubmissionDTO;
import com.studyhub.dto.submission.SubmissionUpdateDTO;
import com.studyhub.exception.BadRequestException;
import com.studyhub.exception.ResourceNotFoundException;
import com.studyhub.mapper.SubmissionMapper;
import com.studyhub.model.Assignment;
import com.studyhub.model.Submission;
import com.studyhub.model.User;
import com.studyhub.repository.AssignmentRepository;
import com.studyhub.repository.SubmissionRepository;
import com.studyhub.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Service class for handling submission-related operations
@Service
@Transactional
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final AssignmentRepository assignmentRepository;
    private final UserRepository userRepository;
    private final SubmissionMapper submissionMapper;

    public SubmissionService(SubmissionRepository submissionRepository, AssignmentRepository assignmentRepository, UserRepository userRepository, SubmissionMapper submissionMapper) {
        this.submissionRepository = submissionRepository;
        this.assignmentRepository = assignmentRepository;
        this.userRepository = userRepository;
        this.submissionMapper = submissionMapper;
    }

    // Submits an assignment for a student
    public SubmissionDTO submitAssignment(Long assignmentId, Long studentId, SubmissionFileDTO submissionDTO) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with ID: " + assignmentId));

        if (assignment.getDueDate().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Submission deadline has passed for assignment ID: " + assignmentId);
        }

        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + studentId));
        if (!student.getRole().equals(RoleConstant.STUDENT)) {
            throw new BadRequestException("User with ID " + studentId + " is not a student");
        }

        try {
            if (submissionDTO.getFile() == null || submissionDTO.getFile().isEmpty()) {
                throw new BadRequestException("A file must be provided for submission");
            }
            byte[] fileBytes = submissionDTO.getFile().getBytes();

            Submission submission = new Submission(assignment, student);
            submission.setSubmittedFileContent(fileBytes);
            submission.setSubmittedFileName(submissionDTO.getFile().getOriginalFilename());
            submission.setSubmissionDate(LocalDateTime.now());
            Submission savedSubmission = submissionRepository.save(submission);

            return submissionMapper.toDTO(savedSubmission);
        } catch (IOException e) {
            throw new BadRequestException("Failed to process submission file: " + e.getMessage());
        }
    }

    // Retrieves a submitted file for download
    public SubmissionService.FileContentResponse getSubmittedFile(Long assignmentId, Long studentId) {
        Submission submission = submissionRepository.findFirstByAssignmentIdAndStudentId(assignmentId, studentId)
                .orElseThrow(() -> new ResourceNotFoundException("No submission found for assignment ID: " + assignmentId + " and student ID: " + studentId));

        if (submission.getSubmittedFileContent() == null || submission.getSubmittedFileContent().length == 0) {
            throw new ResourceNotFoundException("No submitted file available for assignment ID: " + assignmentId + " and student ID: " + studentId);
        }

        String fileName = submission.getSubmittedFileName() != null
                ? submission.getSubmittedFileName()
                : submission.getAssignment().getTitle() + "_" + studentId + ".dat";

        return new FileContentResponse(submission.getSubmittedFileContent(), fileName);
    }

    // Retrieves submissions for a specific student across all assignments in a course
    public List<SubmissionDTO> getSubmissionsByStudent(Long courseId, Long studentId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + studentId));
        if (!student.getRole().equals(RoleConstant.STUDENT)) {
            throw new BadRequestException("User with ID " + studentId + " is not a student");
        }

        List<Assignment> assignments = assignmentRepository.findByCourseId(courseId);
        if (assignments.isEmpty()) {
            throw new ResourceNotFoundException("No assignments found for course ID: " + courseId);
        }

        List<Submission> submissions = new ArrayList<>();
        for (Assignment assignment : assignments) {
            List<Submission> studentSubmissions = submissionRepository.findByAssignmentIdAndStudentId(assignment.getId(), studentId);
            if (!studentSubmissions.isEmpty()) {
                submissions.addAll(studentSubmissions);
            }
        }

        if (submissions.isEmpty()) {
            throw new ResourceNotFoundException("No submissions found for course ID: " + courseId + " and student ID: " + studentId);
        }

        return submissionMapper.toDTOList(submissions);
    }

    public List<SubmissionDTO> getSubmissionsByAssignment(Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId).orElseThrow(() -> new ResourceNotFoundException("Assignment not found with ID: " + assignmentId));
        List<Submission> submissions = submissionRepository.findByAssignmentId(assignmentId);
        if (submissions.isEmpty()) {
            throw new ResourceNotFoundException("No submissions found for assignment ID: " + assignmentId);
        }
        return submissionMapper.toDTOList(submissions);
    }

    // Updates a submission with feedback and grade
    public SubmissionDTO updateSubmission(Long assignmentId, Long studentId, SubmissionUpdateDTO submissionUpdateDTO) {
        Submission submission = submissionRepository.findFirstByAssignmentIdAndStudentId(assignmentId, studentId)
                .orElseThrow(() -> new ResourceNotFoundException("No submission found for assignment ID: " + assignmentId + " and student ID: " + studentId));

        submissionMapper.updateSubmissionFromDTO(submissionUpdateDTO, submission);
        Submission updatedSubmission = submissionRepository.save(submission);
        return submissionMapper.toDTO(updatedSubmission);
    }

    @Getter
    public static class FileContentResponse {
        private final byte[] content;
        private final String fileName;

        public FileContentResponse(byte[] content, String fileName) {
            this.content = content;
            this.fileName = fileName;
        }
    }
}
