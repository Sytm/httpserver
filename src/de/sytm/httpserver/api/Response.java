package de.sytm.httpserver.api;

import java.util.Map;

import de.sytm.httpserver.internal.Constants;
import de.sytm.httpserver.internal.impl.ResponseImpl;

/**
 * This class contains information for a response to the client<br>
 * <br>
 * Create a new instance with {@link Response#newResponse(boolean)}
 * 
 * @author Lukas
 *
 */
public interface Response {

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
	public void setContent(String content);

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
	public String getContent();

	/**
	 * Returns the http responsecode from this response
	 * 
	 * @return The responsecode
	 */
	public HTTPResponseCode getResponseCode();

	/**
	 * Sets the file attachment
	 * 
	 * @param attachment
	 *            The attachment
	 */
	public void addAttachment(Attachment attachment);

	/**
	 * The attachment, if it was set, else <code>null</code>
	 * 
	 * @return The attachment, if present
	 */
	public Attachment getAttachment();

	/**
	 * Creates a new Response object with {@link #newResponse(boolean)} and
	 * <code>false</code> as second parameter
	 * 
	 * @return The created instance
	 */
	public static Response newResponse() {
		return newResponse(false);
	}

	/**
	 * Creates a new Response object.<br>
	 * If <i>setdefaultheaders</i> is true, it will set the header
	 * <code>Content-Type: text/html</code>
	 * 
	 * @param setdefaultheaders
	 *            Set the default header or not
	 * @return The created instance
	 */
	public static Response newResponse(boolean setdefaultheaders) {
		return new ResponseImpl(setdefaultheaders);
	}

	/**
	 * Description from WikiPedia.org:<br>
	 * <br>
	 * <code>The server successfully processed the request and is not returning any content.</code>
	 */
	public static final Response NO_CONTENT = Constants.NO_CONTENT;
	/**
	 * Description from WikiPedia.org:<br>
	 * <br>
	 * <code>The server cannot or will not process the request due to
	 * an apparent client error (e.g., malformed request syntax,
	 * too large size, invalid request message framing,
	 * or deceptive request routing).</code>
	 */
	public static final Response BAD_REQUEST = Constants.BAD_REQUEST;
	/**
	 * Description from WikiPedia.org:<br>
	 * <br>
	 * <code>Similar to 403 Forbidden, but specifically for use when authentication
	 * is required and has failed or has not yet been provided.
	 * The response must include a WWW-Authenticate header
	 * field containing a challenge applicable to the requested resource.
	 * </code>
	 */
	public static final Response UNAUTHORIZED = Constants.UNAUTHORIZED;
	/**
	 * @deprecated For future reserved
	 */
	@Deprecated
	public static final Response PAYMENT_REQUIRED = Constants.PAYMENT_REQUIRED;
	/**
	 * Description from WikiPedia.org:<br>
	 * <br>
	 * <code>The request was a valid request, but the server is refusing to
	 * respond to it. The user might be logged in but does not have the
	 * necessary permissions for the resource.</code>
	 */
	public static final Response FORBIDDEN = Constants.FORBIDDEN;
	/**
	 * Description from WikiPedia.org:<br>
	 * <br>
	 * <code>The requested resource could not be found but may be
	 * available in the future. Subsequent requests by
	 * the client are permissible.</code>
	 */
	public static final Response NOT_FOUND = Constants.NOT_FOUND;
	/**
	 * Description from WikiPedia.org:<br>
	 * <br>
	 * <code>A generic error message, given when an unexpected
	 * condition was encountered and no more specific
	 * message is suitable.</code>
	 */
	public static final Response INTERNAL_ERROR = Constants.INTERNAL_ERROR;
	/**
	 * Description from WikiPedia.org:<br>
	 * <br>
	 * <code>The server either does not recognize the request method,
	 * or it lacks the ability to fulfill the request. Usually
	 * this implies future availability (e.g., a new feature of a
	 * web-service API).</code>
	 */
	public static final Response NOT_IMPLEMENTED = Constants.NOT_IMPLEMENTED;
	/**
	 * Description from WikiPedia.org:<br>
	 * <br>
	 * <code>The server was acting as a gateway or proxy
	 * and received an invalid response from the upstream server.</code>
	 */
	public static final Response BAD_GATEWAY = Constants.BAD_GATEWAY;
	/**
	 * Description from WikiPedia.org:<br>
	 * <br>
	 * <code>The server is currently unavailable (because it is
	 * overloaded or down for maintenance). Generally, this
	 * is a temporary state.</code>
	 */
	public static final Response SERVICE_UNAVIABLE = Constants.SERVICE_UNAVIABLE;
	/**
	 * Description from WikiPedia.org:<br>
	 * <br>
	 * <code>The server was acting as a gateway or proxy
	 * and did not receive a timely response from the
	 * upstream server.</code>
	 */
	public static final Response GATEWAY_TIMEOUT = Constants.GATEWAY_TIMEOUT;
}
