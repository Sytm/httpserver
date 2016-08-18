package de.sytm.httpserver.internal.impl;

import java.util.HashMap;
import java.util.Map;

import de.sytm.httpserver.api.HTTPResponseCode;
import de.sytm.httpserver.api.Response;

public class ResponseImpl implements Response {

	private Map<String, String> headers;
	private String content;
	private HTTPResponseCode httprc;
	
	public ResponseImpl(boolean sdh) {
		headers = new HashMap<String, String>();
		content = "Missing content";
		httprc = HTTPResponseCode.FINE;
		if (sdh) {
			headers.put("Content-Type", "text/html");
		} else {
			headers.put("Content-Type", "text/plain");
		}
	}
	
	@Override
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	@Override
	public void setContent(String content) {
		this.content = content;
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
	public String getContent() {
		return content;
	}

	@Override
	public HTTPResponseCode getResponseCode() {
		return httprc;
	}
}
