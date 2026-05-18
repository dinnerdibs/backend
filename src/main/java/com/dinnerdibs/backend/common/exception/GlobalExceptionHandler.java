package com.dinnerdibs.backend.common.exception;

import com.dinnerdibs.backend.common.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * A global exception handler for the system incorporating default and custom exceptions with HTTP status.
 * Includes custom response structure with the {@code ApiErrorResponse} class.
 * Uses the {@code @RestControllerAdvice} annotation to serve as the primary and global exception handler at a centralized place
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Exception handler for ResourceNotFoundException case - when a particular resource does not exist in the system
     *
     * @param exception A ResourceNotFoundException instance
     * @return A ResponseEntity instance with the custom ApiErrorResponse structure formatted with HTTP status and error messages
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(ResourceNotFoundException exception) {
        ApiErrorResponse response = ApiErrorResponse.builder()
                .success(false)
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /**
     * Exception handler for DuplicateResourceException case - when an immutable resource that already exists is being updated
     *
     * @param exception A DuplicateResourceException instance
     * @return A ResponseEntity instance with the custom ApiErrorResponse structure formatted with HTTP status and error messages
     */
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiErrorResponse> handleDuplicate(DuplicateResourceException exception) {
        ApiErrorResponse response = ApiErrorResponse.builder()
                .success(false)
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    /**
     * Exception handler for MethodArgumentNotValidException - when the provided arguments are validated to be of incorrect format or type
     *
     * @param exception A MethodArgumentNotValidException instance
     * @return A ResponseEntity instance with the custom ApiErrorResponse structure formatted with HTTP status and error messages
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException exception) {
        // Generate a list of errors (for log)
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ApiErrorResponse response = ApiErrorResponse.builder()
                .success(false)
                .message("Error: Validation failed")
                .errors(errors)
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Exception handler for the default Exception - for all standard exceptions
     *
     * @param exception A regular Exception class instance
     * @return A ResponseEntity instance with the custom ApiErrorResponse structure formatted with HTTP status and error messages
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneric(Exception exception) {
        ApiErrorResponse response = ApiErrorResponse.builder()
                .success(false)
                .message("Error: Internal server error")
                .debugMessage(exception.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
