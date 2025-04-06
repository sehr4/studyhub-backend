package com.studyhub.mapper;

import com.studyhub.dto.CourseCreateDTO;
import com.studyhub.dto.CourseDTO;
import com.studyhub.model.Course;
import com.studyhub.model.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

// CourseMapper for conversion between Course and CourseDTOs
@Component
public class CourseMapper {

    // Converts a Course entity to a CourseDTO
    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setCode(course.getCode());
        courseDTO.setDepartment(course.getDepartment());
        courseDTO.setTitle(course.getTitle());
        courseDTO.setCredits(course.getCredits());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setStartDate(course.getStartDate());
        courseDTO.setEndDate(course.getEndDate());
        courseDTO.setInstructorIds(convertUsersToIds(course.getInstructors()));
        courseDTO.setStudentIds(convertUsersToIds(course.getStudents()));

        return courseDTO;
    }

    // Converts a CourseDTO to a Course entity
    public Course toEntity(CourseCreateDTO courseCreateDTO) {
        if (courseCreateDTO == null) {
            return null;
        }
        Course course = new Course();
        course.setCode(courseCreateDTO.getCode());
        course.setDepartment(courseCreateDTO.getDepartment());
        course.setTitle(courseCreateDTO.getTitle());
        course.setCredits(courseCreateDTO.getCredits());
        course.setDescription(courseCreateDTO.getDescription());
        course.setStartDate(courseCreateDTO.getStartDate());
        course.setEndDate(courseCreateDTO.getEndDate());

        return course;
    }

    // Helper method to convert a set of User entities to id's
    private Set<Long> convertUsersToIds(Set<User> users) {
        Set<Long> ids = new HashSet<>();
        for (User user : users) {
            ids.add(user.getId());
        }
        return ids;
    }
}
