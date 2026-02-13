package com.eureca.exception;


public class ResourceNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 176245626293437243L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}