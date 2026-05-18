package com.dinnerdibs.backend.common.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

/**
 * A custom API response type for the error cases
 */
@Builder
@Data
public class ApiErrorResponse {
    /**
     * A boolean flag indicating whether the request was a success or not
     */
    private boolean success;

    /**
     * Provided error or failure message sent back from the request
     */
    private String message;

    /**
     * List of error messages sent back from the request
     */
    private List<String> errors;

    /**
     * Potential debug message for the developer sent back from the request
     */
    private String debugMessage;

    /**
     * Timestamp of the API response
     */
    @Builder.Default
    private Instant timestamp = Instant.now();
}
