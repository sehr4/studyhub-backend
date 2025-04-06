package com.studyhub.controller;

import com.studyhub.dto.UserDTO;
import com.studyhub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// REST controller for handling user-related API requests
@RestController
@RequestMapping("/users") // /api prefix is set in application.properties
public class UserController {

    private final UserService userService;

    // Constructor for dependency injection.
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create a new user
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) {
        // Delegate to the service layer to register the user
        UserDTO registeredUser = userService.registerUser(userDTO);
        return ResponseEntity.ok(registeredUser);
    }

    // Authenticates a user by verifying their email and password
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) {

        // Delegate to the service layer to authenticate the user
        UserDTO loggedInUser = userService.login(userDTO);
        return ResponseEntity.ok(loggedInUser);
    }

    // Retrieves a user by their ID.
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {

        // Delegate to the service layer to retrieve the user
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }
}