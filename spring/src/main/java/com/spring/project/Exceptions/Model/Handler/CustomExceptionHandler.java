package com.spring.project.Exceptions.Model.Handler;

import java.sql.Timestamp;
 
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spring.project.Exceptions.*;
import com.spring.project.Exceptions.Model.Response.ErrorResponse;

@ControllerAdvice
public class CustomExceptionHandler  extends ResponseEntityExceptionHandler {

 
	// RequestedEmailAlreadyInUseException - 409 Conflict
	@ExceptionHandler(value = RequestedEmailAlreadyInUseException.class)
    protected ResponseEntity<Object> handleRequestedEmailAlreadyInUseException(RequestedEmailAlreadyInUseException ex, WebRequest request) {
    	
    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    	
    	ErrorResponse errorResponse = new ErrorResponse(
    			timestamp, 
    			HttpStatus.CONFLICT.value(), 
    			HttpStatus.CONFLICT.getReasonPhrase(), 
    			ex.getMessage(), 
    			request.getDescription(false)
    	);
    	
    	return handleExceptionInternal(ex, errorResponse.getResponse(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
	
	// UserNotFoundException - 400 Bad Request
    @ExceptionHandler(value = UnableToCreateUserException.class)
    protected ResponseEntity<Object> handleUnableToCreateUserException(UnableToCreateUserException ex, WebRequest request) {
    	
    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    	
    	ErrorResponse errorResponse = new ErrorResponse(
    			timestamp, 
    			HttpStatus.BAD_REQUEST.value(), 
    			HttpStatus.BAD_REQUEST.getReasonPhrase(), 
    			ex.getMessage(), 
    			request.getDescription(false)
    	);
    	
    	return handleExceptionInternal(ex, errorResponse.getResponse(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    
    // RequestedEmailAlreadyInUseException - 409 Conflict
 	@ExceptionHandler(value = UserAlreadyExistsException.class)
     protected ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request) {
     	
     	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
     	
     	ErrorResponse errorResponse = new ErrorResponse(
     			timestamp, 
     			HttpStatus.CONFLICT.value(), 
     			HttpStatus.CONFLICT.getReasonPhrase(), 
     			ex.getMessage(), 
     			request.getDescription(false)
     	);
     	
     	return handleExceptionInternal(ex, errorResponse.getResponse(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
     }
	
	// UserNotFoundException - 404 Not Found
    @ExceptionHandler(value = UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
    	
    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    	
    	ErrorResponse errorResponse = new ErrorResponse(
    			timestamp, 
    			HttpStatus.NOT_FOUND.value(), 
    			HttpStatus.NOT_FOUND.getReasonPhrase(), 
    			ex.getMessage(), 
    			request.getDescription(false)
    	);
    	
    	return handleExceptionInternal(ex, errorResponse.getResponse(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
	
}
