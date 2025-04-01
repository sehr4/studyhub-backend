package com.studyhub.dto;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Data Transfer Object for handling login requests.
 */
@Getter
@Setter
public class LoginRequestDTO {

    // Logger to print messages to the console for understanding DTO usage
    private static final Logger logger = LoggerFactory.getLogger(LoginRequestDTO.class);

    private String email;
    private String password;

    /**
     * Default constructor.
     */
    public LoginRequestDTO() {
        logger.debug("Creating new LoginRequestDTO instance");
    }

    /**
     * Constructor for creating a LoginRequestDTO with email and password.
     *
     * @param email the email of the user attempting to log in
     * @param password the password of the user
     */
    public LoginRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
        logger.info("Created LoginRequestDTO instance: email={}", email);
    }
}