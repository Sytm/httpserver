package de.sytm.httpserver.api;

public enum HTTPResponseCode {

	/**
	 * Indicates, that everything worked
	 */
	FINE("200 OK"),

	/**
	 * Indicates, that everything worked, but no content will be sent back
	 */
	NO_CONTENT("204 No Content"),

	/**
	 * Indicates, that the requested page is moved permantly
	 */
	MOVED_PERMANTLY("301 Moved Permanently"),

	/**
	 * Indicates, that the request is wrong
	 */
	BAD_REQUEST("400 Bad request"),

	/**
	 * Indicates, that the user is not permitted to see this content, but he is
	 * able to authorize him, to have acesss
	 */
	UNAUTHORIZED("401 Unauthorized"),

	/**
	 * Reserved for the future
	 */
	PAYMENT_REQUIRED("402 PaymentRequired"),

	/**
	 * Indicates, that the user is not permitted to see this content
	 */
	FORBIDDEN("403 Forbidden"),

	/**
	 * Indicates, that the requested content doesn't exists on the webserver
	 */
	NOT_FOUND("404 Not Found"),

	/**
	 * Indicates, that an unknown error occoured
	 */
	INTERNAL_ERROR("500 Internal Error"),

	/**
	 * Indicates, that this feature is currently not implemented, but soon it
	 * will
	 */
	NOT_IMPLEMENTED("501 Not implemented"),

	/**
	 * DOCUMENT ME!
	 */
	BAD_GATEWAY("502 Bad Gateway"),

	/**
	 * Indicates, that the service currently down is, or somthing like this
	 */
	SERVICE_UNAVIABLE("503 Service Unavailable"),

	/**
	 * DOCUMENT ME!
	 */
	GATEWAY_TIMEOUT("504 Gateway Timeout");

	private final String string;

	private HTTPResponseCode(String value) {
		this.string = value;
	}

	@Override
	public String toString() {
		return "HTTP/1.1 " + string;
	}
}
