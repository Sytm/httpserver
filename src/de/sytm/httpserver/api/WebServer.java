package de.sytm.httpserver.api;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.sytm.httpserver.internal.ClientWorker;

public class WebServer extends Thread {

	private int port;
	private ServerSocket serverSocket;
	private boolean isRunning = false;
	private ExecutorService threadPool;
	private WebListener listener;

	public WebServer(int port, int maxthreads, WebListener listener) {
		this.port = port;
		this.threadPool = Executors.newFixedThreadPool(5);
		this.listener = listener;
	}
	
	public void run() {
		openSocket();
		while (isRunning()) {
			Socket clientSocket = null;
			try {
				clientSocket = this.serverSocket.accept();
				if (!isRunning()) {
					break;
				}
			} catch (IOException e) {
				if (!isRunning()) {
					break;
				}
				throw new RuntimeException("Error accepting client connection", e);
			}
			this.threadPool.execute(new ClientWorker(clientSocket, listener));
		}
		this.threadPool.shutdown();
	}

	/**
	 * Returns true, if the server is currently listening to clients
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
			throw new RuntimeException("Error closing server", e);
		}
	}

	/**
	 * Opens the ServerSocket
	 */
	private void openSocket() {
		try {
			this.serverSocket = new ServerSocket(this.port);
		} catch (IOException e) {
			throw new RuntimeException("Cannot bind to port " + this.port, e);
		}
	}
}