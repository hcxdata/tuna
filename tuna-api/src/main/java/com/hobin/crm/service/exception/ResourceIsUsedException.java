package com.hobin.crm.service.exception;

/**
 * Represents the requested resource is not found.
 * 
 * @author sam
 */
public class ResourceIsUsedException extends ServiceException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8873311825934104839L;

	public ResourceIsUsedException(String resource) {
		super(String.format("Resource '%s' is used", resource), ErrorCode.RESOURCE_USED);
	}
}
