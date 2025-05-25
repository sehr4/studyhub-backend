package com.studyhub.service;

import com.studyhub.dto.ResourceDTO;
import com.studyhub.exception.BadRequestException;
import com.studyhub.exception.ResourceNotFoundException;
import com.studyhub.mapper.ResourceMapper;
import com.studyhub.model.Module;
import com.studyhub.model.Resource;
import com.studyhub.constant.ResourceType;
import com.studyhub.repository.ModuleRepository;
import com.studyhub.repository.ResourceRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;

@Service
@Transactional
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final ModuleRepository moduleRepository;
    private final ResourceMapper resourceMapper;

    public ResourceService(ResourceRepository resourceRepository, ModuleRepository moduleRepository, ResourceMapper resourceMapper) {
        this.resourceRepository = resourceRepository;
        this.moduleRepository = moduleRepository;
        this.resourceMapper = resourceMapper;
    }

    // Creates a new resource for a module
    public ResourceDTO createResource(Long moduleId, ResourceDTO resourceDTO) {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found with ID: " + moduleId));

        ResourceType type = ResourceType.valueOf(resourceDTO.getType().toUpperCase());
        validateResourceType(resourceDTO, type);

        Resource resource = new Resource();
        resource.setModule(module);
        resource.setTitle(resourceDTO.getTitle());
        return getResourceDTO(resourceDTO, type, resource);
    }

    // Retrieves resources by the ModuleId (probably need to add retrieval based on other criteria as well)
    public List<ResourceDTO> getResourcesByModule(Long moduleId) {
        List<Resource> resources = resourceRepository.findByModuleId(moduleId);
        if (resources.isEmpty()) {
            throw new ResourceNotFoundException("No resources found for module ID: " + moduleId);
        }
        return resourceMapper.toDTOList(resources);
    }

    // Update a Resource
    public ResourceDTO updateResource(Long moduleId, Long resourceId, ResourceDTO resourceDTO) {
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with ID: " + resourceId));

        if (!resource.getModule().getId().equals(moduleId)) {
            throw new ResourceNotFoundException("Resource with ID " + resourceId + " does not belong to module with ID " + moduleId);
        }

        ResourceType newType = resourceDTO.getType() != null ? ResourceType.valueOf(resourceDTO.getType().toUpperCase()) : resource.getType();
        validateResourceType(resourceDTO, newType);

        resource.setTitle(resourceDTO.getTitle() != null ? resourceDTO.getTitle() : resource.getTitle());
        return getResourceDTO(resourceDTO, newType, resource);
    }

    public void deleteResource(Long moduleId, Long resourceId) {
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with ID: " + resourceId));

        if (!resource.getModule().getId().equals(moduleId)) {
            throw new ResourceNotFoundException("Resource with ID " + resourceId + " does not belong to module with ID " + moduleId);
        }

        resourceRepository.delete(resource);
    }


    public Resource findByIdAndModuleId(Long resourceId, Long moduleId) {
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with ID: " + resourceId));
        if (!resource.getModule().getId().equals(moduleId)) {
            throw new ResourceNotFoundException("Resource with ID " + resourceId + " does not belong to module with ID " + moduleId);
        }
        if (resource.getType() != ResourceType.FILE) {
            throw new BadRequestException("Resource with ID " + resourceId + " is not a FILE type resource");
        }
        return resource;
    }

    // Helper method extracted from createResource and updateResource for ResourceType validation (return void)
    private void validateResourceType(ResourceDTO resourceDTO, ResourceType type) {
        if (type == null) {
            throw new BadRequestException("Invalid resource type. Must be 'FILE' or 'TEXT'");
        }

        if (type == ResourceType.FILE && (resourceDTO.getFileContent() == null || resourceDTO.getFileContent().isBlank())) {
            throw new BadRequestException("File content is required for FILE type resources");
        }
        if (type == ResourceType.TEXT && (resourceDTO.getContent() == null || resourceDTO.getContent().isBlank())) {
            throw new BadRequestException("Content is required for TEXT type resources");
        }
        if ((type == ResourceType.FILE && resourceDTO.getContent() != null) ||
                (type == ResourceType.TEXT && resourceDTO.getFileContent() != null)) {
            throw new BadRequestException("Only one of fileContent or content should be provided based on type");
        }
    }

    // Helper method extracted from createResource and updateResource returns a saved / updated ResourceDTO
    private ResourceDTO getResourceDTO(ResourceDTO resourceDTO, ResourceType type, Resource resource) {
        resource.setType(type);
        resource.setFileUrl(null); // Not used in DB for now (testing)
        if (type == ResourceType.FILE) {
            resource.setFileContent(Base64.getDecoder().decode(resourceDTO.getFileContent()));
            resource.setOriginalFileName(resourceDTO.getOriginalFileName());
        } else {
            resource.setFileContent(null);
        }
        resource.setContent(type == ResourceType.TEXT ? resourceDTO.getContent() : null);

        Resource savedResource = resourceRepository.save(resource);
        return resourceMapper.toDTO(savedResource);
    }

    @Getter
    public static class FileContentResponse {
        private final byte[] content;
        private final String fileName;

        public FileContentResponse(byte[] content, String fileName) {
            this.content = content;
            this.fileName = fileName;
        }
    }

    public FileContentResponse getFileContentAndName(Long resourceId, Long moduleId) {
        Resource resource = findByIdAndModuleId(resourceId, moduleId);

        if (resource.getFileContent() == null || resource.getFileContent().length == 0) {
            throw new ResourceNotFoundException("No file content available for resource ID: " + resourceId);
        }

        String fileName = resource.getOriginalFileName() != null
                ? resource.getOriginalFileName()
                : resource.getTitle().replaceAll("[^a-zA-Z0-9.-]", "_") + ".dat";

        return new FileContentResponse(resource.getFileContent(), fileName);
    }
}
