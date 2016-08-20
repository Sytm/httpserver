package de.sytm.httpserver.internal.impl;

import java.net.InetAddress;
import java.util.Collections;
import java.util.Map;

import de.sytm.httpserver.api.RequestData;
import de.sytm.httpserver.api.RequestType;

public class RequestDataImpl implements RequestData {

	private final InetAddress address;
	private final String path;
	private final RequestType type;
	private final Map<String, String> data;
	
	public RequestDataImpl(String path, RequestType type, Map<String, String> data, InetAddress address) {
		this.path = path;
		this.type = type;
		this.data = Collections.unmodifiableMap(data);
		this.address = address;
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
		return address;
	}
}
