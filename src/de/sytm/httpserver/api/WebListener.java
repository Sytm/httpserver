package de.sytm.httpserver.api;

public interface WebListener {

	/**
	 * This method gets called, when a client the send a request to the server
	 * 
	 * @param requestData The information about the request
	 * @return The processed information
	 */
	public Response recieve(RequestData requestData);
}
