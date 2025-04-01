package com.studyhub.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for handling login requests.
 */
@Getter
@Setter
public class LoginRequestDTO {

    private String email;
    private String password;

    public LoginRequestDTO() {
        // Default constructor
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
    }
}