package com.hobin.crm.service.exception;

/**
 * Throws when the resource requesting to create has already existed. The client
 * should change the request data and try again.
 * 
 * @author xuxx
 */
public class InvalidValueException extends ServiceException {
	private static final long serialVersionUID = 1L;
	private static final String messagePattern = "Resource with '%s'='%s' has invalid.";

	public InvalidValueException(String property, String value) {
		super(String.format(messagePattern, property, value), ErrorCode.INVALID_VALUE);
	}
}
