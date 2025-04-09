package com.studyhub.mapper;

import com.studyhub.dto.CourseCreateDTO;
import com.studyhub.dto.CourseDTO;
import com.studyhub.model.Course;
import com.studyhub.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.HashSet;
import java.util.Set;

// CourseMapper for conversion between Course and CourseDTOs
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseMapper {

    // Converts a Course entity to a CourseDTO
    @Mapping(target = "instructorIds", source = "instructors", qualifiedByName = "usersToIds")
    @Mapping(target = "studentIds", source = "students", qualifiedByName = "usersToIds")
    CourseDTO toDTO(Course course);

    // Converts a CourseDTO to a Course entity
    Course toEntity(CourseCreateDTO courseCreateDTO);

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
