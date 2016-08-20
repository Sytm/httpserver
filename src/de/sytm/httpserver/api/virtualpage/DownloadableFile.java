package de.sytm.httpserver.api.virtualpage;

import de.sytm.httpserver.internal.ByteBuf;

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
	public ByteBuf getBytes();

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
