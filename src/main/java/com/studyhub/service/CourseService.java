package com.studyhub.service;

import com.studyhub.constant.RoleConstant;
import com.studyhub.dto.*;
import com.studyhub.exception.BadRequestException;
import com.studyhub.exception.ResourceNotFoundException;
import com.studyhub.mapper.CourseMapper;
import com.studyhub.model.Course;
import com.studyhub.model.User;
import com.studyhub.repository.CourseRepository;
import com.studyhub.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (courseRepository.findByCode(courseCreateDTO.getCode()).isPresent()) {
            throw new BadRequestException("Course with code '" + courseCreateDTO.getCode() + "' already exists");
        }
        if (courseRepository.findByTitle(courseCreateDTO.getTitle()).isPresent()) {
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
        // We use a Set (HashSet) in the Course, so there will be no error,
        // but this if test provide the user with more info.
        if (course.getStudents().contains(student)) {
            throw new BadRequestException("Student with ID " + student.getId() + " is already enrolled in course with ID " + course.getId());
        }
        course.getStudents().add(student);
        courseRepository.save(course);
    }

    // Retrieves courses by department
    public List<CourseDTO> getCoursesByDepartment(String department) {
        List<Course> courses = courseRepository.findByDepartment(department);
        if (courses.isEmpty()) {
            throw new ResourceNotFoundException("No courses found in department: " + department);
        }
        return courseMapper.toDTOList(courses);
    }

    // Retrieves summarized courses by department
    public List<CourseSummaryDTO> getSummarizedCoursesByDepartment(String department) {
        List<Course> courses = courseRepository.findByDepartment(department);
        if (courses.isEmpty()) {
            throw new ResourceNotFoundException("No courses found in department: " + department);
        }
        return courseMapper.toSummaryDTOList(courses);
    }

    // TEST: attempt to reduce code duplication through helper method
    private List<Course> getCoursesForStudent(Long studentId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + studentId));
        // Verify student exists and has student role
        if (!student.getRole().equals(RoleConstant.STUDENT)) {
            throw new BadRequestException("User with ID " + studentId + " is not a student");
        }
        List<Course> courses = courseRepository.findByStudents(student);
        if (courses.isEmpty()) {
            throw new ResourceNotFoundException("No courses found for student with ID: " + studentId);
        }
        return courses;
    }

    // Retrieves detailed list of courses for a specific student
    public List<CourseDTO> getCoursesByStudent(Long studentId) {
        return courseMapper.toDTOList(getCoursesForStudent(studentId));
    }

    // Retrieves a summarized version of courses for a specific student
    public List<CourseSummaryDTO> getSummarizedCoursesByStudent(Long studentId) {
        return courseMapper.toSummaryDTOList((getCoursesForStudent(studentId)));
    }

//    // Retrieves courses for a specific student
//    public List<CourseDTO> getCoursesByStudent(Long studentId) {
//        // Verify student exists and has student role
//        User student = userRepository.findById(studentId)
//                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + studentId));
//
//        if (!student.getRole().equals(RoleConstant.STUDENT)) {
//            throw new BadRequestException("User with ID " + studentId + " is not a student");
//        }
//        // Retrieve student's courses
//        List<Course> courses = courseRepository.findByStudents(student);
//        if (courses.isEmpty()) {
//            throw new ResourceNotFoundException("No courses found for student with ID: " + studentId);
//        }
//        return courseMapper.toDTOList(courses);
//    }
//
//    public List<CourseSummaryDTO> getSummarizedCoursesByStudent(Long studentId) {
//        User student = userRepository.findById(studentId)
//                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + studentId));
//
//        if (!student.getRole().equals(RoleConstant.STUDENT)) {
//            throw new BadRequestException("User with ID " + studentId + " is not a student");
//        }
//        List<Course> courses = courseRepository.findByStudents(student);
//        if (courses.isEmpty()) {
//            throw new ResourceNotFoundException("No courses found for student with ID: " + studentId);
//        }
//        return courseMapper.toSummaryDTOList(courses);
//    }

    // Update Course
    public CourseDTO updateCourse(CourseUpdateDTO courseUpdateDTO) {
        Course existingCourse = courseRepository.findById(courseUpdateDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + courseUpdateDTO.getId()));

        // Check for duplicate code or title if it is to be changed
        if (courseUpdateDTO.getCode() != null &&
                !existingCourse.getCode().equals(courseUpdateDTO.getCode()) &&
                courseRepository.findByCode(courseUpdateDTO.getCode()).isPresent()) {
            throw new BadRequestException("Course with code '" + courseUpdateDTO.getCode() + "' already exists");
        }
        if (courseUpdateDTO.getTitle() != null &&
                !existingCourse.getTitle().equals(courseUpdateDTO.getTitle()) &&
                courseRepository.findByTitle(courseUpdateDTO.getTitle()).isPresent()) {
            throw new BadRequestException("Course with title '" + courseUpdateDTO.getTitle() + "' already exists");
        }

        // Update DTO
        courseMapper.updateCourseFromDTO(courseUpdateDTO, existingCourse);

        // Update the instructors
        if (courseUpdateDTO.getInstructorIds() != null) {
            Set<User> instructors = new HashSet<>();
            for (Long id : courseUpdateDTO.getInstructorIds()) {
                User user = userRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with ID: " + id));
                if (!user.getRole().equals(RoleConstant.INSTRUCTOR)) {
                    throw new BadRequestException("User with ID " + id + " is not an instructor");
                }
                instructors.add(user);
            }
            existingCourse.setInstructors(instructors);
        }
        Course updatedCourse = courseRepository.save(existingCourse);
        return courseMapper.toDTO(updatedCourse);
    }

    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + id));
        courseRepository.delete(course);
    }
}
