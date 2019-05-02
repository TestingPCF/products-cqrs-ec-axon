package com.hcl.cloud.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * @author BrijendraK
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    /*
     * private Date timestamp; private String message; private int errorCode;
     */

    /**
     * @param exception
     */
    public ProductException(String exception) {
        super(exception);
    }

    /**
     * 
     * @param timestamp
     * @param message
     * @param errorCode
     */
    /*
     * public ProductException(Date timestamp, String message, int errorCode) {
     * super(); this.timestamp = timestamp; this.message = message; this.errorCode =
     * errorCode; }
     * 
     * public Date getTimestamp() { return timestamp; }
     * 
     * public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
     * 
     * public String getMessage() { return message; }
     * 
     * public void setMessage(String message) { this.message = message; }
     * 
     * public int getErrorCode() { return errorCode; }
     * 
     * public void setErrorCode(int errorCode) { this.errorCode = errorCode; }
     */
}