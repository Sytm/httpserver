package de.sytm.httpserver.api;

import de.sytm.httpserver.internal.impl.SafeServerPropertiesImpl;

/**
 * Contains information about a webserver<br><br>
 * You can create a new instance with {@link ServerProperties#createDefault()}
 * @author Lukas
 *
 */
public interface ServerProperties {

	/**
	 * Sets the new access filter
	 * @param filter The new filter
	 * @throws IllegalArgumentException If the filter is null
	 */
	public void setAcessFilter(AccessFilter filter);
	
	/**
	 * Returns the access filter
	 * @return The filter
	 */
	public AccessFilter getAccessFilter();
	
	/**
	 * Sets the port for the webserver
	 * @param port The port number
	 * @throws IllegalArgumentException If the port number is lower than 0 or higher than 65535
	 */
	public void setPort(int port);
	
	/**
	 * Returns the current port
	 * @return The port number
	 */
	public int getPort();
	
	/**
	 * Sets the amount of worker threads for the server
	 * @param amount The amount
	 * @throws IllegalArgumentException If the amount is equal or lower than zero
	 */
	public void setWorkerThreads(int amount);
	
	/**
	 * Returns the amount of worker threads
	 * @return The amount
	 */
	public int getWorkerThreads();
	
	/**
	 * Sets the WebListener for the WebServer
	 * @param webListener
	 * @throws IllegalArgumentException If the listener is null
	 */
	public void setListener(WebListener webListener);
	
	/**
	 * Returns the weblistener
	 * @return The listener
	 */
	public WebListener getWebListener();
	
	/**
	 * Creates a new instance of {@link ServerProperties}
	 * @return the new created instance
	 */
	public static ServerProperties createDefault() {
		return new SafeServerPropertiesImpl();
	}
	
	/**
	 * Copies this instance
	 * @return The copied instance
	 */
	public ServerProperties copy();
}
