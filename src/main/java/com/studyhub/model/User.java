package com.studyhub.model;

import com.studyhub.constants.RoleConstants;
import jakarta.persistence.*;
import lombok.*;

// User entity, maps to 'users' table in the db
@Data
@Entity
@Table(name = "users")
public class User {

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
    }

    public User(String firstName, String lastName, String email, RoleConstants role, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.password = password;
    }
}