package com.studyhub.service;

import com.studyhub.constant.RoleConstant;
import com.studyhub.dto.CourseCreateDTO;
import com.studyhub.dto.CourseDTO;
import com.studyhub.dto.EnrollmentDTO;
import com.studyhub.exception.BadRequestException;
import com.studyhub.exception.ResourceNotFoundException;
import com.studyhub.mapper.CourseMapper;
import com.studyhub.model.Course;
import com.studyhub.model.User;
import com.studyhub.repository.CourseRepository;
import com.studyhub.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Service class for handling course-related operations
@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.courseMapper = courseMapper;
    }

    // Creates a new course
    public CourseDTO createCourse(CourseCreateDTO courseCreateDTO) {
        // Check for duplicate code or title
        if (courseRepository.findCourseByCode(courseCreateDTO.getCode()).isPresent()) {
            throw new BadRequestException("Course with code '" + courseCreateDTO.getCode() + "' already exists");
        }
        if (courseRepository.findCourseByTitle(courseCreateDTO.getTitle()).isPresent()) {
            throw new BadRequestException("Course with title '" + courseCreateDTO.getTitle() + "' already exists");
        }

        // Map DTO to entity
        Course course = courseMapper.toEntity(courseCreateDTO);

        // Assign instructors if provided
        if (courseCreateDTO.getInstructorIds() != null && !courseCreateDTO.getInstructorIds().isEmpty()) {
            Set<User> instructors = new HashSet<>();
            for (Long id : courseCreateDTO.getInstructorIds()) {
                User user = userRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with ID: " + id));

                // Validate that user has an instructor role
                if (!user.getRole().equals(RoleConstant.INSTRUCTOR)) {
                    throw new BadRequestException("User with ID " + id + " is not an instructor");
                }
                instructors.add(user);
            }
            course.setInstructors(instructors);
        }

        // Save and return DTO
        Course savedCourse = courseRepository.save(course);
        return courseMapper.toDTO(savedCourse);
    }

    // Retrieves a course by its ID
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + id));
        return courseMapper.toDTO(course);
    }

    // Enrolls a student in a course
    public void enrollStudent(EnrollmentDTO enrollmentDTO) {
        Course course = courseRepository.findById(enrollmentDTO.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + enrollmentDTO.getCourseId()));
        User student = userRepository.findById(enrollmentDTO.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + enrollmentDTO.getStudentId()));

        // Validate student role
        if (!student.getRole().equals(RoleConstant.STUDENT)) {
            throw new BadRequestException("User with ID " + enrollmentDTO.getStudentId() + " is not a student");
        }
        course.getStudents().add(student);
        courseRepository.save(course);
    }

    // Retrieves courses by department
    public List<CourseDTO> getCoursesByDepartment(String department) {
        List<Course> courses = courseRepository.findCoursesByDepartment(department);
        if (courses.isEmpty()) {
            throw new ResourceNotFoundException("No courses found in department: " + department);
        }

        return convertToDTOList(courses);
    }

    // Retrieves courses for a specific student
    public List<CourseDTO> getCoursesByStudent(Long studentId) {
        // Verify student exists and has student role
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + studentId));

        if (!student.getRole().equals(RoleConstant.STUDENT)) {
            throw new BadRequestException("User with ID " + studentId + " is not a student");
        }

        // Retrieve student's courses
        List<Course> courses = courseRepository.findCoursesByStudents(student);
        if (courses.isEmpty()) {
            throw new ResourceNotFoundException("No courses found for student with ID: " + studentId);
        }

        return convertToDTOList(courses);
    }

    // Helper method to convert a list of Course entities to DTOs
    private List<CourseDTO> convertToDTOList(List<Course> courses) {
        List<CourseDTO> courseDTOs = new ArrayList<>();
        for (Course course : courses) {
            courseDTOs.add(courseMapper.toDTO(course));
        }
        return courseDTOs;
    }
}
