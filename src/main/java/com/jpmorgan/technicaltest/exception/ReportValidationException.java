package com.jpmorgan.technicaltest.exception;

public class ReportValidationException extends Exception{

	private String message;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8991950804476075257L;
	
	public ReportValidationException(String message, Throwable exception)
	{
		super(exception);
		this.message = message;
	}
	
	public ReportValidationException(String message)
	{
		this(message,null);
	}

	public String getMessage() {
		return message;
	}

	

}
