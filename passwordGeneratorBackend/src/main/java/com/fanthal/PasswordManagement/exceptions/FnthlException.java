package com.fanthal.PasswordManagement.exceptions;

import com.fanthal.PasswordManagement.type.ErrorMessage;

public class FnthlException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final String code;
	private final String internalMessage;

	public FnthlException(ErrorMessage errorMessage) {
		super(errorMessage.getMessage());
		this.code = errorMessage.getCode();
		this.internalMessage = errorMessage.getInternalMessage();
	}

	public FnthlException(ErrorMessage errorMessage, FnthlException e) {
		super(errorMessage.getMessage(), e);
		this.code = errorMessage.getCode();
		this.internalMessage = errorMessage.getInternalMessage();
	}

	public FnthlException(ErrorMessage errorMessage, String exceptionalMessage) {
		super(errorMessage.getMessage());
		this.code = errorMessage.getCode();
		this.internalMessage = exceptionalMessage;
	}

	public FnthlException(ErrorMessage errorMessage, String message, String exceptionalMessage) {
		super(message);
		this.code = errorMessage.getCode();
		this.internalMessage = exceptionalMessage;
	}

	public String getCode() {
		return code;
	}

	public String getInternalMessage() {
		return internalMessage;
	}
}
