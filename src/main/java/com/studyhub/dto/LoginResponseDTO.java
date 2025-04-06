package com.studyhub.dto;

import lombok.Getter;
import lombok.Setter;

// Data Transfer Object, returning login response data, including a JWT token.
@Getter
@Setter
public class LoginResponseDTO {

    private String token;
    private UserDTO user;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String token, UserDTO user) {
        this.token = token;
        this.user = user;
    }
}