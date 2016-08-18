package de.sytm.httpserver;

import java.io.File;

import de.sytm.httpserver.api.WebServer;
import de.sytm.httpserver.api.listeners.FileListener;

public class Launcher {

	public static void main(String[] args) {
		/*
		 * Creating a new server instance on port 9000, 10 worker threads and the listener who reads files from the
		 */
		WebServer server = new WebServer(9000, 10, new FileListener(new File("D:/Dev/eclipse/docs/theshapecore/")));
		/*
		 * Starting the thread
		 */
		server.start();
		System.out.println("Webserver started!");
	}
}
