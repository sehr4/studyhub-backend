package com.studyhub.controller;

import com.studyhub.dto.ResourceDTO;
import com.studyhub.service.ResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

// REST controller for handling module resources API requests
@RestController
@RequestMapping("/modules/{moduleId}/resources")
@Tag(name = "Resource Controller", description = "APIs for managing course module resources")
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping
        @Operation(summary = "Create a new resource", description = "Creates a resource for a specified module")
        @ApiResponses({
                @ApiResponse(responseCode = "200", description = "Resource created successfully",
                        content = @Content(schema = @Schema(implementation = ResourceDTO.class))),
                @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
                @ApiResponse(responseCode = "404", description = "Module not found", content = @Content),
                @ApiResponse(responseCode = "500", description = "Unexpected server error")
        })
        public ResponseEntity<ResourceDTO> createResource(
                @PathVariable Long moduleId,
                @Valid @Validated(ResourceDTO.CreationValidation.class) @RequestBody ResourceDTO resourceDTO) {
            ResourceDTO createdResource = resourceService.createResource(moduleId, resourceDTO);
            return ResponseEntity.ok(createdResource);
        }

    @GetMapping
    @Operation(summary = "Get resources by module", description = "Retrieves all resources for a specified module")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Resources found",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResourceDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No resources found for module", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<List<ResourceDTO>> getResourcesByModule(@PathVariable Long moduleId) {
        List<ResourceDTO> resources = resourceService.getResourcesByModule(moduleId);
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{resourceId}")
    @Operation(summary = "Update a resource", description = "Updates an existing resource in a specific module")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Resource updated successfully",
                    content = @Content(schema = @Schema(implementation = ResourceDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource or module not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<ResourceDTO> updateResource(
            @PathVariable Long moduleId,
            @PathVariable Long resourceId,
            @Valid @RequestBody ResourceDTO resourceDTO) {
        resourceDTO.setId(resourceId);
        ResourceDTO updatedResource = resourceService.updateResource(moduleId, resourceId, resourceDTO);
        return ResponseEntity.ok(updatedResource);
    }

    @DeleteMapping("/{resourceId}")
    @Operation(summary = "Delete a resource", description = "Deletes a resource in a specific module")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Resource deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Resource or module not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<Void> deleteResource(@PathVariable Long moduleId, @PathVariable Long resourceId) {
        resourceService.deleteResource(moduleId, resourceId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload a file resource", description = "Uploads a file as a resource for a specified module")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "File uploaded successfully",
                    content = @Content(schema = @Schema(implementation = ResourceDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Module not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    // Accepts MultipartFile, title, and moduleId from the HTTP request
    public ResponseEntity<ResourceDTO> uploadFileResource(@PathVariable Long moduleId,
                                                          @RequestParam("file") MultipartFile file,
                                                          @RequestParam("title") String title) {
        //  Checks if the uploaded file is empty, returns 400 Bad Request if so
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            // Convert file to bytes Array (a Base64 string)
            byte[] fileBytes = file.getBytes();
            // TEST print
            if (fileBytes.length > 10) {
                System.out.println("First 10 bytes: " + Arrays.toString(Arrays.copyOfRange(fileBytes, 0, 10)));
            } else {
                System.out.println("All bytes: " + Arrays.toString(fileBytes));
            }
            System.out.println("Total bytes: " + fileBytes.length);

            // Converts the byte array to a Base64 string using
            String fileContentBase64 = Base64.getEncoder().encodeToString(fileBytes);

            ResourceDTO resourceDTO = new ResourceDTO();
            resourceDTO.setTitle(title);
            resourceDTO.setType("FILE");
            resourceDTO.setFileContent(fileContentBase64);
            resourceDTO.setOriginalFileName(file.getOriginalFilename());

            ResourceDTO createdResource = resourceService.createResource(moduleId, resourceDTO);
            return ResponseEntity.ok(createdResource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{resourceId}/download")
    @Operation(summary = "Download a file resource", description = "Downloads a file resource for a specified module")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "File downloaded successfully",
                    content = @Content(mediaType = "application/octet-stream")),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource or module not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<byte[]> downloadResource(@PathVariable Long moduleId, @PathVariable Long resourceId) {
        ResourceService.FileContentResponse fileResponse = resourceService.getFileContentAndName(resourceId, moduleId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileResponse.getFileName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileResponse.getContent());
    }
}
