package com.studyhub.exception;

/**
 * Exception thrown when a request contains invalid or malformed data.
 * This exception maps to a 400 Bad Request HTTP status.
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}