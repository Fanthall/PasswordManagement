package com.fanthal.PasswordManagement.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fanthal.PasswordManagement.dto.ErrorResponseDTO;
import com.fanthal.PasswordManagement.exceptions.FnthlException;
import com.fanthal.PasswordManagement.exceptions.ObjectNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class FnthlControllerAdvice {
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ErrorResponseDTO> objectNotFoundException(ObjectNotFoundException ex) {
		log.error(ex.getCode() + ex.getMessage());
		ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getCode(), ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(FnthlException.class)
	public ResponseEntity<ErrorResponseDTO> semException(FnthlException ex) {
		log.error(ex.getCode() + ex.getMessage(), ex);
		ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getCode(), ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
	}

}
