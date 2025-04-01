package com.studyhub.dto;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Data Transfer Object for returning login response data, including a JWT token.
 */
@Getter
@Setter
public class LoginResponseDTO {

    // Logger to print messages to the console for understanding DTO usage
    private static final Logger logger = LoggerFactory.getLogger(LoginResponseDTO.class);

    private String token;
    private UserDTO user;

    /**
     * Default constructor.
     */
    public LoginResponseDTO() {
        logger.debug("Creating new LoginResponseDTO instance");
    }

    /**
     * Constructor for creating a LoginResponseDTO with a token and user details.
     *
     * @param token the JWT token generated upon successful login
     * @param user  the user details
     */
    public LoginResponseDTO(String token, UserDTO user) {
        this.token = token;
        this.user = user;
        logger.info("Created LoginResponseDTO instance: token={}, user={}", token, user);
    }
}