package de.sytm.httpserver;

import java.io.File;

import de.sytm.httpserver.api.ServerProperties;
import de.sytm.httpserver.api.WebServer;
import de.sytm.httpserver.api.listeners.FileListener;

public class Launcher {

	public static void main(String[] args) {
		ServerProperties props = ServerProperties.createDefault();
		props.setListener(new FileListener(new File("D:/Dev/eclipse/docs/theshapecore/")));
		WebServer server = new WebServer(props);
		server.start();
		System.out.println("Webserver started!");
	}
}
