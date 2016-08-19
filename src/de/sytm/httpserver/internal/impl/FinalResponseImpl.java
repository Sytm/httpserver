package de.sytm.httpserver.internal.impl;

import java.util.Collections;
import java.util.Map;

import de.sytm.httpserver.api.Attachment;
import de.sytm.httpserver.api.HTTPResponseCode;
import de.sytm.httpserver.api.Response;

public class FinalResponseImpl implements Response {

	private Map<String, String> headers;
	private String content;
	private HTTPResponseCode httprc;
	
	public FinalResponseImpl(Map<String, String> headers, String content, HTTPResponseCode httprc) {
		this.headers = Collections.unmodifiableMap(headers);
		this.content = content;
		this.httprc = httprc;
	}
	
	@Override
	public void setHeaders(Map<String, String> headers) {
	}

	@Override
	public void setBody(String content) {
	}

	@Override
	public void setResponseCode(HTTPResponseCode responsecode) {
	}

	@Override
	public Map<String, String> getHeaders() {
		return headers;
	}

	@Override
	public String getBody() {
		return content;
	}

	@Override
	public HTTPResponseCode getResponseCode() {
		return httprc;
	}

	@Override
	public void setAttachment(Attachment attachment) {
	}

	@Override
	public Attachment getAttachment() {
		return null;
	}
}
