//package com.studyhub.repository;
//
//import com.studyhub.model.Course;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface CourseRepository extends JpaRepository<Course, Long> {
//    List<Course> findCoursesByDepartment(String department);
//    List<Course> findCoursesByInstructors(String user);
//    List<Course> findCoursesByStudents(String user);
//
//}
