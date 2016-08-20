package de.sytm.httpserver.internal.impl;

import de.sytm.httpserver.api.Mapper;
import de.sytm.httpserver.api.Strings;
import de.sytm.httpserver.api.presets.listeners.virtual.PageRequest;
import de.sytm.httpserver.api.presets.listeners.virtual.PageResponse;
import de.sytm.httpserver.api.presets.listeners.virtual.TextResponse;
import de.sytm.httpserver.api.presets.listeners.virtual.VirtualPage;
import de.sytm.httpserver.internal.Constants;

public class DefaultVirtualPageImpl implements VirtualPage {

	private static String content;
	private static VirtualPage page;

	private DefaultVirtualPageImpl() {
	}

	public static VirtualPage getDefault() {
		if (page == null)
			page = new DefaultVirtualPageImpl();
		return page;
	}

	@Override
	public PageResponse onRecieve(PageRequest request) {
		TextResponse response = new TextResponseImpl();
		if (content == null) {
			String base = Strings.replaceAll(Constants.HTML_PRESET,
					new Mapper<String, String>().add("%head%", "<title>Empty page</title>").add("%bodyparams%", ""));
			String body = "This is your first virtual page!<br><br>Add some content with adding a custom one!";
			content = Strings.replaceAll(base, "%body%", body);
		}
		response.setBody(content);
		return response;
	}
}
