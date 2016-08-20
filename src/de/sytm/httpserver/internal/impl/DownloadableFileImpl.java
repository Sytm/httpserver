package de.sytm.httpserver.internal.impl;

import java.io.File;

import de.sytm.httpserver.api.presets.listeners.virtual.DownloadableFile;
import de.sytm.httpserver.internal.ByteBuf;
import de.sytm.httpserver.internal.utils.IOUtils;

class DownloadableFileImpl implements DownloadableFile {

	private final String filename;
	private final ByteBuf bytes;
	private final String type;
	private final int filesize;

	DownloadableFileImpl(String filename, File file) {
		this.filename = filename;
		this.filesize = (int) file.length();
		this.type = IOUtils.getFileType(file);
		this.bytes = IOUtils.readFileToBuffer(file);
	}

	@Override
	public String getFileName() {
		return filename;
	}

	@Override
	public ByteBuf getBytes() {
		return bytes;
	}

	@Override
	public String getFileType() {
		return type;
	}

	@Override
	public int getFileSize() {
		return filesize;
	}
}
