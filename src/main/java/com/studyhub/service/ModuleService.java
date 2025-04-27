package com.studyhub.service;

import com.studyhub.dto.ModuleCreateDTO;
import com.studyhub.dto.ModuleDTO;
import com.studyhub.dto.ModuleUpdateDTO;
import com.studyhub.exception.BadRequestException;
import com.studyhub.exception.ResourceNotFoundException;
import com.studyhub.mapper.ModuleMapper;
import com.studyhub.model.Course;
import com.studyhub.model.Module;
import com.studyhub.repository.CourseRepository;
import com.studyhub.repository.ModuleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ModuleService {

    private final ModuleRepository moduleRepository;
    private final CourseRepository courseRepository;
    private final ModuleMapper moduleMapper;

    public ModuleService(ModuleRepository moduleRepository, CourseRepository courseRepository, ModuleMapper moduleMapper) {
        this.moduleRepository = moduleRepository;
        this.courseRepository = courseRepository;
        this.moduleMapper = moduleMapper;
    }

    public ModuleDTO createModule(Long courseId, ModuleCreateDTO moduleCreateDTO) {
        // Validate course exists
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + courseId));

        // Check for duplicate title within the same course
        Optional<Module> existingModule = moduleRepository.findByCourseIdAndTitle(courseId, moduleCreateDTO.getTitle());
        if (existingModule.isPresent()) {
            throw new BadRequestException("A module with title '" + moduleCreateDTO.getTitle() + "' already exists in course with ID: " + courseId);
        }
        Module module = moduleMapper.toEntity(moduleCreateDTO);
        module.setCourse(course);
        Module savedModule = moduleRepository.save(module);
        return moduleMapper.toDTO(savedModule);
    }

    public List<ModuleDTO> getModulesByCourse(Long courseId) {
        List<Module> modules = moduleRepository.findByCourseId(courseId);
        if (modules.isEmpty()) {
            throw new ResourceNotFoundException("No modules found for course ID: " + courseId);
        }
        return moduleMapper.toDTOList(modules);
    }

    public ModuleDTO updateModule(Long courseId, Long moduleId, ModuleUpdateDTO moduleUpdateDTO) {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found with ID: " + moduleId));

        // Validate that the module belongs to the specified course
        if (!module.getCourse().getId().equals(courseId)) {
            throw new ResourceNotFoundException("Module with ID " + moduleId + " does not belong to course with ID " + courseId);
        }
        // Check for duplicate title within the same course
        if (moduleUpdateDTO.getTitle() != null) {
            Optional<Module> existingModule = moduleRepository.findByCourseIdAndTitle(courseId, moduleUpdateDTO.getTitle());
            if (existingModule.isPresent() && !existingModule.get().getId().equals(moduleId)) {
                throw new BadRequestException("A module with title '" + moduleUpdateDTO.getTitle() + "' already exists in course with ID: " + courseId);
            }
        }
        moduleMapper.updateModuleFromDTO(moduleUpdateDTO, module);
        Module updatedModule = moduleRepository.save(module);
        return moduleMapper.toDTO(updatedModule);
    }

    public void deleteModule(Long courseId, Long moduleId) {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found with ID: " + moduleId));
        // Validate that the module belongs to the specified course
        if (!module.getCourse().getId().equals(courseId)) {
            throw new ResourceNotFoundException("Module with ID " + moduleId + " does not belong to course with ID " + courseId);
        }
        moduleRepository.delete(module);
    }
}