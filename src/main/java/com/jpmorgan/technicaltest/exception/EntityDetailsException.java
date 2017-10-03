package com.jpmorgan.technicaltest.exception;

public class EntityDetailsException extends Exception{

	private String message;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8991950804476075257L;
	
	public EntityDetailsException(String message, Throwable exception)
	{
		super(exception);
		this.message = message;
	}
	
	public EntityDetailsException(String message)
	{
		this(message,null);
	}

	public String getMessage() {
		return message;
	}

	

}
