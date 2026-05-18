package com.dinnerdibs.backend.config.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * A custom component class to manage and check for environment modes between development and production
 */
@Component
public class EnvironmentMode {
    /**
     * An attribute dependency with the application.yaml for the environment mode value
     */
    @Value("${environment.mode}")
    private String ENVIRONMENT_MODE;

    /**
     * Getter method for the environment mode from the application properties
     *
     * @return The environment mode set in application properties
     */
    public String getEnvironmentMode() {
        return this.ENVIRONMENT_MODE;
    }

    /**
     * Checks whether the current environment is in the development mode
     *
     * @return A boolean flag to indicate if the environment is development mode
     */
    public boolean isDevelopmentMode() {
        return this.ENVIRONMENT_MODE.equalsIgnoreCase("development");
    }

    /**
     * Checks whether the current environment is in the production mode
     *
     * @return A boolean flag to indicate if the environment is production mode
     */
    public boolean isProductionMode() {
        return this.ENVIRONMENT_MODE.equalsIgnoreCase("production");
    }
}
