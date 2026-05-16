package com.dinnerdibs.backend.user.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for updating User entity attributes and fields (safe attributes)
 */
@Data
public class UserUpdateRequest {
    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Size(max = 100)
    private String displayName;

    private String profilePicture;

    private String bannerPicture;

    private String bioMotivation;

    private String bioDescription;

    private String addressLocal;

    private String addressCity;

    private String addressState;

    private String addressPostCode;

    private String addressCountry;

    private String occupation;
}
