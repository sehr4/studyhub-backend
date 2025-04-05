package com.studyhub.repository;

import com.studyhub.model.Course;
import com.studyhub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// CourseRepository interface for Crud operation on the Course entity
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findCourseByCode(String code);
    Optional<Course> findCourseByTitle(String title);

    List<Course> findCoursesByDepartment(String department);
    List<Course> findCoursesByStudents(User student);
}
