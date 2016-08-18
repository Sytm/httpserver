package de.sytm.httpserver.internal;

import java.util.HashMap;
import java.util.Map;

import de.sytm.httpserver.api.HTTPResponseCode;
import de.sytm.httpserver.api.Response;
import de.sytm.httpserver.internal.impl.FinalResponseImpl;

public final class Constants {

	private Constants() {
	}

	public static final String VERSION;
	public static final String SERVERADMINMAIL;
	public static final String HTML_PRESET;
	private static final String ERROR_PRESET;
	private static final String SERVER_ERROR_PRESET;
	public static final Response NO_CONTENT;
	public static final Response BAD_REQUEST;
	public static final Response UNAUTHORIZED;
	public static final Response PAYMENT_REQUIRED;
	public static final Response FORBIDDEN;
	public static final Response NOT_FOUND;
	public static final Response INTERNAL_ERROR;
	public static final Response NOT_IMPLEMENTED;
	public static final Response BAD_GATEWAY;
	public static final Response SERVICE_UNAVIABLE;
	public static final Response GATEWAY_TIMEOUT;

	static {
		VERSION = "0.1.0";
		SERVERADMINMAIL = "admin@localhost";
		HTML_PRESET = "<!DOCTYPE html><html>%head%<head></head><body %bodyparams%>%body%</body></html>";
		ERROR_PRESET = HTML_PRESET.replaceAll("%head%", "<title>%error_type%</title>")
				.replaceAll("%bodyparams%", "style=\"font-family: courier, fixed, monospace;\"").replaceAll("%body%",
						"<center><div style=\"font-size: 50px; margin-top: 60px;\"><strong>%error_type%</strong></div><br>"
								+ "<br><br><div style=\"font-size: 30px; font-family: courier, fixed, monospace;\">%message_small%</div><hr><div style=\"font-size: 18px\">Java HTTPServer "
								+ VERSION + "</div></center>");
		SERVER_ERROR_PRESET = HTML_PRESET.replaceAll("%head%", "<title>%error_type%</title>")
				.replaceAll("%bodyparams%", "style=\"font-family: courier, fixed, monospace;\"").replaceAll("%body%",
						"<center><div style=\"font-size: 50px; margin-top: 60px;\"><strong>%error_type%</strong></div><br>"
								+ "<br><br><div style=\"font-size: 30px; font-family: courier, fixed, monospace;\">%message_small%</div><hr><div style=\"font-size: 18px\">Please contact a server admin: <a href=\"mailto:"
								+ SERVERADMINMAIL + "\">" + SERVERADMINMAIL + "</a><br>Java HTTPServer " + VERSION
								+ "</div></center>");
		NO_CONTENT = new FinalResponseImpl(new HashMap<String, String>(), "", HTTPResponseCode.NO_CONTENT);
		BAD_REQUEST = new FinalResponseImpl(getDefaultHeaders(),
				ERROR_PRESET.replaceAll("%error_type%", "Error - 400").replaceAll("%message_small%", "Bad request!"),
				HTTPResponseCode.BAD_REQUEST);
		UNAUTHORIZED = new FinalResponseImpl(getDefaultHeaders(),
				ERROR_PRESET.replaceAll("%error_type%", "Error - 401").replaceAll("%message_small%", "Unauthorized!"),
				HTTPResponseCode.UNAUTHORIZED);
		PAYMENT_REQUIRED = new FinalResponseImpl(getDefaultHeaders(), ERROR_PRESET
				.replaceAll("%error_type%", "Error - 402").replaceAll("%message_small%", "Payment required!"),
				HTTPResponseCode.PAYMENT_REQUIRED);
		FORBIDDEN = new FinalResponseImpl(getDefaultHeaders(),
				ERROR_PRESET.replaceAll("%error_type%", "Error - 403").replaceAll("%message_small%", "Forbidden!"),
				HTTPResponseCode.FORBIDDEN);
		NOT_FOUND = new FinalResponseImpl(getDefaultHeaders(),
				ERROR_PRESET.replaceAll("%error_type%", "Error - 404").replaceAll("%message_small%", "Page not found!"),
				HTTPResponseCode.NOT_FOUND);
		INTERNAL_ERROR = new FinalResponseImpl(getDefaultHeaders(), SERVER_ERROR_PRESET
				.replaceAll("%error_type%", "Error - 500").replaceAll("%message_small%", "An internal Error occoured!"),
				HTTPResponseCode.INTERNAL_ERROR);
		NOT_IMPLEMENTED = new FinalResponseImpl(getDefaultHeaders(), ERROR_PRESET
				.replaceAll("%error_type%", "Error - 501").replaceAll("%message_small%", "Not implemented yet!"),
				HTTPResponseCode.NOT_IMPLEMENTED);
		BAD_GATEWAY = new FinalResponseImpl(getDefaultHeaders(), SERVER_ERROR_PRESET
				.replaceAll("%error_type%", "Error - 502").replaceAll("%message_small%", "Bad Gateway!"),
				HTTPResponseCode.BAD_GATEWAY);
		SERVICE_UNAVIABLE = new FinalResponseImpl(getDefaultHeaders(),
				SERVER_ERROR_PRESET.replaceAll("%error_type%", "Error - 503").replaceAll("%message_small%",
						"Service currently unaviable!"),
				HTTPResponseCode.SERVICE_UNAVIABLE);
		GATEWAY_TIMEOUT = new FinalResponseImpl(getDefaultHeaders(), SERVER_ERROR_PRESET
				.replaceAll("%error_type%", "Error - 504").replaceAll("%message_small%", "Gateway timeout!"),
				HTTPResponseCode.GATEWAY_TIMEOUT);
	}

	private static Map<String, String> getDefaultHeaders() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Content-Type", "text/html");
		return map;
	}

	public static Response getMovedPermantly(String url) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Location", url);
		return new FinalResponseImpl(headers, "", HTTPResponseCode.MOVED_PERMANTLY);
	}
}
