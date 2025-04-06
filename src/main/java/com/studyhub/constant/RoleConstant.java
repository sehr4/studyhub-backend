package com.studyhub.constant;


// Enum for user roles
public enum RoleConstant {
    STUDENT,
    INSTRUCTOR,
    ADMIN;

    public static RoleConstant fromString(String role) {
        return RoleConstant.valueOf(role);
    }
}