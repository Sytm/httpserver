package de.sytm.httpserver.internal;

public class WebException extends RuntimeException {

	private static final long serialVersionUID = 7154647256246599004L;

	public WebException(String message) {
		super(message);
	}
	
	public WebException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
