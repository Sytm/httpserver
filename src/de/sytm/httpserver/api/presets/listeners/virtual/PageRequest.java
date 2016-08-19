package de.sytm.httpserver.api.presets.listeners.virtual;

import java.io.File;
import java.net.InetAddress;
import java.util.Map;

import de.sytm.httpserver.api.RequestType;

/**
 * This is a class which is returned on a virtual page
 * 
 * @author Lukas
 *
 */
public interface PageRequest {

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
	 * Example:<br>
	 * <code>http://example.com/index.html&#63;key=value&#38;key2=value2</code>
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

	/**
	 * Converts the file to an downloadable type
	 * 
	 * @param file
	 *            The file, which the client should download
	 * @return The new created response for downloadable files
	 */
	public DownloadableFile download(File file);

	/**
	 * Converts the file to an downloadable type with a preferred filename for
	 * the browser
	 * 
	 * @param file
	 *            The file, which the client should download
	 * @param filename
	 *            The filename for the download
	 * @return The new created response for downloadable files
	 */
	public DownloadableFile download(File file, String filename);

	/**
	 * Converts the file to an displayable image for the browser
	 * 
	 * @param file
	 *            The image-file
	 * @return The new created response for displayable images
	 */
	public DisplayableImage image(File file);

	/**
	 * Creates a new 'normal' response for normal text content
	 * 
	 * @return The new created response for text
	 */
	public TextResponse textResponse();
}
