package com.studyhub.model;

import com.studyhub.constants.RoleConstants;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entity representing a user in the StudyHub application.
 * Maps to the 'users' table in the database.
 */

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    private static final Logger logger = LoggerFactory.getLogger(User.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleConstants role;

    @Column(name = "password", nullable = false)
    private String password;

    public User() {
        logger.debug("Creating new User instance");
    }

    public User(String firstName, String lastName, String email,  RoleConstants role, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.password = password;
        logger.info("Created User instance: email={}, role={}",  email, role);
    }

    /**
     * Returns a string representation of the User object.
     *
     * @return a string containing the user's details
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}