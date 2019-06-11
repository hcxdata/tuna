package com.hobin.crm.service.exception;

/**
 * Represents the requested resource is not found.
 * @author sam
 */
public class ResourceNotFoundException extends ServiceException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8873311825934104839L;

	public ResourceNotFoundException(String resource, ErrorCode errorCode) {
        super(String.format("Resource '%s' not found", resource), errorCode);
    }
}
