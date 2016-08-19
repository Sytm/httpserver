package de.sytm.httpserver.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Map.Entry;

import de.sytm.httpserver.api.AccessFilter;
import de.sytm.httpserver.api.RequestData;
import de.sytm.httpserver.api.RequestType;
import de.sytm.httpserver.api.Response;
import de.sytm.httpserver.api.WebListener;
import de.sytm.httpserver.internal.RequestParser.RawRequest;
import de.sytm.httpserver.internal.impl.RequestDataImpl;
import de.sytm.httpserver.internal.utils.IOUtils;

public class ClientWorker implements Runnable {

	private AccessFilter filter;
	private WebListener listener;
	private Socket clientSocket;

	public ClientWorker(Socket clientSocket, WebListener listener, AccessFilter filter) {
		this.clientSocket = clientSocket;
		this.listener = listener;
		this.filter = filter;
	}

	public void run() {
		try {
			byte[] buffer = new byte[512];
			InputStream input = clientSocket.getInputStream();
			OutputStream output = clientSocket.getOutputStream();
			if (filter.check(clientSocket.getInetAddress())) {
				try {
					input.read(buffer);
					buffer = IOUtils.cleanBuffer(buffer);
					String requestinformation = new String(buffer);
					RequestData request = parse(requestinformation);
					if (filter.check(clientSocket.getInetAddress(), request.getRequestedPath())) {
						Response rawresponse = listener.recieve(request);
						boolean def = true;
						if (rawresponse.getAttachment() != null) {
							if (rawresponse.getAttachment().getContent() != null) {
								def = false;
								output.write(rawresponse.getResponseCode().toString().getBytes());
								output.write((toString(rawresponse.getHeaders()) + "\r\n\r\n").getBytes());
								output.write(rawresponse.getAttachment().getContent());
								output.flush();
							}
						}
						if (def) {
							String response = toString(rawresponse);
							output.write(response.getBytes());
							output.flush();
						}
					} else {
						String response = toString(Response.FORBIDDEN);
						output.write(response.getBytes());
						output.flush();
					}
				} catch (Exception | OutOfMemoryError ex) {
					ex.printStackTrace();
					String response = toString(Response.INTERNAL_ERROR);
					output.write(response.getBytes());
					output.flush();
				}
			} else {
				String response = toString(Response.FORBIDDEN);
				output.write(response.getBytes());
				output.flush();
			}
			output.close();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static RequestData parse(String information) {
		RawRequest request = RequestParser.parse(information);
		return new RequestDataImpl(request.getHeaders().get("Request-Path"), RequestType.valueOf(request),
				request.getUrlData());
	}

	private static String toString(Response response) {
		String result = "";
		result += response.getResponseCode().toString();
		result += "\r\n" + toString(response.getHeaders()) + "\r\n\r\n";
		result += response.getBody();
		return result;
	}

	private static String toString(Map<String, String> headers) {
		String result = "";
		boolean first = true;
		for (Entry<String, String> entry : headers.entrySet()) {
			if (first) {
				result += entry.getKey() + ": " + entry.getValue();
				first = false;
			} else {
				result += "\r\n" + entry.getKey() + ": " + entry.getValue();
			}
		}
		return result;
	}
}