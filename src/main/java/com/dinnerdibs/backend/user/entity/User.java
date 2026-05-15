package com.dinnerdibs.backend.user.entity;

import com.dinnerdibs.backend.common.BaseEntity;
import com.dinnerdibs.backend.user.enums.AccountStatus;
import com.dinnerdibs.backend.user.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * A User entity that maps to a "users" table on the database via the Hibernate ORM rules and options
 */
@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_users_email", columnList = "email"),
        @Index(name = "idx_users_username", columnList = "username")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Basic User Information

    /**
     * A User's given first name
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * A User's last name
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * A User's date of birth
     */
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    // Authentication information

    /**
     * A User's email address that is unique and not previously used by the same or different user
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * A boolean flag to indicate whether a User's email address has been verified by the system
     */
    @Column(name = "email_verified")
    @Builder.Default
    private boolean emailVerified = false;

    /**
     * A User's account status based on their activity
     * Can be one of the AccountStatus enum value at a given time
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "account_status")
    private AccountStatus accountStatus = AccountStatus.PENDING;

    /**
     * A User's username that is unique and not already taken by a different user
     */
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /**
     * A User's profile page display name - acts as the primary business name
     */
    @Column(name = "display_name")
    private String displayName;

    /**
     * A User account's hashed and encrypted password value
     */
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    // User Profile Information

    /**
     * A URL value to the User's profile picture
     */
    @Column(name = "profile_picture")
    private String profilePicture;

    /**
     * A URL value to the User's banner picture
     */
    @Column(name = "banner_picture")
    private String bannerPicture;

    /**
     * A URL value to the User's Instagram page
     */
    @Column(name = "instagram_page_link")
    private String instagramPageLink;

    /**
     * A URL value to the User's Facebook page
     */
    @Column(name = "facebook_page_link")
    private String facebookPageLink;

    /**
     * A URL value to the User's YouTube page
     */
    @Column(name = "youtube_page_link")
    private String youtubePageLink;

    /**
     * A URL value to the User's TikTok page
     */
    @Column(name = "tiktok_page_link")
    private String tiktokPageLink;

    /**
     * A URL value to the User's website page
     */
    @Column(name = "website_link")
    private String websiteLink;

    // User Bio Information

    /**
     * The User's motivation story behind starting the business (why they are doing the business)
     */
    @Column(name = "bio_motivation", length = 1000)
    private String bioMotivation;

    /**
     * The User's profile description about their business (what business they are doing)
     */
    @Column(name = "bio_description", length = 2000)
    private String bioDescription;

    // User Address Information

    /**
     * A User's local street address
     */
    @Column(name = "address_local")
    private String addressLocal;

    /**
     * A boolean flag to indicate whether the provided local street address must be visible to the public or not
     */
    @Column(name = "show_address_local")
    @Builder.Default
    private boolean showAddressLocal = false;

    /**
     * A User's city address field
     */
    @Column(name = "address_city")
    private String addressCity;

    /**
     * A User's state/province address field
     */
    @Column(name = "address_state")
    private String addressState;

    /**
     * A User's address post code field
     */
    @Column(name = "address_post_code")
    private String addressPostCode;

    /**
     * A User's country address field
     */
    @Column(name = "address_country")
    private String addressCountry;

    /**
     * A User's occupation field
     */
    @Column(name = "occupation")
    private String occupation;

    // User Roles Information

    /**
     * The set of roles a User can have, that decides their ability to use the system
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(name = "role")
    private Set<Role> roles = new HashSet<>();
}
