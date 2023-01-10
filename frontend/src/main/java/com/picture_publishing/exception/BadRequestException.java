package com.picture_publishing.exception;

import java.io.Serial;

public class BadRequestException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	public BadRequestException() {
	}

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

}
