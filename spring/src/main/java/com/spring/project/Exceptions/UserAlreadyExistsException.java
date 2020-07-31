package com.spring.project.Exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.*;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="User Already Exists") // Custom 404 error
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String msg) {
    	super(msg);
    }
}
