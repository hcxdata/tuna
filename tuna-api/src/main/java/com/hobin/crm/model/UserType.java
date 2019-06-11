package com.hobin.crm.model;

import io.swagger.annotations.ApiModel;

/**
 * Defines the primary user types.
 * 
 * @author xuxx
 */
@ApiModel
public enum UserType {
	/**
	 * Indicates the user is a web user.
	 */
	WEB_USER,
	/**
	 * Indicates the user is a mobile user.
	 */
	MOBILE_USER
}
