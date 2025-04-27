package com.studyhub.controller;

import com.studyhub.dto.ModuleCreateDTO;
import com.studyhub.dto.ModuleDTO;
import com.studyhub.service.ModuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
