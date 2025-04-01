package com.studyhub.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception thrown when a request contains invalid or malformed data.
 * This exception maps to a 400 Bad Request HTTP status.
 */
public class BadRequestException extends RuntimeException {

    // Logger to print messages to the console for understanding exception handling
    private static final Logger logger = LoggerFactory.getLogger(BadRequestException.class);

    /**
     * Constructs a new BadRequestException with the specified message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public BadRequestException(String message) {
        super(message);
        logger.error("BadRequestException: {}", message);
    }
}