package com.dinnerdibs.backend.config;

/**
 * A helper class to centralize the backend's API versioning structure and route endpoints with easy management
 */
public class ApiPaths {
    /**
     * Private constructor to avoid creating object instances of the ApiPaths class
     */
    private ApiPaths() {
    }

    /**
     * Static field for the first API version
     */
    private static final String API_VERSION_1 = "v1";

    /**
     * Static field to set the current stable API version for the system
     */
    public static final String CURRENT_API_VERSION = API_VERSION_1;

    /**
     * Static field for the standard API path such as /api/v1
     */
    public static final String API_VERSION = "/api/" + CURRENT_API_VERSION;

    /**
     * Static field for the users API route with versioning
     */
    public static final String USERS = API_VERSION + "/users";
}
