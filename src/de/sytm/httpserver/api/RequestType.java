package de.sytm.httpserver.api;

import de.sytm.httpserver.internal.RequestParser.RawRequest;

public enum RequestType {

	/**
	 * The request was a get request
	 */
	GET, 
	/**
	 * The request was a post request
	 */
	POST;

	public static RequestType valueOf(RawRequest request) {
		if (!request.getHeaders().containsKey("Request-Type"))
			return GET;
		if (request.getHeaders().get("Request-Type").equals("POST")) {
			return POST;
		} else {
			return GET;
		}
	}
}
