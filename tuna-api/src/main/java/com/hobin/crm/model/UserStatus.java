package com.hobin.crm.model;

import io.swagger.annotations.ApiModel;

/**
 * Define the primary user account statuses.
 * @author xuxx
 */
@ApiModel
public enum UserStatus {
    /**
     * Indicates the user has registered successfully.
     */
    REGISTERED,
    /**
     * Indicated the user has verified through email.
     */
    VERIFIED,
    /**
     * Indicated the user has certificated with an available certificate.
     */
    CERTIFICATED,
    /**
     * Indicated the user has been disabled for some reason.
     */
    FROZEN
}
