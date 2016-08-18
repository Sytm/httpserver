package de.sytm.httpserver.api;

import de.sytm.httpserver.internal.RequestParser.RawRequest;

public enum RequestType {

	GET, POST;

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
