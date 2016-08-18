package de.sytm.httpserver.api;

import java.net.InetAddress;

public interface AccessFilter {

	public boolean check(InetAddress address);
	public boolean check(InetAddress address, String path);
}
