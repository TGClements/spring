package com.spring.project.Exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.*;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="User Not Found") // Custom 404 error
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String msg) {
    	super(msg);
    }
}
