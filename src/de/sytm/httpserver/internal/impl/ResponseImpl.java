package de.sytm.httpserver.internal.impl;

import java.util.HashMap;
import java.util.Map;

import de.sytm.httpserver.api.Attachment;
import de.sytm.httpserver.api.HTTPResponseCode;
import de.sytm.httpserver.api.Response;
import de.sytm.httpserver.internal.Validator;

public class ResponseImpl implements Response {

	private Attachment attachment;
	private Map<String, String> headers;
	private String content;
	private HTTPResponseCode httprc;
	
	public ResponseImpl(boolean sdh) {
		headers = new HashMap<String, String>();
		content = "Missing content";
		httprc = HTTPResponseCode.FINE;
		if (sdh) {
			headers.put("Content-Type", "text/html");
		}
	}
	
	@Override
	public void setHeaders(Map<String, String> headers) {
		Validator.notNull(headers, "The headers can't be null!");
		this.headers = headers;
	}

	@Override
	public void setContent(String content) {
		Validator.notNull(content, "The content for the page can't be null!");
		this.content = content;
	}

	@Override
	public void setResponseCode(HTTPResponseCode responsecode) {
		Validator.notNull(responsecode, "The responsecode can't be null!");
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

	@Override
	public void addAttachment(Attachment attachment) {
		Validator.notNull(attachment, "The attachment can't be null!");
		this.attachment = attachment;
	}

	@Override
	public Attachment getAttachment() {
		return attachment;
	}
}
