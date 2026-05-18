package com.dinnerdibs.backend.common.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

/**
 * A formatted API response structure for all universal and success responses
 *
 * @param <T> A data body that is part of the response
 */
@Builder
@Data
public class ApiResponse<T> {
    /**
     * A boolean flag indicating whether the  request was a success or not
     */
    private boolean success;

    /**
     * Provided error or failure message sent back to the user
     */
    private String message;

    /**
     * The size count of the data embedded within the API response
     */
    private Integer size;

    /**
     * The actual data content of the API response
     */
    private T data;

    /**
     * Timestamp of the API response
     */
    @Builder.Default
    private Instant timestamp = Instant.now();
}
