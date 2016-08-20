package de.sytm.httpserver.internal.impl;

import java.io.File;

import de.sytm.httpserver.api.virtualpage.DisplayableImage;
import de.sytm.httpserver.internal.ByteBuf;
import de.sytm.httpserver.internal.utils.IOUtils;

class DisplayableImageImpl implements DisplayableImage {

	private final ByteBuf bytes;
	private final String type;
	private final int filesize;
	
	DisplayableImageImpl(File file) {
		this.type = IOUtils.getFileType(file);
		this.bytes = IOUtils.readImageToBuffer(file);
		this.filesize = (int) file.length();
	}

	@Override
	public ByteBuf getBytes() {
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
