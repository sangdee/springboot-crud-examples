package com.gwell.crudtemplate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author : Sangji Lee
 * @see : https://github.com/sangdee
 * @since : 2021-03-07
 */

/**
 * Exception for handling 404 error
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No such order")
public class CustomNotFoundException extends HttpClientErrorException {
    public CustomNotFoundException() {
        super(HttpStatus.NOT_FOUND, "No such order");
    }
}
