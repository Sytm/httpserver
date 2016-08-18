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
	
	public void addAttachment(Attachment attachment);
	
	public Attachment getAttachment();

	public static Response newResponse() {
		return newResponse(false);
	}

	public static Response newResponse(boolean setdefaultheaders) {
		return new ResponseImpl(setdefaultheaders);
	}

	public static final Response NO_CONTENT = Constants.NO_CONTENT;
	public static final Response BAD_REQUEST = Constants.BAD_REQUEST;
	public static final Response UNAUTHORIZED = Constants.UNAUTHORIZED;
	public static final Response PAYMENT_REQUIRED = Constants.PAYMENT_REQUIRED;
	public static final Response FORBIDDEN = Constants.FORBIDDEN;
	public static final Response NOT_FOUND = Constants.NOT_FOUND;
	public static final Response INTERNAL_ERROR = Constants.INTERNAL_ERROR;
	public static final Response NOT_IMPLEMENTED = Constants.NOT_IMPLEMENTED;
	public static final Response BAD_GATEWAY = Constants.BAD_GATEWAY;
	public static final Response SERVICE_UNAVIABLE = Constants.SERVICE_UNAVIABLE;
	public static final Response GATEWAY_TIMEOUT = Constants.GATEWAY_TIMEOUT;
}
