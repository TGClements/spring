package com.spring.project.Exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.*;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Incomplete User Registration Data")
public class UnableToCreateUserException extends RuntimeException {

	public UnableToCreateUserException(String msg) {
		super(msg);
	}

}
