package com.studyhub.mapper;

import com.studyhub.dto.module.ModuleCreateDTO;
import com.studyhub.dto.module.ModuleDTO;
import com.studyhub.dto.module.ModuleUpdateDTO;
import com.studyhub.model.Module;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

// ModuleMapper for converting between Module and ModuleDTOs
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ModuleMapper {

    @Mapping(target = "courseId", source = "course.id")
    ModuleDTO toDTO(Module module);
    List<ModuleDTO> toDTOList(List<Module> modules);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "resources", ignore = true)
    Module toEntity(ModuleCreateDTO moduleCreateDTO);

    @Mapping(target = "course", ignore = true)
    @Mapping(target = "resources", ignore = true)
    void updateModuleFromDTO(ModuleUpdateDTO moduleUpdateDTO, @MappingTarget Module module);
}
