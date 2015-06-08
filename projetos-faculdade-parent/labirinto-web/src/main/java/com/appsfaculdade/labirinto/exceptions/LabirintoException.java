package com.appsfaculdade.labirinto.exceptions;

public class LabirintoException extends Exception {
	
	private static final long serialVersionUID = -6967142969582032315L;

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
