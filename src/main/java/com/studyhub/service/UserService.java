package com.studyhub.service;

import com.studyhub.dto.LoginRequestDTO;
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

// UserService component, handling user-related business logic
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO registerUser(UserDTO userDTO) {
        // Validate that the email is unique
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists: " + userDTO.getEmail());
        }

        // Convert DTO to entity for registration
        User user = userMapper.toEntity(userDTO);

        // Use the provided password if present, otherwise set a temporary one
        String password = userDTO.getPassword() != null ? userDTO.getPassword() : "temporaryPassword";
        user.setPassword(passwordEncoder.encode(password));

        // Save the user to the database
        User savedUser = userRepository.save(user);

        // Convert the saved entity back to a DTO and return
        return userMapper.toDTO(savedUser);
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        // Find the user in the database or throw an exception
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        // Convert to DTO and return
        return userMapper.toDTO(user);
    }

    @Transactional(readOnly = true)
    public UserDTO login(LoginRequestDTO loginRequestDTO) {
        // Find the user by email
        User user = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid email or password"));

        // Verify the password
        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid email or password");
        }

        // Convert to DTO and return
        return userMapper.toDTO(user);
    }
}