package com.studyhub.dto;

import com.studyhub.constant.RoleConstant;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserUpdateDTO {

    private Long id;

    @Email(message = "Email must be a valid email address")
    private String email;

    private String firstName;
    private String lastName;
    private RoleConstant role;
    private String password;
}