package de.sytm.httpserver.api;

import java.net.InetAddress;
import java.util.Map;

/**
 * Contains information about a request from a client
 * 
 * @author Lukas
 *
 */
public interface RequestData {

	/**
	 * Returns the requested path on the webserver<br>
	 * <br>
	 * The path is at this location:<br>
	 * <code>http://example.com/PATH</code><br>
	 * so the path of <code>http://example.com/forums/add.php</code> is
	 * <code>/forums/add.php</code>
	 * 
	 * @return The requested path
	 */
	public String getRequestedPath();

	/**
	 * Returns the request type
	 * 
	 * @return The request type of the request
	 */
	public RequestType getType();

	/**
	 * The data from the url or the post-body<br>
	 * <br>
	 * The data is the part after the file, if it is a {@link RequestType#GET},
	 * else it is in the request body.<br>
	 * Example: <code>http://example.com/index.html?key=value&key2=value2</code>
	 * 
	 * @return The data
	 */
	public Map<String, String> getData();

	/**
	 * Returns the addrees of the client who requested the page
	 * 
	 * @return The InetAddress of the client
	 */
	public InetAddress getAddress();
}
