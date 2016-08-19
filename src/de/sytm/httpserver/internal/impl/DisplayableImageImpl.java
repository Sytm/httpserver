package de.sytm.httpserver.internal.impl;

import java.io.File;

import de.sytm.httpserver.api.presets.listeners.virtual.DisplayableImage;
import de.sytm.httpserver.internal.utils.IOUtils;

class DisplayableImageImpl implements DisplayableImage {

	private final byte[] bytes;
	private final String type;
	private final int filesize;
	
	public DisplayableImageImpl(File file) {
		this.type = IOUtils.getFileType(file);
		this.bytes = IOUtils.readImageToBytes(file);
		this.filesize = (int) file.length();
	}

	@Override
	public byte[] getBytes() {
		return bytes;
	}

	@Override
	public String getImageType() {
		return type;
	}

	@Override
	public int getFileSize() {
		return filesize;
	}
}
