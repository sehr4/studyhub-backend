package com.studyhub.service;

import com.studyhub.constant.RoleConstant;
import com.studyhub.dto.AssignmentCreateDTO;
import com.studyhub.dto.AssignmentDTO;
import com.studyhub.dto.AssignmentSubmissionDTO;
import com.studyhub.dto.AssignmentUpdateDTO;
import com.studyhub.exception.BadRequestException;
import com.studyhub.exception.ResourceNotFoundException;
import com.studyhub.mapper.AssignmentMapper;
import com.studyhub.model.Assignment;
import com.studyhub.model.Course;
import com.studyhub.model.User;
import com.studyhub.repository.AssignmentRepository;
import com.studyhub.repository.CourseRepository;
import com.studyhub.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

// Service class for handling assignment-related operations
@Service
@Transactional
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final AssignmentMapper assignmentMapper;

    public AssignmentService(AssignmentRepository assignmentRepository, CourseRepository courseRepository, UserRepository userRepository, AssignmentMapper assignmentMapper) {
        this.assignmentRepository = assignmentRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.assignmentMapper = assignmentMapper;
    }

    public AssignmentDTO createAssignment(Long courseId, AssignmentCreateDTO assignmentCreateDTO) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + courseId));

        Assignment assignment = assignmentMapper.toEntity(assignmentCreateDTO);
        assignment.setCourse(course);
        Assignment savedAssignment = assignmentRepository.save(assignment);
        return assignmentMapper.toDTO(savedAssignment);
    }

    public List<AssignmentDTO> getAssignmentsByCourse(Long courseId) {
        List<Assignment> assignments = assignmentRepository.findByCourseId(courseId);
        if (assignments.isEmpty()) {
            throw new ResourceNotFoundException("No assignments found for course ID: " + courseId);
        }
        return assignmentMapper.toDTOList(assignments);
    }

    public AssignmentDTO updateAssignment(Long courseId, Long assignmentId, AssignmentUpdateDTO assignmentUpdateDTO) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with ID: " + assignmentId));

        if (!assignment.getCourse().getId().equals(courseId)) {
            throw new ResourceNotFoundException("Assignment with ID " + assignmentId + " does not belong to course with ID " + courseId);
        }

        assignmentMapper.updateAssignmentFromDTO(assignmentUpdateDTO, assignment);
        Assignment updatedAssignment = assignmentRepository.save(assignment);
        return assignmentMapper.toDTO(updatedAssignment);
    }

    public void deleteAssignment(Long courseId, Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with ID: " + assignmentId));

        if (!assignment.getCourse().getId().equals(courseId)) {
            throw new ResourceNotFoundException("Assignment with ID " + assignmentId + " does not belong to course with ID " + courseId);
        }

        assignmentRepository.delete(assignment);
    }

    public AssignmentDTO submitAssignment(Long courseId, Long assignmentId, Long studentId, AssignmentSubmissionDTO submissionDTO) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with ID: " + assignmentId));

        if (!assignment.getCourse().getId().equals(courseId)) {
            throw new ResourceNotFoundException("Assignment with ID " + assignmentId + " does not belong to course with ID " + courseId);
        }

        if (assignment.getDueDate().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Submission deadline has passed for assignment ID: " + assignmentId);
        }

        // Look up the student
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
            assignment.setSubmittedFileContent(fileBytes);
            assignment.setSubmittedFileName(submissionDTO.getFile().getOriginalFilename());

            assignment.setSubmissionDate(LocalDateTime.now());
            assignment.setStudent(student);
            Assignment updatedAssignment = assignmentRepository.save(assignment);
            return assignmentMapper.toDTO(updatedAssignment);
        } catch (IOException e) {
            throw new BadRequestException("Failed to process submission file: " + e.getMessage());
        }
    }

    public AssignmentService.FileContentResponse getSubmittedFile(Long courseId, Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with ID: " + assignmentId));

        if (!assignment.getCourse().getId().equals(courseId)) {
            throw new ResourceNotFoundException("Assignment with ID " + assignmentId + " does not belong to course with ID " + courseId);
        }

        if (assignment.getSubmittedFileContent() == null || assignment.getSubmittedFileContent().length == 0) {
            throw new ResourceNotFoundException("No submitted file available for assignment ID: " + assignmentId);
        }

        String fileName = assignment.getSubmittedFileName() != null
                ? assignment.getSubmittedFileName()
                : assignment.getTitle() + ".dat";

        return new FileContentResponse(assignment.getSubmittedFileContent(), fileName);
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
