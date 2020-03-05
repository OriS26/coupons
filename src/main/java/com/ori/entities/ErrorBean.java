package com.ori.entities;

public class ErrorBean {
	
	private int errorNumber;
	private String errorMessage;

	public int getErrorNumber() {
		return errorNumber;
	}

	public ErrorBean() {
		super();
	}

	public ErrorBean(int errorNumber, String errorMessage) {
		super();
		this.errorNumber = errorNumber;
		this.errorMessage = errorMessage;
	}

	public void setErrorNumber(int errorNumber) {
		this.errorNumber = errorNumber;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
