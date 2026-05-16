package com.dinnerdibs.backend.user.service;

import com.dinnerdibs.backend.user.dto.request.UserRegisterRequest;
import com.dinnerdibs.backend.user.dto.request.UserUpdateRequest;
import com.dinnerdibs.backend.user.dto.response.UserResponse;

import java.util.List;
import java.util.UUID;

/**
 * The UserService interface specific to the User entity that defines required methods for its implementation
 */
public interface UserService {
    /**
     * UserService method to register and persist a new user to the system
     *
     * @param request A UserRegister object with specified fields
     * @return A UserResponse instance
     */
    UserResponse register(UserRegisterRequest request);

    /**
     * UserService method to get a specific user from the database through the provided ID attribute
     *
     * @param id Unique ID parameter for a given user
     * @return A UserResponse instance
     */
    UserResponse getUserById(UUID id);

    /**
     * UserService method to get a list of all users from the database
     *
     * @return A List of UserResponse instances
     */
    List<UserResponse> getUsers();

    /**
     * UserService method to update an existing user with the provided ID and new request information
     *
     * @param id      Unique ID parameter for a given user
     * @param request A UserUpdate object with specified (safe) fields
     * @return A UserResponse instance
     */
    UserResponse updateUserById(UUID id, UserUpdateRequest request);

    /**
     * UserService method to delete an existing user from the database with the provided ID parameter value
     *
     * @param id Unique ID parameter for a given user
     */
    void deleteUserById(UUID id);
}
