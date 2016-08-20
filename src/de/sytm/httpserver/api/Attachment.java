package de.sytm.httpserver.api;

import de.sytm.httpserver.internal.ByteBuf;
import de.sytm.httpserver.internal.impl.AttachmentImpl;

/**
 * A class which conatins raw bytes.<br>
 * <br>
 * Create a new instance with {@link Attachment#createAttachment()}
 * 
 * @author Lukas
 *
 */
public interface Attachment {

	/**
	 * Sets the bytes
	 * 
	 * @param bytes
	 *            The bytes to set
	 */
	public void setContent(ByteBuf bytes);

	/**
	 * Returns the content of the attachment
	 * 
	 * @return The content
	 */
	public ByteBuf getContent();

	/**
	 * Creates a new instance of {@link Attachment}
	 * 
	 * @return Creates a new attachment instance
	 */
	public static Attachment createAttachment() {
		return new AttachmentImpl();
	}
}
