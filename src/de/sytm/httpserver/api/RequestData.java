package de.sytm.httpserver.api;

import java.net.InetAddress;
import java.util.Map;

public interface RequestData {

	public String getRequestPath();
	
	public RequestType getType();

	public Map<String, String> getData();
	
	public InetAddress getAddress();
}
