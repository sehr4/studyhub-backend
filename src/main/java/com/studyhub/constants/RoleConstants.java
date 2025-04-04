package com.studyhub.constants;


/**
 * Enum representing the possible roles a user can have in the StudyHub application.
 */
public enum RoleConstants {
    STUDENT,
    INSTRUCTOR,
    ADMIN;

    /**
     * Converts a string to the corresponding RoleConstants enum value.
     *
     * @param role the string representation of the role (e.g., "STUDENT")
     * @return the corresponding RoleConstants enum value
     */
    public static RoleConstants fromString(String role) {
        return RoleConstants.valueOf(role);
    }
}