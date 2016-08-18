package de.sytm.httpserver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.sytm.httpserver.api.ServerProperties;
import de.sytm.httpserver.api.WebServer;
import de.sytm.httpserver.api.listeners.FileListener;

public class Launcher {

	public static void main(String[] args) {
		List<String> indexfiles = new ArrayList<String>();
		indexfiles.add("index.html");
		indexfiles.add("index.htm");
		indexfiles.add("index.js");
		indexfiles.add("index.txt");
		ServerProperties props = ServerProperties.createDefault();
		props.setListener(new FileListener(new File("D:/Dev/eclipse/docs/theshapecore/"), indexfiles));
		WebServer server = new WebServer(props);
		server.start();
		System.out.println("Webserver started!");
	}
}
