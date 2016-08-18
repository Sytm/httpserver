package de.sytm.httpserver.internal.impl;

import de.sytm.httpserver.api.RequestData;
import de.sytm.httpserver.api.Response;
import de.sytm.httpserver.api.WebListener;

class DefaultWebListenerImpl implements WebListener {

	DefaultWebListenerImpl() {
	}

	@Override
	public Response recieve(RequestData requestData) {
		Response response = Response.newResponse(true);
		response.setContent("<h1>This is your first page!</h1><br><br><br><p>Add some content with adding a custom WebListener!</p>");
		return response;
	}
}
