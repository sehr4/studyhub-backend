package com.studyhub.exception;

// Exception thrown for invalid or malformed data, maps to a 400 Bad Request HTTP status
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
