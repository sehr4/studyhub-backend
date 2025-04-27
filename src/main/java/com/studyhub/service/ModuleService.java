package com.studyhub.service;

import com.studyhub.dto.ModuleCreateDTO;
import com.studyhub.dto.ModuleDTO;
import com.studyhub.exception.ResourceNotFoundException;
import com.studyhub.mapper.ModuleMapper;
import com.studyhub.model.Course;
import com.studyhub.model.Module;
import com.studyhub.repository.CourseRepository;
import com.studyhub.repository.ModuleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + courseId));
        Module module = moduleMapper.toEntity(moduleCreateDTO);
        module.setCourse(course);
        Module savedModule = moduleRepository.save(module);
        return moduleMapper.toDTO(savedModule);
    }
}
