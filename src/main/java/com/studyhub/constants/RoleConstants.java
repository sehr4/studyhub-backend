package com.studyhub.constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Enum representing the possible roles a user can have in the StudyHub application.
 */
public enum RoleConstants {
    ROLE_STUDENT,
    ROLE_INSTRUCTOR,
    ROLE_ADMIN;

    // Logger to print messages to the console
    private static final Logger logger = LoggerFactory.getLogger(RoleConstants.class);

    /**
     * Converts a string to the corresponding RoleConstants enum value.
     * This method is useful for deserialization or parsing roles from external sources.
     *
     * @param role the string representation of the role (e.g., "ROLE_STUDENT")
     * @return the corresponding RoleConstants enum value
     * @throws IllegalArgumentException if the role string does not match any enum value
     */
    public static RoleConstants fromString(String role) {
        logger.debug("Converting string to RoleConstants: {}", role);
        try {
            RoleConstants roleConstant = RoleConstants.valueOf(role);
            logger.info("Successfully converted string to RoleConstants: {}", roleConstant);
            return roleConstant;
        } catch (IllegalArgumentException e) {
            logger.error("Invalid role string: {}. Valid roles are: ROLE_STUDENT, ROLE_INSTRUCTOR, ROLE_ADMIN", role);
            throw new IllegalArgumentException("Invalid role: " + role, e);
        }
    }
}