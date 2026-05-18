package com.dinnerdibs.backend.common.exception;

/**
 * A custom Exception class designed to handle when a particular immutable resource that already exists is being changed
 */
public class DuplicateResourceException extends RuntimeException {
    /**
     * Primary constructor for the DuplicateResourceException class
     *
     * @param message The error message for the exception
     */
    public DuplicateResourceException(String message) {
        super(message);
    }
}
