package com.studyhub.controller;

import com.studyhub.dto.UserDTO;
import com.studyhub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling user-related API requests.
 */
@RestController
@RequestMapping("/users") // /api prefix is set in application.properties
public class UserController {

    private final UserService userService;

    /**
     * Constructor for dependency injection.
     *
     * @param userService the service for handling user-related business logic
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registers a new user.
     *
     * @param userDTO the UserDTO containing the user's details
     * @return a ResponseEntity containing the registered user's details
     */
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) {
        // Delegate to the service layer to register the user
        UserDTO registeredUser = userService.registerUser(userDTO);
        return ResponseEntity.ok(registeredUser);
    }

    /**
     * Authenticates a user by verifying their email and password.
     *
     * @param userDTO the UserDTO containing the email and password
     * @return a ResponseEntity containing the user's details if authentication is successful
     */
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) {

        // Delegate to the service layer to authenticate the user
        UserDTO loggedInUser = userService.login(userDTO);
        return ResponseEntity.ok(loggedInUser);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return a ResponseEntity containing the user's details
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {

        // Delegate to the service layer to retrieve the user
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }
}