package de.sytm.httpserver.api;

import java.net.InetAddress;

/**
 * This class allows you, to block ips and restrict access to directorys
 * 
 * @author Lukas
 *
 */
public interface AccessFilter {

	/**
	 * Check, if the address is allowed or denied
	 * 
	 * @param address
	 *            The adrress from the client
	 * @return True, if the request is allowed
	 */
	public boolean check(InetAddress address);

	/**
	 * Check, if the address is allowed or denied for this specific path
	 * 
	 * @param path
	 *            The path requested from the client
	 * @param address
	 *            The adrress from the client
	 * @return True, if the request is allowed
	 */
	public boolean check(InetAddress address, String path);
}
