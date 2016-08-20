package de.sytm.httpserver.internal.impl;

import java.util.HashMap;
import java.util.Map;

import de.sytm.httpserver.api.HTTPResponseCode;
import de.sytm.httpserver.api.virtualpage.TextResponse;

class TextResponseImpl implements TextResponse {

	private Map<String, String> headers;
	private String body;
	private HTTPResponseCode httprc;
	
	TextResponseImpl() {
		headers = new HashMap<String, String>();
		body = "Missing body";
		httprc = HTTPResponseCode.FINE;
	}

	@Override
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	@Override
	public void setBody(String content) {
		this.body = content;
	}

	@Override
	public void setResponseCode(HTTPResponseCode responsecode) {
		this.httprc = responsecode;
	}

	@Override
	public Map<String, String> getHeaders() {
		return headers;
	}

	@Override
	public String getBody() {
		return body;
	}

	@Override
	public HTTPResponseCode getResponseCode() {
		return httprc;
	}
}
