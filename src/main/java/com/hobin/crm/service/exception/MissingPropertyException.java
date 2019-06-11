package com.hobin.crm.service.exception;

import org.springframework.util.StringUtils;

/**
 * Represents the request missing one or more mandatory properties.
 * @author xuxx
 */
public class MissingPropertyException extends ServiceException {
    private static final long serialVersionUID = -3099479354169820710L;
    private static final String messagePattern = "One or more properties in '%s' are missing.";

    public MissingPropertyException(String[] properties) {
        super(String.format(messagePattern, StringUtils.arrayToCommaDelimitedString(properties)), ErrorCode.MISSING_PROPERTY);
    }
}
