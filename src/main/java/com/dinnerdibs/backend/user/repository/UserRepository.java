package com.dinnerdibs.backend.user.repository;

import com.dinnerdibs.backend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * UserRepository interface that serves as the repository layer between the controller/service and the database persistence
 * Extends the JpaRepository interface
 */
public interface UserRepository extends JpaRepository<User, UUID> {
    /**
     * Find a user by the provided email address
     *
     * @param email Email address value to be searched
     * @return An optional User entity if present and registered with the provided email address
     */
    Optional<User> findByEmail(String email);

    /**
     * Find a user by the provided username value
     *
     * @param username Username value to be searched
     * @return An optional User entity if present and registered with the provided username value
     */
    Optional<User> findByUsername(String username);

    /**
     * Checks if the provided email address is already registered with
     *
     * @param email Email address value to be checked
     * @return A boolean flag indicating whether the provided email address was previously used and exists
     */
    boolean existsByEmail(String email);

    /**
     * Checks if the provided username is already registered with
     *
     * @param username Username value to be checked
     * @return A boolean flag indicating whether the provided username value was previously used and exists
     */
    boolean existsByUsername(String username);
}
