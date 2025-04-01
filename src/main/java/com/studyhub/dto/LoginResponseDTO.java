package com.studyhub.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for returning login response data, including a JWT token.
 */
@Getter
@Setter
public class LoginResponseDTO {

    private String token;
    private UserDTO user;

    public LoginResponseDTO() {
        // Default constructor
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
    }
}