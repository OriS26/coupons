package com.ori.exceptions;

import com.ori.enums.ErrorTypes;

public class ApplicationException extends Exception {
	
	private static long IL;

	private static final long serialVersionUID = IL;
	
	private ErrorTypes errorType;
	
	
	public ApplicationException (Exception e, ErrorTypes errorType, String message) {
		super(message, e);
		
	}
	
	public ApplicationException (ErrorTypes errorType, String message) {
		super(message);
		this.errorType = errorType;
		
	}
	
	public ApplicationException(String string) {
		// TODO Auto-generated constructor stub
	}

	public ErrorTypes getErrorType() {
		return errorType;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @param errorType the errorType to set
	 */
	public void setErrorType(ErrorTypes errorType) {
		this.errorType = errorType;
	}
	
}
