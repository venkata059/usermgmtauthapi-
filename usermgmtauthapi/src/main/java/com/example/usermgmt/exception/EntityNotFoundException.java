package com.example.usermgmt.exception;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6113560192117626028L;

	public EntityNotFoundException() {
		super();
	}

	public EntityNotFoundException(String arg0) {
		super(arg0);
	}

	public EntityNotFoundException(Throwable arg0) {
		super(arg0);
	}

}

