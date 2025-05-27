package com.studyhub.mapper;

import com.studyhub.dto.assignment.AssignmentCreateDTO;
import com.studyhub.dto.assignment.AssignmentDTO;
import com.studyhub.dto.assignment.AssignmentUpdateDTO;
import com.studyhub.model.Assignment;
import org.mapstruct.*;

import java.util.List;

// AssignmentMapper for converting between Assignment and AssignmentDTOs
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AssignmentMapper {

    @Mapping(target = "courseId", source = "course.id")
    AssignmentDTO toDTO(Assignment assignment);
    List<AssignmentDTO> toDTOList(List<Assignment> assignments);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Assignment toEntity(AssignmentCreateDTO assignmentCreateDTO);

    @Mapping(target = "course", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateAssignmentFromDTO(AssignmentUpdateDTO assignmentUpdateDTO, @MappingTarget Assignment assignment);
}
