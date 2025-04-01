package com.studyhub.exception;

/**
 * Exception thrown when a requested resource is not found in the system.
 * This exception maps to a 404 Not Found HTTP status.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}