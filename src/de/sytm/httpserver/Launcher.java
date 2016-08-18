package de.sytm.httpserver;

import de.sytm.httpserver.api.RequestData;
import de.sytm.httpserver.api.Response;
import de.sytm.httpserver.api.WebListener;
import de.sytm.httpserver.api.WebServer;

public class Launcher {

	public static void main(String[] args) {
//		WebServer server = new WebServer(9000, 10, new FileListener(new File("D:/Dev/eclipse/docs/theshapecore/")));
		WebServer server = new WebServer(9000, 10, new WebListener() {
			
			@Override
			public Response recieve(RequestData requestData) {
				return Response.INTERNAL_ERROR;
			}
		});
		server.start();
		System.out.println("Webserver started!");
		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		System.out.println("Stopping server!");
		server.shutdown();
	}
}
