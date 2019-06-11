package com.hobin.crm.service.exception;

/*
 * @author xuxx
 */
public class EncryptException extends ServiceException {

	private static final long serialVersionUID = -5427235736864198558L;

	public EncryptException() {
        super("Encrypt data failed", ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
