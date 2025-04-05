package com.studyhub.constant;


/**
 * Enum representing the possible roles a user can have in the StudyHub application.
 */
public enum RoleConstant {
    STUDENT,
    INSTRUCTOR,
    ADMIN;

    /**
     * Converts a string to the corresponding RoleConstant enum value.
     *
     * @param role the string representation of the role (e.g., "STUDENT")
     * @return the corresponding RoleConstant enum value
     */
    public static RoleConstant fromString(String role) {
        return RoleConstant.valueOf(role);
    }
}