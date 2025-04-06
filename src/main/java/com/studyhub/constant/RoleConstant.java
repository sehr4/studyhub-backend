package com.studyhub.constant;


// Enum for user roles
public enum RoleConstant {
    STUDENT,
    INSTRUCTOR,
    ADMIN;

    public static RoleConstant fromString(String role) {
        try {
            return RoleConstant.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + role);
        }
    }
}