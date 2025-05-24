package com.studyhub.repository;

import com.studyhub.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// ModuleRepository interface for Crud operation on the Module entity
@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    List<Module> findByCourseId(Long courseId);
    Optional<Module> findByCourseIdAndTitle(Long courseId, String title);
}