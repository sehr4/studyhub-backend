package com.studyhub.dto;

import com.studyhub.constant.RoleConstant;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

// Data Transfer Object for updating user in API
// 'Relaxed' validation that allow flexible updating
@Data
public class UserUpdateDTO {

    private Long id;

    @Email(message = "Email must be a valid email address")
    @Size(min = 5, max = 100, message = "Email must be between 5 and 100 characters")
    private String email;

    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    private RoleConstant role;

    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;
}
