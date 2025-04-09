package com.studyhub.exception;

// Exception thrown when resource is not found in the system, maps to a 404 Not Found HTTP status
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
