package de.sytm.httpserver.internal.impl;

import de.sytm.httpserver.api.Attachment;
import de.sytm.httpserver.internal.ByteBuf;
import de.sytm.httpserver.internal.Validate;

public class AttachmentImpl implements Attachment {

	private ByteBuf bytes;

	@Override
	public void setContent(ByteBuf bytes) {
		Validate.notNull(bytes, "The bytes can't be null!");
		this.bytes = bytes;
	}

	@Override
	public ByteBuf getContent() {
		return bytes;
	}
}
