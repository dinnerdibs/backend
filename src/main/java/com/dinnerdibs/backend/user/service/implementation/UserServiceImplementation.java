package com.dinnerdibs.backend.user.service.implementation;

import com.dinnerdibs.backend.user.dto.request.UserRegisterRequest;
import com.dinnerdibs.backend.user.dto.request.UserUpdateRequest;
import com.dinnerdibs.backend.user.dto.response.UserResponse;
import com.dinnerdibs.backend.user.entity.User;
import com.dinnerdibs.backend.user.enums.AccountStatus;
import com.dinnerdibs.backend.user.enums.Role;
import com.dinnerdibs.backend.user.repository.UserRepository;
import com.dinnerdibs.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * The implementation class for UserService interface
 * Defines the methods created by the UserService interface
 */
@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    /**
     * A UserRepository instance to make queries and modify the database tables for the User entity
     */
    private final UserRepository userRepository;

    /**
     * Implementation to the UserService method to register and persist a new user to the system
     *
     * @param request A UserRegister object with specified fields
     * @return A UserResponse instance with formatted safe fields
     */
    @Override
    public UserResponse register(UserRegisterRequest request) {
        // Check if the provided email is previously already registered
        if (this.userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Error: Email already exists");
        }

        // Check if the provided username is previously already registered
        if (this.userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Error: Username already exists");
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .email(request.getEmail())
                .username(request.getUsername())
                .displayName(request.getDisplayName())
                .passwordHash(hashPassword(request.getPassword()))
                .accountStatus(AccountStatus.PENDING)
                .emailVerified(false)
                .build();

        // Initially set the role to the defaults
        user.getRoles().add(Role.CUSTOMER);

        return this.mapToResponse(user);
    }

    /**
     * Implementation to the UserService method to get a specific user from the database through the provided ID attribute
     *
     * @param id Unique ID parameter for a given user
     * @return A UserResponse instance with formatted safe fields
     */
    @Override
    public UserResponse getUserById(UUID id) {
        return this.userRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Error: User not found!"));
    }

    /**
     * Implementation to the UserService method to get a list of all users from the database
     *
     * @return A List of UserResponse instances
     */
    @Override
    public List<UserResponse> getUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    /**
     * Implementation to the UserService method to update an existing user with the provided ID and new request information
     *
     * @param id      Unique ID parameter for a given user
     * @param request A UserUpdate object with specified (safe) fields
     * @return A UserResponse instance with formatted safe fields
     */
    @Override
    public UserResponse updateUserById(UUID id, UserUpdateRequest request) {
        // Check if an existing user maps to the provided ID attribute
        User user = this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("Error: User not found!"));

        // Check each specific User entity attribute in the UserUpdateRequest and update the user instance accordingly

        if (request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }

        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }

        if (request.getDisplayName() != null) {
            user.setDisplayName(request.getDisplayName());
        }

        if (request.getProfilePicture() != null) {
            user.setProfilePicture(request.getProfilePicture());
        }

        if (request.getBannerPicture() != null) {
            user.setBannerPicture(request.getBannerPicture());
        }

        if (request.getBioMotivation() != null) {
            user.setBioMotivation(request.getBioMotivation());
        }

        if (request.getBioDescription() != null) {
            user.setBioDescription(request.getBioDescription());
        }

        if (request.getAddressLocal() != null) {
            user.setAddressLocal(request.getAddressLocal());
        }

        if (request.getAddressCity() != null) {
            user.setAddressCity(request.getAddressCity());
        }

        if (request.getAddressState() != null) {
            user.setAddressState(request.getAddressState());
        }

        if (request.getAddressPostCode() != null) {
            user.setAddressPostCode(request.getAddressPostCode());
        }

        if (request.getAddressCountry() != null) {
            user.setAddressCountry(request.getAddressCountry());
        }

        if (request.getOccupation() != null) {
            user.setOccupation(request.getOccupation());
        }

        return this.mapToResponse(this.userRepository.save(user));
    }

    /**
     * Implementation to the UserService method to delete an existing user from the database with the provided ID parameter value
     *
     * @param id Unique ID parameter for a given user
     */
    @Override
    public void deleteUserById(UUID id) {
        this.userRepository.deleteById(id);
    }

    /**
     * Utility method to hash a given password string with bcrypt
     * NOTE: Currently does not hash the password as intended
     * TODO: Include a proper hashing technique and update the existing logic
     *
     * @param password The provided decrypted password string by the user
     * @return The encrypted (bcrypt) password hashed value
     */
    private String hashPassword(String password) {
        // TODO: Include a proper hashing technique and update the existing logic
        return "hashed_" + password;
    }

    /**
     * Maps a given User instance to the UserResponse instance with the safe fields for API responses
     *
     * @param user A User entity instance with all fields (including unsafe fields)
     * @return A UserResponse instance with necessary and safe fields from the User instance
     */
    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .email(user.getEmail())
                .username(user.getUsername())
                .displayName(user.getDisplayName())
                .accountStatus(user.getAccountStatus())
                .emailVerified(user.isEmailVerified())
                .profilePicture(user.getProfilePicture())
                .bannerPicture(user.getBannerPicture())
                .roles(user.getRoles())
                .build();
    }
}
