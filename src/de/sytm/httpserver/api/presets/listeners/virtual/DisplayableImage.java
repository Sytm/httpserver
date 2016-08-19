package de.sytm.httpserver.api.presets.listeners.virtual;

/**
 * With this class you can display images to clients
 * 
 * @author Lukas
 *
 */
public interface DisplayableImage extends PageResponse {

	/**
	 * Returns the image in its bytes
	 * 
	 * @return The file
	 */
	public byte[] getBytes();

	/**
	 * Returns the image type of the image
	 * 
	 * @return The mime-type
	 */
	public String getImageType();

	/**
	 * Returns the size of the file
	 * 
	 * @return The size
	 */
	public int getFileSize();
}
