package com.dinnerdibs.backend.common.exception;

/**
 * A custom Exception class designed to handle when a particular resource does not exist or is not found
 */
public class ResourceNotFoundException extends RuntimeException {
    /**
     * Primary constructor for the ResourceNotFoundException class
     *
     * @param message The error message for the exception
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
