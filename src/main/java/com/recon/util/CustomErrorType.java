package com.recon.util;

public class CustomErrorType {
	private String errorCode;
	private String errorMessage;

	public CustomErrorType(String errorCode, String errorMessage) {
		// TODO Auto-generated constructor stub
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
