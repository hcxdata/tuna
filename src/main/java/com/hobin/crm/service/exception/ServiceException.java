package com.hobin.crm.service.exception;

/**
 * Define the common service exception informations.
 * @author xuxx
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -7671681338076071825L;
    /**
     * The exception error code.
     */
    public ErrorCode errorCode;

    public ServiceException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
