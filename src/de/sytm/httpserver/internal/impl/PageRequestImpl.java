package de.sytm.httpserver.internal.impl;

import java.io.File;
import java.net.InetAddress;
import java.util.Map;

import de.sytm.httpserver.api.RequestType;
import de.sytm.httpserver.api.presets.listeners.virtual.DisplayableImage;
import de.sytm.httpserver.api.presets.listeners.virtual.DownloadableFile;
import de.sytm.httpserver.api.presets.listeners.virtual.PageRequest;
import de.sytm.httpserver.api.presets.listeners.virtual.TextResponse;

public class PageRequestImpl implements PageRequest {

	private RequestType type;
	private Map<String, String> data;
	private InetAddress address;
	
	public PageRequestImpl(RequestType type, Map<String, String> data, InetAddress address) {
		this.type = type;
		this.data = data;
		this.address = address;
	}

	@Override
	public RequestType getType() {
		return type;
	}

	@Override
	public Map<String, String> getData() {
		return data;
	}

	@Override
	public InetAddress getAddress() {
		return address;
	}
	
	@Override
	public DownloadableFile download(File file) {
		return download(file, file.getName());
	}

	@Override
	public DownloadableFile download(File file, String filename) {
		return new DownloadableFileImpl(filename, file);
	}

	@Override
	public DisplayableImage image(File file) {
		return new DisplayableImageImpl(file);
	}

	@Override
	public TextResponse textResponse() {
		return new TextResponseImpl();
	}
}
