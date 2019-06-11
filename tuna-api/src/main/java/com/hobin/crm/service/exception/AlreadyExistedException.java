package com.hobin.crm.service.exception;

/**
 * Throws when the resource requesting to create has already existed.
 * The client should change the request data and try again.
 * @author xuxx
 */
public class AlreadyExistedException extends ServiceException {
    private static final long serialVersionUID = 891906524719137915L;
    private static final String messagePattern = "Resource with '%s'='%s' has already existed.";

    public AlreadyExistedException(String property, String value) {
        super(String.format(messagePattern, property, value), ErrorCode.ALREADY_EXISTED);
    }
}
