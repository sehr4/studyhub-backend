package com.studyhub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for handling application-specific exceptions.
 * Maps exceptions to appropriate HTTP status codes and responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles ResourceNotFoundException and returns a 404 Not Found response.
     *
     * @param ex the ResourceNotFoundException to handle
     * @return a ResponseEntity containing the error message and 404 status
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//        The same with ResponseEntity builder:
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles BadRequestException and returns a 400 Bad Request response.
     *
     * @param ex the BadRequestException to handle
     * @return a ResponseEntity containing the error message and 400 status
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//        The same with ResponseEntity builder:
//        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    /**
     * Handles validation errors and returns a 400 Bad Request response with a map of field errors.
     *
     * @param ex the MethodArgumentNotValidException containing validation errors
     * @return a ResponseEntity containing a map of field names to error messages and 400 status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        // Create a map to store field names and their corresponding error messages
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//        The same with ResponseEntity builder:
//        return ResponseEntity.badRequest().body(errors);

    }

    /**
     * Handles all other uncaught exceptions and returns a 500 Internal Server Error response.
     *
     * @param ex the Exception to handle
     * @return a ResponseEntity containing a generic error message and 500 status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
//        The same with ResponseEntity builder:
//        return ResponseEntity.internalServerError().body("An unexpected error occurred");

    }
}