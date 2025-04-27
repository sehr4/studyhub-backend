package com.studyhub.controller;

import com.studyhub.dto.ModuleCreateDTO;
import com.studyhub.dto.ModuleDTO;
import com.studyhub.dto.ModuleUpdateDTO;
import com.studyhub.service.ModuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses/{courseId}/modules")
@Tag(name = "Module Controller", description = "APIs for managing course modules")
public class ModuleController {

    private final ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @PostMapping
    @Operation(summary = "Create a new module", description = "Creates a module for a specified course")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Module created successfully",
                    content = @Content(schema = @Schema(implementation = ModuleDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Course not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<ModuleDTO> createModule(@PathVariable Long courseId, @Valid @RequestBody ModuleCreateDTO moduleCreateDTO) {
        ModuleDTO moduleDTO = moduleService.createModule(courseId, moduleCreateDTO);
        return ResponseEntity.ok(moduleDTO);
    }

    @GetMapping
    @Operation(summary = "Get modules by course", description = "Retrieves all modules for a specified course")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Modules found",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ModuleDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No modules found for course", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<List<ModuleDTO>> getModulesByCourse(@PathVariable Long courseId) {
        List<ModuleDTO> modules = moduleService.getModulesByCourse(courseId);
        return ResponseEntity.ok(modules);
    }

    @PutMapping("/{moduleId}")
    @Operation(summary = "Update a module", description = "Updates an existing module in a specific course")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Module updated successfully",
                    content = @Content(schema = @Schema(implementation = ModuleDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Module or course not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<ModuleDTO> updateModule(
            @PathVariable Long courseId,
            @PathVariable Long moduleId,
            @Valid @RequestBody ModuleUpdateDTO moduleUpdateDTO) {
        moduleUpdateDTO.setId(moduleId);
        ModuleDTO updatedModule = moduleService.updateModule(courseId, moduleId, moduleUpdateDTO);
        return ResponseEntity.ok(updatedModule);
    }

    @DeleteMapping("/{moduleId}")
    @Operation(summary = "Delete a module", description = "Deletes a module in a specific course")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Module deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Module or course not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<Void> deleteModule(
            @PathVariable Long courseId,
            @PathVariable Long moduleId) {
        moduleService.deleteModule(courseId, moduleId);
        return ResponseEntity.noContent().build();
    }
}
