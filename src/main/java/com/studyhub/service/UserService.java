package com.studyhub.service;

import com.studyhub.dto.UserDTO;
import com.studyhub.exception.BadRequestException;
import com.studyhub.exception.ResourceNotFoundException;
import com.studyhub.mapper.UserMapper;
import com.studyhub.model.User;
import com.studyhub.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * UserService for handling user-related business logic,
 * such as registration, retrieval, login, etc.
 */
@Service
@Transactional
public class UserService {

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
    }

    /**
     * Registers a new user in the system.
     *
     * @param userDTO the UserDTO containing the user's details
     * @return the registered user's details as a UserDTO
     * @throws BadRequestException if the username or email already exists
     */
    public UserDTO registerUser(UserDTO userDTO) {
        // Validate that the email is unique
        Optional<User> existingUserByEmail = userRepository.findUserByEmail(userDTO.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new BadRequestException("Email already exists: " + userDTO.getEmail());
        }

        // Convert DTO to entity
        User user = userMapper.toEntity(userDTO);

        // Set a temporary password and hash it
        String temporaryPassword = "temporaryPassword";
        String hashedPassword = passwordEncoder.encode(temporaryPassword);
        user.setPassword(hashedPassword);

        // Save the user to the database
        User savedUser = userRepository.save(user);

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
        // Find the user in the database or throw an exception
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
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
        // Validate that email and password are provided
        if (userDTO.getEmail() == null || userDTO.getEmail().isBlank()) {
            throw new BadRequestException("Email is required");
        }
        if (userDTO.getPassword() == null || userDTO.getPassword().isBlank()) {
            throw new BadRequestException("Password is required");
        }

        // Find the user by email
        User user = userRepository.findUserByEmail(userDTO.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid email or password"));

        // Verify the password
        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid email or password");
        }

        // Convert to DTO and return
        return userMapper.toDTO(user);
    }
}