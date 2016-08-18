package de.sytm.httpserver.internal;

import java.util.HashMap;
import java.util.Map;

import de.sytm.httpserver.api.HTTPResponseCode;
import de.sytm.httpserver.api.Response;
import de.sytm.httpserver.internal.impl.FinalResponseImpl;

public final class Constants {

	private Constants() {
	}
	
	public static final Response NOT_FOUND;
	public static final Response INTERNAL_ERROR;
	
	static {
		String pattern = "<center><div style=\"font-size: 50px; font-family: sans-serif;\">%message%</div></center>";
		NOT_FOUND = new FinalResponseImpl(getDefaultHeaders(), pattern.replaceAll("%message%", "Error - 404<br><br>Site not found!"), HTTPResponseCode.NOT_FOUND);
		INTERNAL_ERROR = new FinalResponseImpl(getDefaultHeaders(), pattern.replaceAll("%message%", "Error - 500<br><br>An internal Error occoured!"), HTTPResponseCode.INTERNAL_ERROR);
	}
	
	private static Map<String, String> getDefaultHeaders() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Content-Type", "text/html");
		return map;
	}
}
