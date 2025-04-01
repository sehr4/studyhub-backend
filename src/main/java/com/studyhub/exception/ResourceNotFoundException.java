package com.studyhub.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception thrown when a requested resource is not found in the system.
 * This exception maps to a 404 Not Found HTTP status.
 */
public class ResourceNotFoundException extends RuntimeException {

    // Logger to print messages to the console for understanding exception handling
    private static final Logger logger = LoggerFactory.getLogger(ResourceNotFoundException.class);

    /**
     * Constructs a new ResourceNotFoundException with the specified message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public ResourceNotFoundException(String message) {
        super(message);
        logger.error("ResourceNotFoundException: {}", message);
    }
}