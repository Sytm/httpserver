package de.sytm.httpserver.internal.impl;

import java.util.HashMap;
import java.util.Map;

import de.sytm.httpserver.api.Attachment;
import de.sytm.httpserver.api.HTTPResponseCode;
import de.sytm.httpserver.api.Response;
import de.sytm.httpserver.internal.Validate;

public class ResponseImpl implements Response {

	private Attachment attachment;
	private Map<String, String> headers;
	private String body;
	private HTTPResponseCode httprc;
	
	public ResponseImpl(boolean sdh) {
		headers = new HashMap<String, String>();
		body = "Missing body";
		httprc = HTTPResponseCode.FINE;
		if (sdh) {
			headers.put("Content-Type", "text/html");
		}
	}
	
	@Override
	public void setHeaders(Map<String, String> headers) {
		Validate.notNull(headers, "The headers can't be null!");
		this.headers = headers;
	}

	@Override
	public void setBody(String content) {
		Validate.notNull(content, "The content for the page can't be null!");
		this.body = content;
	}

	@Override
	public void setResponseCode(HTTPResponseCode responsecode) {
		Validate.notNull(responsecode, "The responsecode can't be null!");
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

	@Override
	public void setAttachment(Attachment attachment) {
		Validate.notNull(attachment, "The attachment can't be null!");
		this.attachment = attachment;
	}

	@Override
	public Attachment getAttachment() {
		return attachment;
	}
}
