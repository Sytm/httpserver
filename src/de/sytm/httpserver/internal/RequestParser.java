package de.sytm.httpserver.internal;

import java.util.HashMap;

public class RequestParser {

	public static RawRequest parse(String string) {
		HashMap<String, String> result = new HashMap<String, String>();
		HashMap<String, String> urldata = new HashMap<String, String>();
		String[] lines = string.split("\\r?\\n");
		for (String line : lines) {
			if (line.toUpperCase().startsWith("GET") || line.toUpperCase().startsWith("POST")) {
				String[] parts = line.split(" ");
				result.put("Request-Type", parts[0].toUpperCase());
				result.put("Protocol-Version", parts[parts.length - 1]);
				DoubleObjectContainer<String, HashMap<String, String>> url = decompileURL(line.substring(line.indexOf(" ") + 1, line.lastIndexOf(" ")));
				urldata.putAll(url.getSecondValue());
				result.put("Request-Path", url.getFirstValue());
			} else {
				int index = line.indexOf(":");
				if (index >= 0) {
					index = line.indexOf(" ");
					result.put(line.substring(0, index), line.substring(index + 1));
				} else if (line.trim().length() > 0) {
					String[] dataparts;
					if (line.contains("&")) {
						dataparts = line.split("&");
					} else {
						dataparts = new String[] { line };
					}
					for (String datapart : dataparts) {
						String[] datapartarray;
						if (datapart.contains("=")) {
							datapartarray = datapart.split("=", 2);
						} else {
							datapartarray = new String[] { datapart, "true" };
						}
						urldata.put(datapartarray[0], datapartarray[1]);
					}
				}
			}
		}
		return new RawRequest(result, urldata);
	}

	private static DoubleObjectContainer<String, HashMap<String, String>> decompileURL(String url) {
		HashMap<String, String> urldata = new HashMap<String, String>();
		int index = url.indexOf("?");
		if (index >= 0) {
			String line = url.substring(index + 1);
			String[] dataparts;
			if (line.contains("&")) {
				dataparts = line.split("&");
			} else {
				dataparts = new String[] { line };
			}
			for (String datapart : dataparts) {
				String[] datapartarray;
				if (datapart.contains("=")) {
					datapartarray = datapart.split("=", 2);
				} else {
					datapartarray = new String[] { datapart, "true" };
				}
				urldata.put(datapartarray[0], datapartarray[1]);
			}
			return new DoubleObjectContainer<String, HashMap<String,String>>(url.substring(0, index), urldata);
		}
		return new DoubleObjectContainer<String, HashMap<String,String>>(url, urldata);
	}
	
	public static class RawRequest {

		private HashMap<String, String> map1;
		private HashMap<String, String> map2;

		private RawRequest(HashMap<String, String> map1, HashMap<String, String> map2) {
			this.map1 = map1;
			this.map2 = map2;
		}

		public HashMap<String, String> getHeaders() {
			return map1;
		}

		public HashMap<String, String> getUrlData() {
			return map2;
		}
	}
}
