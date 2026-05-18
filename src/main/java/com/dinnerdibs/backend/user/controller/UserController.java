package com.dinnerdibs.backend.user.controller;

import com.dinnerdibs.backend.common.response.ApiResponse;
import com.dinnerdibs.backend.config.api.ApiPaths;
import com.dinnerdibs.backend.user.dto.request.UserRegisterRequest;
import com.dinnerdibs.backend.user.dto.request.UserUpdateRequest;
import com.dinnerdibs.backend.user.dto.response.UserResponse;
import com.dinnerdibs.backend.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * UserController class for the User entity
 * The Controller class servers as the layer between the incoming request (from the specified path) and the Service layer
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.USERS)
public class UserController {
    /**
     * A UserService instance user the methods defined in its implementation
     */
    private final UserService userService;

    /**
     * Controller method to register a new user to the system
     * Path: /api/v/users/register (POST)
     *
     * @param request A UserRegisterRequest instance with provided fields
     * @return A UserResponse instance with formatted safe fields in the ApiResponse structure
     */
    @PostMapping(path = "/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(@Valid @RequestBody UserRegisterRequest request) {
        UserResponse registeredUser = this.userService.register(request);

        ApiResponse<UserResponse> response = ApiResponse.<UserResponse>builder()
                .success(true)
                .message("User registered successfully")
                .data(registeredUser)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Controller method to get a specific user by the ID attribute
     * Path: /api/v/users/{id} (GET)
     *
     * @param id A unique ID attribute for a specific user
     * @return A UserResponse instance with formatted safe fields in the ApiResponse structure
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable UUID id) {
        UserResponse user = this.userService.getUserById(id);

        ApiResponse<UserResponse> response = ApiResponse.<UserResponse>builder()
                .success(true)
                .message("User retrieved successfully")
                .data(user)
                .build();

        return ResponseEntity.ok(response);
    }

    /**
     * Controller method to get a list of all users
     * Path: /api/v/users (GET)
     *
     * @return A List of UserResponse instances with formatted safe fields in the ApiResponse structure
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getUsers() {
        List<UserResponse> users = this.userService.getUsers();

        ApiResponse<List<UserResponse>> response = ApiResponse.<List<UserResponse>>builder()
                .success(true)
                .message("Users retrieved successfully")
                .size(users.size())
                .data(users)
                .build();

        return ResponseEntity.ok(response);
    }

    /**
     * Controller method to update an existing user with the specified ID attribute and provided information
     * Path: /api/v/users/{id} (PATCH)
     *
     * @param id      A unique ID attribute for a specific user
     * @param request A UserUpdateRequest instance with some provided fields
     * @return A UserResponse instance with formatted safe fields in the ApiResponse structure
     */
    @PatchMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUserById(@PathVariable UUID id, @Valid @RequestBody UserUpdateRequest request) {
        UserResponse updatedUser = this.userService.updateUserById(id, request);

        ApiResponse<UserResponse> response = ApiResponse.<UserResponse>builder()
                .success(true)
                .message("User updated successfully")
                .data(updatedUser)
                .build();

        return ResponseEntity.ok(response);
    }

    /**
     * Controller method to delete an existing user in the system with the provided ID attribute
     * Path: /api/v/users/{id} (DELETE)
     *
     * @param id A unique ID attribute for a specified user
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUserById(@PathVariable UUID id) {
        this.userService.deleteUserById(id);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .message("User deleted successfully")
                .build();

        return ResponseEntity.ok(response);
    }
}
