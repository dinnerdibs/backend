package com.dinnerdibs.backend.common;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * An abstract BaseEntity class with a 'createdAt' and 'updatedAt' fields with getters and setters
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    /**
     * The timestamp of the object's creation date
     */
    @CreationTimestamp
    private LocalDateTime createdAt;

    /**
     * The timestamp of the object's updated date
     */
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
