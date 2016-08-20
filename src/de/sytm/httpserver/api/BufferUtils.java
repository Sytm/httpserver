package de.sytm.httpserver.api;

import java.io.File;

import de.sytm.httpserver.internal.ByteBuf;
import de.sytm.httpserver.internal.Validate;
import de.sytm.httpserver.internal.utils.IOUtils;

/**
 * This class provides utils to create a {@link ByteBuf}
 * 
 * @author Lukas
 *
 */
public final class BufferUtils {

	private BufferUtils() {
	}

	/**
	 * Use this, if you want to convert normal a file
	 * 
	 * @param file
	 *            The file to convert
	 * @return The responding bytebuf
	 * @throws IllegalArgumentException
	 *             If the file is null
	 */
	public static ByteBuf createBuffer(File file) {
		Validate.notNull(file, "The file can't be null!");
		return IOUtils.readFileToBuffer(file);
	}

	/**
	 * Use this, if you want to convert a file
	 * 
	 * @param file
	 *            The file to convert
	 * @return The responding bytebuf
	 * @throws IllegalArgumentException
	 *             If the file is null
	 */
	public static ByteBuf createImageBuffer(File file) {
		Validate.notNull(file, "The file can't be null!");
		return IOUtils.readImageToBuffer(file);
	}
}
