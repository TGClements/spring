package com.spring.project.Exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.*;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.CONFLICT, reason="Email already in use.")
public class RequestedEmailAlreadyInUseException extends RuntimeException {

	public RequestedEmailAlreadyInUseException(String msg) {
		super(msg);
	}
}
