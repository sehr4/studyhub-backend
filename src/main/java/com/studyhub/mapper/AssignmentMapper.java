package com.studyhub.mapper;

import com.studyhub.constant.Grade;
import com.studyhub.dto.AssignmentCreateDTO;
import com.studyhub.dto.AssignmentDTO;
import com.studyhub.dto.AssignmentUpdateDTO;
import com.studyhub.model.Assignment;
import org.mapstruct.*;

import java.util.List;

// AssignmentMapper for converting between Assignment and AssignmentDTOs
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AssignmentMapper {

    @Mapping(target = "courseId", source = "course.id")
    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "grade", source = "grade", qualifiedByName = "gradeToString")
    AssignmentDTO toDTO(Assignment assignment);
    List<AssignmentDTO> toDTOList(List<Assignment> assignments);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "submissionDate", ignore = true)
    @Mapping(target = "submittedFileContent", ignore = true)
    @Mapping(target = "submittedFileName", ignore = true)
    @Mapping(target = "feedback", ignore = true)
    @Mapping(target = "grade", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Assignment toEntity(AssignmentCreateDTO assignmentCreateDTO);

    @Mapping(target = "course", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "submissionDate", ignore = true)
    @Mapping(target = "submittedFileContent", ignore = true)
    @Mapping(target = "submittedFileName", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "grade", source = "grade", qualifiedByName = "stringToGrade")
    void updateAssignmentFromDTO(AssignmentUpdateDTO assignmentUpdateDTO, @MappingTarget Assignment assignment);

    @Named("gradeToString")
    default String gradeToString(Grade grade) {
        return grade != null ? grade.name() : null;
    }

    @Named("stringToGrade")
    default Grade stringToGrade(String grade) {
        return grade != null ? Grade.fromString(grade) : null;
    }
}