package com.studyhub.dto;

import com.studyhub.constants.RoleConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Data Transfer Object for representing a user in API requests and responses.
 */
@Getter
@Setter
public class UserDTO {

    private static final Logger logger = LoggerFactory.getLogger(UserDTO.class);

    private Long id;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must be a valid email address")
    private String email;

    private String firstName;

    private String lastName;

    @NotNull(message = "Role cannot be null")
    private RoleConstants role;

    private String password;

    public UserDTO() {
        logger.debug("Creating new UserDTO instance");
    }

    public UserDTO(Long id, String firstName, String lastName, String email, RoleConstants role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        logger.info("Created UserDTO instance: id={}, email={}, role={}", id, email, role);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", password='" + (password != null ? "[REDACTED]" : null) + '\'' +
                '}';
    }
}