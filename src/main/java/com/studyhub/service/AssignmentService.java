package com.studyhub.service;

import com.studyhub.dto.assignment.AssignmentCreateDTO;
import com.studyhub.dto.assignment.AssignmentDTO;
import com.studyhub.dto.assignment.AssignmentUpdateDTO;
import com.studyhub.exception.ResourceNotFoundException;
import com.studyhub.mapper.AssignmentMapper;
import com.studyhub.model.Assignment;
import com.studyhub.model.Course;
import com.studyhub.repository.AssignmentRepository;
import com.studyhub.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

// Service class for handling assignment-related operations
@Service
@Transactional
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final CourseRepository courseRepository;
    private final AssignmentMapper assignmentMapper;

    public AssignmentService(AssignmentRepository assignmentRepository, CourseRepository courseRepository, AssignmentMapper assignmentMapper) {
        this.assignmentRepository = assignmentRepository;
        this.courseRepository = courseRepository;
        this.assignmentMapper = assignmentMapper;
    }

    // Creates a new assignment for a course
    public AssignmentDTO createAssignment(Long courseId, AssignmentCreateDTO assignmentCreateDTO) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + courseId));

        Assignment assignment = assignmentMapper.toEntity(assignmentCreateDTO);
        assignment.setCourse(course);
        Assignment savedAssignment = assignmentRepository.save(assignment);
        return assignmentMapper.toDTO(savedAssignment);
    }

    // Retrieves assignments for a specific course
    public List<AssignmentDTO> getAssignmentsByCourse(Long courseId) {
        List<Assignment> assignments = assignmentRepository.findByCourseId(courseId);
        if (assignments.isEmpty()) {
            throw new ResourceNotFoundException("No assignments found for course ID: " + courseId);
        }
        return assignmentMapper.toDTOList(assignments);
    }

    // Retrieves assignment by ID
    public AssignmentDTO getAssignmentById(Long courseId, Long id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No assignment found with ID: " + id));

        if (!assignment.getCourse().getId().equals(courseId)) {
            throw new ResourceNotFoundException("Assignment with ID " + id + " does not belong to course with ID " + courseId);
        }

        return assignmentMapper.toDTO(assignment);
    }

    // Updates an assignment
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

    // Deletes an assignment
    public void deleteAssignment(Long courseId, Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with ID: " + assignmentId));

        if (!assignment.getCourse().getId().equals(courseId)) {
            throw new ResourceNotFoundException("Assignment with ID " + assignmentId + " does not belong to course with ID " + courseId);
        }

        assignmentRepository.delete(assignment);
    }
}