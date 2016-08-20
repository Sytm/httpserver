package de.sytm.httpserver.api.virtualpage;

import java.util.Map;

import de.sytm.httpserver.api.Attachment;
import de.sytm.httpserver.api.HTTPResponseCode;

public interface TextResponse extends PageResponse {

	/**
	 * Sets the headers for the response <br>
	 * <br>
	 * Example for an file-download:
	 * <ul>
	 * <li>Content-Type -&#62; application/java-archive</li>
	 * <li>Content-Disposition -&#62; attachment;
	 * filename="MyJavaArchive.jar"</li>
	 * </ul>
	 * 
	 * @param headers
	 *            The headers
	 * @throws IllegalArgumentException
	 *             If the headers are null
	 */
	public void setHeaders(Map<String, String> headers);

	/**
	 * Sets the content for the response<br>
	 * <br>
	 * Will be ignored, if an {@link Attachment} is set
	 * 
	 * @param content
	 *            The content for the page
	 * @throws IllegalArgumentException
	 *             If the content is null
	 */
	public void setBody(String content);

	/**
	 * Sets the responsecode for the response
	 * 
	 * @param responsecode
	 *            The code
	 * @throws IllegalArgumentException
	 *             If the responsecode is null
	 */
	public void setResponseCode(HTTPResponseCode responsecode);

	/**
	 * Returns the currently settet headers
	 * 
	 * @return The headers
	 */
	public Map<String, String> getHeaders();

	/**
	 * Returns the content from the response
	 * 
	 * @return The content
	 */
	public String getBody();

	/**
	 * Returns the http responsecode from this response
	 * 
	 * @return The responsecode
	 */
	public HTTPResponseCode getResponseCode();
}
