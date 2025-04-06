package com.studyhub.dto;

import lombok.Getter;
import lombok.Setter;

// Data Transfer Object for login requests
@Getter
@Setter
public class LoginRequestDTO {

    private String email;
    private String password;

    public LoginRequestDTO() {
    }

    public LoginRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}