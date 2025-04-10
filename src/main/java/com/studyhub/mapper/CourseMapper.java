package com.studyhub.mapper;

import com.studyhub.dto.CourseCreateDTO;
import com.studyhub.dto.CourseDTO;
import com.studyhub.dto.CourseSummaryDTO;
import com.studyhub.dto.CourseUpdateDTO;
import com.studyhub.model.Course;
import com.studyhub.model.User;
import org.mapstruct.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// CourseMapper for conversion between Course and CourseDTOs
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseMapper {

    // Converts a Course entity to a CourseDTO
    @Mapping(target = "instructorIds", source = "instructors", qualifiedByName = "usersToIds")
    @Mapping(target = "studentIds", source = "students", qualifiedByName = "usersToIds")
    CourseDTO toDTO(Course course);
    List<CourseDTO> toDTOList(List<Course> courses);

    // Converts a CourseDTO to a Course entity
    @Mapping(target = "id", ignore = true) // auto-generated
    @Mapping(target = "instructors", ignore = true) // Handle in CourseService
    @Mapping(target = "students", ignore = true) // adding in enrollment
    Course toEntity(CourseCreateDTO courseCreateDTO);

    @Mapping(target = "instructors", ignore = true)
    @Mapping(target = "students", ignore = true)
    void updateCourseFromDTO(CourseUpdateDTO courseUpdateDTO, @MappingTarget Course course);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    @Mapping(target = "department", source = "department")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "credits", source = "credits")
    CourseSummaryDTO toSummaryDTO(Course course);
    List<CourseSummaryDTO> toSummaryDTOList(List<Course> courses);

    // Helper method to convert a set of User entities to id's
    @Named("usersToIds")
    default Set<Long> convertUsersToIds(Set<User> users) {
        if (users == null) {
            return null;
        }
        Set<Long> ids = new HashSet<>();
        for (User user : users) {
            ids.add(user.getId());
        }
        return ids;
    }
}
