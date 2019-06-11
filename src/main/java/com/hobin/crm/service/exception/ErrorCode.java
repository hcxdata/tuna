package com.hobin.crm.service.exception;

/**
 * Defines the common services error codes.
 * @author xuxx
 */
public enum ErrorCode {
    // Common service exceptions
    BAD_REQUEST(400, 400),
    UNAUTHORIZED(401, 401),
    FORBIDDEN(403, 403),
    NOT_FOUND(404, 404),
    CONFLICT(409, 409),
    INTERNAL_SERVER_ERROR(500, 500),

    NO_CONTENT(1000,204),
    
    ALREADY_EXISTED(1100, 409),
    MISSING_PROPERTY(1101, 400),
    UNSUPPORTED_ACTION(1102, 404),
    CONFLICT_ACTION(1103, 409),
    NOT_BOUND(1104,400),
    OUT_OF_LIMIT(1105,409),
    INVALID_VALUE(1106,409),
	INVALID_STATUS(1107,409),
	RESOURCE_USED(1108,409);
	
    /**
     * The service error code.
     */
    public int code;
    /**
     * The HTTP response status code for the service error.
     */
    public int httpStatusCode;

    ErrorCode(int code, int httpStatusCode) {
        this.code = code;
        this.httpStatusCode = httpStatusCode;
    }
}
