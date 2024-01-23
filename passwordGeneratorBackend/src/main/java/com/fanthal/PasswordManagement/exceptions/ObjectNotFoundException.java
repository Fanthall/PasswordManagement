package com.fanthal.PasswordManagement.exceptions;

import com.fanthal.PasswordManagement.type.ErrorMessage;

public class ObjectNotFoundException extends FnthlException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(ErrorMessage errorMessage) {
		super(errorMessage);
	}
}