package de.sytm.httpserver.api;

public enum HTTPResponseCode {

	FINE("200 OK"), NO_CONTENT("204 No Content"), BAD_REQUEST("400 Bad request"), UNAUTHORIZED(
			"401 Unauthorized"), PAYMENT_REQUIRED("402 PaymentRequired"), FORBIDDEN("403 Forbidden"), NOT_FOUND(
					"404 Not Found"), INTERNAL_ERROR("500 Internal Error"), NOT_IMPLEMENTED("501 Not implemented");

	private final String string;

	private HTTPResponseCode(String value) {
		this.string = value;
	}
	
	@Override
	public String toString() {
		return "HTTP/1.1 " + string;
	}
}
