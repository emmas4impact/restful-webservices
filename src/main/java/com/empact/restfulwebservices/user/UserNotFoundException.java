package com.empact.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String message) {
		super(message);
		
	}

	
	private static final long serialVersionUID = -6994874027971696681L;

}
