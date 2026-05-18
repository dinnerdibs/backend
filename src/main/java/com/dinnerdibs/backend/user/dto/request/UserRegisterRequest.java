package com.dinnerdibs.backend.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for User register operation request
 */
@Data
public class UserRegisterRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Past
    private LocalDate dateOfBirth;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 4, max = 30)
    private String username;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;

    @NotBlank
    @Size(max = 100)
    private String displayName;
}
