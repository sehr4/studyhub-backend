package com.studyhub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

// I have included a commented ResponseEntity builder under each return for my own learning

// Global exception handler
// Maps exceptions to appropriate HTTP status codes and responses
@ControllerAdvice
public class GlobalExceptionHandler {

    // ResourceNotFoundException returns a ResponseEntity with error message and 404 status
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // BadRequestException returns a ResponseEntity with error message and 400 status
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    // MethodArgumentNotValidException contains validation errors,
    // and returns a ResponseEntity with a map of field names to error messages and 400 status
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        // Create a map to store field names and their corresponding error messages
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//        return ResponseEntity.badRequest().body(errors);

    }

    // Generic exception handler for other uncaught exceptions returns generic error message and 500 status
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
//        return ResponseEntity.internalServerError().body("An unexpected error occurred");
    }
}
