package com.studyhub.dto;

import com.studyhub.constant.RoleConstant;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// Data Transfer Object for user in API
@Data
public class UserDTO {

    private Long id;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must be a valid email address")
    private String email;

    private String firstName;
    private String lastName;

    @NotNull(message = "Role cannot be null")
    private RoleConstant role;

    private String password;

    public UserDTO() {
    }

    public UserDTO(Long id, String firstName, String lastName, String email, RoleConstant role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }
}