package de.sytm.httpserver.internal.impl;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import de.sytm.httpserver.api.RequestData;
import de.sytm.httpserver.api.RequestType;

public class RequestDataImpl implements RequestData {

	private final String path;
	private final RequestType type;
	private final HashMap<String, String> data;
	
	public RequestDataImpl(String path, RequestType type, HashMap<String, String> data) {
		this.path = path;
		this.type = type;
		this.data = data;
	}

	@Override
	public RequestType getType() {
		return type;
	}

	@Override
	public Map<String, String> getData() {
		return data;
	}

	@Override
	public String getRequestedPath() {
		return path == null ? "/" : path;
	}

	@Override
	public InetAddress getAddress() {
		return null;
	}
}
