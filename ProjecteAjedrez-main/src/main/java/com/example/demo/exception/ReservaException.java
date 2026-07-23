package com.example.demo.exception;

public class ReservaException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public ReservaException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ReservaException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
