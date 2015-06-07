package com.appsfaculdade.labirinto.exeptions;

public class LabirintoException extends Exception {

	private static final long serialVersionUID = 1L;

	public LabirintoException() {
		super();
	}

	public LabirintoException(final String message) {
		super(message);
	}

	public LabirintoException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
}
