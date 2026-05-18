package com.dinnerdibs.backend.user.controller;

import com.dinnerdibs.backend.config.api.ApiPaths;
import com.dinnerdibs.backend.user.dto.request.UserRegisterRequest;
import com.dinnerdibs.backend.user.dto.request.UserUpdateRequest;
import com.dinnerdibs.backend.user.dto.response.UserResponse;
import com.dinnerdibs.backend.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
     * @return A UserResponse instance with formatted safe fields
     */
    @PostMapping(path = "/register")
    public UserResponse register(@Valid @RequestBody UserRegisterRequest request) {
        return this.userService.register(request);
    }

    /**
     * Controller method to get a specific user by the ID attribute
     * Path: /api/v/users/{id} (GET)
     *
     * @param id A unique ID attribute for a specific user
     * @return A UserResponse instance with formatted safe fields
     */
    @GetMapping(path = "/{id}")
    public UserResponse getUserById(@PathVariable UUID id) {
        return this.userService.getUserById(id);
    }

    /**
     * Controller method to get a list of all users
     * Path: /api/v/users (GET)
     *
     * @return A List of UserResponse instances with formatted safe fields
     */
    @GetMapping
    public List<UserResponse> getUsers() {
        return this.userService.getUsers();
    }

    /**
     * Controller method to update an existing user with the specified ID attribute and provided information
     * Path: /api/v/users/{id} (PATCH)
     *
     * @param id      A unique ID attribute for a specific user
     * @param request A UserUpdateRequest instance with some provided fields
     * @return A UserResponse instance with formatted safe fields
     */
    @PatchMapping(path = "/{id}")
    public UserResponse updateUserById(@PathVariable UUID id, @Valid @RequestBody UserUpdateRequest request) {
        return this.userService.updateUserById(id, request);
    }

    /**
     * Controller method to delete an existing user in the system with the provided ID attribute
     * Path: /api/v/users/{id} (DELETE)
     *
     * @param id A unique ID attribute for a specified user
     */
    @DeleteMapping(path = "/{id}")
    public void deleteUserById(@PathVariable UUID id) {
        this.userService.deleteUserById(id);
    }
}
