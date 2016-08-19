package de.sytm.httpserver.api.presets.listeners.virtual;

/**
 * This class holds information about a downloadable file
 * 
 * @author Lukas
 *
 */
public interface DownloadableFile extends PageResponse {

	/**
	 * Returns the filename, which should be preferred by the browser
	 * 
	 * @return The filename
	 */
	public String getFileName();

	/**
	 * Returns the file in its bytes
	 * 
	 * @return The file
	 */
	public byte[] getBytes();

	/**
	 * Returns the mime-type of the file
	 * 
	 * @return The mime-type
	 */
	public String getFileType();

	/**
	 * Returns the size of the file
	 * 
	 * @return The size
	 */
	public int getFileSize();
}
