package com.dinnerdibs.backend.user.dto.response;

import com.dinnerdibs.backend.user.enums.AccountStatus;
import com.dinnerdibs.backend.user.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) for User entity as a response (only includes the "safe" fields)
 */
@Data
@Builder
public class UserResponse {
    private UUID id;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String email;

    private String username;

    private String displayName;

    private AccountStatus accountStatus;

    private boolean emailVerified;

    private String profilePicture;

    private String bannerPicture;

    private Set<Role> roles;
}
