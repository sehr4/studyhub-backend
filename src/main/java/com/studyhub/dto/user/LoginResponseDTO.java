package com.studyhub.dto.user;

import lombok.Data;

// Data Transfer Object, returning login response data, including a JWT token.
@Data
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
