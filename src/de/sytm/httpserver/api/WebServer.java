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
	private WebListener listener;

	/**
	 * Creates a new webserver
	 * @param port The port for the webserver
	 * @param maxthreads The maximum amount of worker threads
	 * @param listener The weblistener for incoming reqeusts
	 * @throws IllegalArgumentException If listener is null or maxthreads is lower or equal to zero or the port is in the wrong range
	 */
	public WebServer(int port, int maxthreads, WebListener listener) {
		if (port > 65535 || port < 0)
		if (maxthreads <= 0)
			throw new IllegalArgumentException("The amount of workerthreads must be greater than 0!");
		if (listener == null)
			throw new IllegalArgumentException("The listener class can't be null!");
		this.port = port;
		this.threadPool = Executors.newFixedThreadPool(maxthreads);
		this.listener = listener;
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
			this.threadPool.execute(new ClientWorker(clientSocket, listener));
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