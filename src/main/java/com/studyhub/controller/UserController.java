package com.studyhub.controller;

import com.studyhub.dto.UserDTO;
import com.studyhub.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling user-related API requests.
 */
@RestController
@RequestMapping("/users") // /api prefix is implemented in the application.properties file
public class UserController {

    // Logger to print messages to the console for understanding controller operations
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    /**
     * Constructor for dependency injection.
     *
     * @param userService the service for handling user-related business logic
     */
    public UserController(UserService userService) {
        this.userService = userService;
        logger.info("UserController initialized");
    }

    /**
     * Registers a new user.
     *
     * @param userDTO the UserDTO containing the user's details
     * @return a ResponseEntity containing the registered user's details
     */
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) {
        logger.debug("Received request to register user: {}", userDTO);

        // Delegate to the service layer to register the user
        UserDTO registeredUser = userService.registerUser(userDTO);

        logger.info("User registered successfully: id={}, email={}", registeredUser.getId(), registeredUser.getEmail());
        return ResponseEntity.ok(registeredUser);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return a ResponseEntity containing the user's details
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        logger.debug("Received request to get user by ID: {}", id);

        // Delegate to the service layer to retrieve the user
        UserDTO userDTO = userService.getUserById(id);

        logger.info("Returning user: id={}, email={}", userDTO.getId(), userDTO.getEmail());
        return ResponseEntity.ok(userDTO);
    }

    /**
     * Authenticates a user by verifying their email and password.
     *
     * @param userDTO the UserDTO containing the email and password
     * @return a ResponseEntity containing the user's details if authentication is successful
     */
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) {
        logger.debug("Received login request for email: {}", userDTO.getEmail());

        // Delegate to the service layer to authenticate the user
        UserDTO loggedInUser = userService.login(userDTO);

        logger.info("User logged in successfully: id={}, email={}", loggedInUser.getId(), loggedInUser.getEmail());
        return ResponseEntity.ok(loggedInUser);
    }
}