package com.studyhub.service;

import com.studyhub.dto.UserDTO;
import com.studyhub.exception.BadRequestException;
import com.studyhub.exception.ResourceNotFoundException;
import com.studyhub.mapper.UserMapper;
import com.studyhub.model.User;
import com.studyhub.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service class for handling user-related business logic, such as registration, retrieval, and login.
 */
@Service
@Transactional
public class UserService {

    // Logger to print messages to the console for understanding service operations
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Constructor for dependency injection.
     *
     * @param userRepository  the repository for accessing user data
     * @param userMapper      the mapper for converting between User entities and DTOs
     * @param passwordEncoder the encoder for hashing passwords
     */
    public UserService(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        logger.info("UserService initialized");
    }

    /**
     * Registers a new user in the system.
     *
     * @param userDTO the UserDTO containing the user's details
     * @return the registered user's details as a UserDTO
     * @throws BadRequestException if the username or email already exists
     */
    public UserDTO registerUser(UserDTO userDTO) {
        logger.debug("Registering new user: {}", userDTO);

        // Validate that the username is unique
//        Optional<User> existingUserByUsername = userRepository.findByUsername(userDTO.getUsername());
//        if (existingUserByUsername.isPresent()) {
//            logger.error("Username already exists: {}", userDTO.getUsername());
//            throw new BadRequestException("Username already exists: " + userDTO.getUsername());
//        }

        // Validate that the email is unique
        Optional<User> existingUserByEmail = userRepository.findByEmail(userDTO.getEmail());
        if (existingUserByEmail.isPresent()) {
            logger.error("Email already exists: {}", userDTO.getEmail());
            throw new BadRequestException("Email already exists: " + userDTO.getEmail());
        }

        // Convert DTO to entity
        User user = userMapper.toEntity(userDTO);

        // Set a temporary password and hash it
        String temporaryPassword = "temporaryPassword";
        String hashedPassword = passwordEncoder.encode(temporaryPassword);
        user.setPassword(hashedPassword);
        logger.debug("Hashed temporary password for user {}: {}", userDTO.getEmail(), hashedPassword);

        // Save the user to the database
        User savedUser = userRepository.save(user);
        logger.info("User registered successfully: id={}, email={}", savedUser.getId(), savedUser.getEmail());

        // Convert the saved entity back to a DTO and return
        return userMapper.toDTO(savedUser);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the UserDTO containing the user's details
     * @throws ResourceNotFoundException if the user is not found
     */
    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        logger.debug("Retrieving user by ID: {}", id);

        // Find the user in the database
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            logger.error("User not found with ID: {}", id);
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }

        User user = userOptional.get();
        logger.info("Retrieved user: id={}, email={}", user.getId(), user.getEmail());

        // Convert to DTO and return
        return userMapper.toDTO(user);
    }

    /**
     * Authenticates a user by verifying their email and password.
     *
     * @param userDTO the UserDTO containing the email and password
     * @return the UserDTO containing the user's details if authentication is successful
     * @throws BadRequestException if the email or password is invalid
     */
    @Transactional(readOnly = true)
    public UserDTO login(UserDTO userDTO) {
        logger.debug("Attempting to log in user with email: {}", userDTO.getEmail());

        // Validate that email and password are provided
        if (userDTO.getEmail() == null || userDTO.getEmail().isBlank()) {
            logger.error("Email is required for login");
            throw new BadRequestException("Email is required");
        }
        if (userDTO.getPassword() == null || userDTO.getPassword().isBlank()) {
            logger.error("Password is required for login");
            throw new BadRequestException("Password is required");
        }

        // Find the user by username
        Optional<User> userOptional = userRepository.findByEmail(userDTO.getEmail());
        if (userOptional.isEmpty()) {
            logger.error("Invalid email: {}", userDTO.getEmail());
            throw new BadRequestException("Invalid email or password");
        }

        User user = userOptional.get();

        // Verify the password
        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            logger.error("Invalid password for email: {}", userDTO.getEmail());
            throw new BadRequestException("Invalid email or password");
        }

        logger.info("User logged in successfully: id={}, email={}", user.getId(), user.getEmail());

        // Convert to DTO and return
        return userMapper.toDTO(user);
    }
}