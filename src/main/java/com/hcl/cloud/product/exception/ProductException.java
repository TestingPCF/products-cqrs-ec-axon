package com.hcl.cloud.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author BrijendraK
 *
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ProductException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * @param exception
     */
    public ProductException(String exception) {
        super(exception);
    }

}