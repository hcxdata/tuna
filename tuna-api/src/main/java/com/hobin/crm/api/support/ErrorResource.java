package com.hobin.crm.api.support;

/**
 * Represents an error response.
 * @author xuxx
 */
public class ErrorResource {
    /**
     * Error code.
     */
    public int code;
    /**
     * Error message.
     */
    public String message;

    public ErrorResource() {

    }

    public ErrorResource(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
