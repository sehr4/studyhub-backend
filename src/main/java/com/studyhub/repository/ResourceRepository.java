package com.studyhub.repository;

import com.studyhub.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// ResourceRepository interface for Crud operation on the Resource entity
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    List<Resource> findByModuleId(Long moduleId);
}
