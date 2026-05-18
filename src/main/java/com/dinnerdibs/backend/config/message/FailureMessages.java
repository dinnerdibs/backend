package com.dinnerdibs.backend.config.message;

/**
 * A utility class managing the failure messages/responses for the system
 */
public class FailureMessages {
    /**
     * Custom failure message when a provided attribute already exists on the system
     *
     * @param attribute The attribute that needs to be checked
     * @return A string with the error message
     */
    public static String ALREADY_EXISTS(String attribute) {
        return "Error: The provided attribute already exists on the system (" + attribute + ").";
    }

    /**
     * Custom failure message when a provided attribute does not exist on the system and is being retrieved
     *
     * @param attribute The attribute that needs to be checked
     * @return A string with the error message
     */
    public static String NOT_FOUND(String attribute) {
        return "Error: The entity with the attribute (" + attribute + ") was not found in the system";
    }
}
