package de.sytm.httpserver.api;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import de.sytm.httpserver.internal.ClientWorker;
import de.sytm.httpserver.internal.WebException;

public class WebServer extends Thread {

	private int port;
	private ServerSocket serverSocket;
	private boolean isRunning;
	private ExecutorService threadPool;
	private AccessFilter filter;
	private WebListener listener;

	/**
	 * Creates a new webserver
	 * 
	 * @param properties
	 *            The properties for this server
	 */
	public WebServer(ServerProperties properties) {
		this.listener = properties.getWebListener();
		this.filter = properties.getAccessFilter();
		this.threadPool = Executors.newFixedThreadPool(properties.getWorkerThreads());
		this.port = properties.getPort();
	}

	public void run() {
		openSocket();
		while (isRunning()) {
			Socket clientSocket;
			try {
				clientSocket = this.serverSocket.accept();
				if (!isRunning()) {
					break;
				}
			} catch (IOException e) {
				if (e.getMessage().equalsIgnoreCase("socket closed"))
					break;
				if (!isRunning()) {
					break;
				}
				throw new WebException("Error while accepting client connection", e);
			}
			this.threadPool.execute(new ClientWorker(clientSocket, listener, filter));
		}
		this.threadPool.shutdown();
		try {
			this.threadPool.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns true, if the server is currently listening to clients
	 * 
	 * @return The state of webserver
	 */
	public synchronized boolean isRunning() {
		return this.isRunning;
	}

	/**
	 * Shuts the server down
	 */
	public synchronized void shutdown() {
		this.isRunning = true;
		try {
			this.serverSocket.close();
		} catch (IOException e) {
			throw new WebException("Error closing server", e);
		}
	}

	/**
	 * Opens the ServerSocket
	 */
	private void openSocket() {
		try {
			this.serverSocket = new ServerSocket(this.port);
			isRunning = true;
		} catch (IOException e) {
			throw new WebException("Cannot bind to port " + this.port, e);
		}
	}
}