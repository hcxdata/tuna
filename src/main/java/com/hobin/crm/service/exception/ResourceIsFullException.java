package com.hobin.crm.service.exception;

/**
 * Represents the requested resource is not found.
 * 
 * @author sam
 */
public class ResourceIsFullException extends ServiceException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8873311825934104839L;

	public ResourceIsFullException(String resource) {
		super(String.format("Resource '%s' is full", resource), ErrorCode.OUT_OF_LIMIT);
	}
}
