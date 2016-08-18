package de.sytm.httpserver.api;

public enum HTTPResponseCode {

	FINE("200 OK"), NO_CONTENT("204 No Content"), MOVED_PERMANTLY("301 Moved Permanently"), BAD_REQUEST(
			"400 Bad request"), UNAUTHORIZED("401 Unauthorized"), PAYMENT_REQUIRED("402 PaymentRequired"), FORBIDDEN(
					"403 Forbidden"), NOT_FOUND("404 Not Found"), INTERNAL_ERROR("500 Internal Error"), NOT_IMPLEMENTED(
							"501 Not implemented"), BAD_GATEWAY("502 Bad Gateway"), SERVICE_UNAVIABLE(
									"503 Service Unavailable"), GATEWAY_TIMEOUT("504 Gateway Timeout");

	private final String string;

	private HTTPResponseCode(String value) {
		this.string = value;
	}

	@Override
	public String toString() {
		return "HTTP/1.1 " + string;
	}
}
