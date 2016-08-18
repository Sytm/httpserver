package de.sytm.httpserver.internal.impl;

import de.sytm.httpserver.api.Attachment;

public class AttachmentImpl implements Attachment {

	private byte[] bytes;

	@Override
	public void setContent(byte[] bytes) {
		if (bytes == null)
			throw new IllegalArgumentException("The bytes can't be null!");
		this.bytes = bytes;
	}

	@Override
	public byte[] getContent() {
		return bytes;
	}
}
