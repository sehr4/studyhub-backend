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

    public CourseDTO createCourse(CourseCreateDTO courseCreateDTO) {
        // Check for duplicate code or title
        if (courseRepository.findCourseByCode(courseCreateDTO.getCode()).isPresent()) {
            throw new BadRequestException("Course with code: " + courseCreateDTO.getCode() + " already exists");
        }
        if (courseRepository.findCourseByTitle(courseCreateDTO.getTitle()).isPresent()) {
            throw new BadRequestException("Course with title: " + courseCreateDTO.getTitle() + " already exists");
        }

        // Map DTO to entity
        Course course = courseMapper.toEntity(courseCreateDTO);

        // Assign instructors if provided
        if (courseCreateDTO.getInstructorIds() != null && !courseCreateDTO.getInstructorIds().isEmpty()) {
            Set<User> instructors = new HashSet<>();
            for (Long id : courseCreateDTO.getInstructorIds()) {
                User user = userRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Instructor with ID: " + id + " not found"));
                if (user.getRole().equals(RoleConstant.INSTRUCTOR)) {
                    instructors.add(user);
                }
            }
            course.setInstructors(instructors);
        }

        // Save and return DTO
        Course savedCourse = courseRepository.save(course);
        return courseMapper.toDTO(savedCourse);
    }

    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course with ID: " + id + " not found"));
        return courseMapper.toDTO(course);
    }

    public void enrollStudent(EnrollmentDTO enrollmentDTO) {
        Course course = courseRepository.findById(enrollmentDTO.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course with ID: " + enrollmentDTO.getCourseId() + "was not found"));
        User student = userRepository.findById(enrollmentDTO.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student with ID: " + enrollmentDTO.getStudentId() + "was not found"));

        if (!student.getRole().equals(RoleConstant.STUDENT)) {
            throw new BadRequestException("User: " + enrollmentDTO.getStudentId() + " is not a student");
        }
        course.getStudents().add(student);
        courseRepository.save(course);
    }

    public List<CourseDTO> getCourseByDepartment(String department) {
        List<Course> courses = courseRepository.findCoursesByDepartment(department);
        if (courses.isEmpty()) {
            throw new ResourceNotFoundException("No courses found in department: " + department);
        }
        // Convert each course to CourseDTO
        List<CourseDTO> courseDTOs = new ArrayList<>();
        for (Course course : courses) {
            courseDTOs.add(courseMapper.toDTO(course));
        }
        return courseDTOs;
    }

    public List<CourseDTO> getCoursesByStudent(Long studentId) {
        // Check that user exists and with student role
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student with ID: " + studentId + " not found "));
        if (!student.getRole().equals(RoleConstant.STUDENT)) {
            throw new BadRequestException("User is not a student: " + studentId);
        }
        // Check that student has courses
        List<Course> courses = courseRepository.findCoursesByStudents(student);
        if (courses.isEmpty()) {
            throw new ResourceNotFoundException("No courses found for student with ID: " + studentId);
        }
        // Convert to DTOs
        List<CourseDTO> courseDTOs = new ArrayList<>();
        for (Course course : courses) {
            courseDTOs.add(courseMapper.toDTO(course));
        }
        return courseDTOs;
    }
}
