package de.sytm.httpserver.api;

import java.util.Map;

import de.sytm.httpserver.internal.Constants;
import de.sytm.httpserver.internal.impl.ResponseImpl;

public interface Response {
	
	public void setHeaders(Map<String, String> headers);

	public void setContent(String content);
	
	public void setResponseCode(HTTPResponseCode responsecode);

	public Map<String, String> getHeaders();

	public String getContent();
	
	public HTTPResponseCode getResponseCode();
	
	public static Response newResponse() {
		return newResponse(false);
	}
	
	public static Response newResponse(boolean setdefaultheaders) {
		return new ResponseImpl(setdefaultheaders);
	}
	
	public static final Response NOT_FOUND = Constants.NOT_FOUND;
	
	public static final Response INTERNAL_ERROR = Constants.INTERNAL_ERROR;
}
