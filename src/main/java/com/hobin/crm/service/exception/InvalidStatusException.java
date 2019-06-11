package com.hobin.crm.service.exception;

/**
 * Throws when the resource requesting to create has already existed. The client
 * should change the request data and try again.
 * 
 * @author xuxx
 */
public class InvalidStatusException extends ServiceException {
	private static final long serialVersionUID = 1L;
	private static final String messagePattern = "Resource status change with '%s' -> '%s' has invalid.";
	private static final String messagePattern2 = "Resources current status '%s' cannot be changed. ";

	public InvalidStatusException(String current, String changed) {
		super(String.format(messagePattern, current, changed), ErrorCode.INVALID_STATUS);
	}

	public InvalidStatusException(String current) {
		super(String.format(messagePattern2, current), ErrorCode.INVALID_STATUS);
	}
}
