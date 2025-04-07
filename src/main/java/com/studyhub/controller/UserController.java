package com.studyhub.controller;

import com.studyhub.dto.LoginRequestDTO;
import com.studyhub.dto.UserDTO;
import com.studyhub.model.User;
import com.studyhub.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// REST controller for handling user-related API requests
@RestController
@RequestMapping("/users") // /api prefix is set in application.properties
@Tag(name = "User Controller", description = "APIs for managing user-related operations")
public class UserController {

    private final UserService userService;

    // Constructor for dependency injection.
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create a new user
    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Creates a new user with the provided details")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "User registered successfully", content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input or email already exists", content = @Content)
    })
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) {
        // Delegate to the service layer to register the user
        UserDTO registeredUser = userService.registerUser(userDTO);
        return ResponseEntity.ok(registeredUser);
    }

    // Authenticates a user by verifying their email and password
    @PostMapping("/login")
    @Operation(summary = "Log in a user", description = "Authenticates a user with email and password")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "Login successful", content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid email or password", content = @Content)
    })
    public ResponseEntity<UserDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        // Delegate to the service layer to authenticate the user
        UserDTO loggedInUser = userService.login(loginRequestDTO);
        return ResponseEntity.ok(loggedInUser);
    }

    // Retrieves a user by their ID.
    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieve a user by their unique ID")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "User found", content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        // Delegate to the service layer to retrieve the user
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }
}
