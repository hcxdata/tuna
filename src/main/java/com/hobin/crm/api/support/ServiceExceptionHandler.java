package com.hobin.crm.api.support;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hobin.crm.service.exception.ErrorCode;
import com.hobin.crm.service.exception.ServiceException;
import com.hobin.crm.util.JsonMapper;

/**
 * Exception handler handles exceptions from service layer.
 * @author xuxx
 */
@ControllerAdvice(annotations = {RestController.class})
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public static final String HTTP_X_FORWARDED_FOR = "X-Forwarded-For";
    private final JsonMapper jsonMapper = new JsonMapper();

    @ExceptionHandler(ServiceException.class)
    public final ResponseEntity<ErrorResource> handleServiceException(ServiceException ex, HttpServletRequest request) {
        logDebug(ex, request);
        ErrorResource resource = new ErrorResource(ex.errorCode.code, ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<ErrorResource>(resource, headers, HttpStatus.valueOf(ex.errorCode.httpStatusCode));
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResource> handleGeneralException(Exception ex, HttpServletRequest request) {
        logError(ex, request);
        ErrorResource resource = new ErrorResource(ErrorCode.INTERNAL_SERVER_ERROR.code,
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<ErrorResource>(resource, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void logError(Exception ex, HttpServletRequest request) {
        Map<String, String> errorInfo = new HashMap<>();
        errorInfo.put("message", ex.getMessage());
        if (ex instanceof ServiceException) {
            errorInfo.put("code", Integer.toString(((ServiceException)ex).errorCode.code));
        }
        errorInfo.put("from", request.getRemoteAddr());
        errorInfo.put("method", request.getMethod());
        String query = request.getQueryString();
        errorInfo.put("path", query != null ? request.getRequestURI() + "?" + query : request.getRequestURI());
        errorInfo.put("forwardedFor", request.getHeader(HTTP_X_FORWARDED_FOR));
        logger.error("Service throws some exception, details: {}", jsonMapper.toJson(errorInfo), ex);
    }

    private void logDebug(ServiceException ex, HttpServletRequest request) {
        if (logger.isDebugEnabled()) {
            Map<String, String> info = new HashMap<>();
            info.put("message", ex.getMessage());
            info.put("code", Integer.toString(ex.errorCode.code));
            info.put("method", request.getMethod());
            info.put("from", request.getRemoteAddr());
            String query = request.getQueryString();
            info.put("path", query != null ? request.getRequestURI() + "?" + query : request.getRequestURI());
            info.put("forwardedFor", request.getHeader(HTTP_X_FORWARDED_FOR));
            logger.debug("Service exception, details: {}", jsonMapper.toJson(info));
        }
    }
}
