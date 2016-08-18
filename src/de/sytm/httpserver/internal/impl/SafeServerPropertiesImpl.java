package de.sytm.httpserver.internal.impl;

import de.sytm.httpserver.api.AccessFilter;
import de.sytm.httpserver.api.ServerProperties;
import de.sytm.httpserver.api.WebListener;
import de.sytm.httpserver.internal.Validator;

public class SafeServerPropertiesImpl implements ServerProperties, Cloneable {

	private AccessFilter filter;
	private int port;
	private int wthreads;
	private WebListener listener;

	public SafeServerPropertiesImpl() {
		port = 9000;
		wthreads = 4;
		filter = new DefaultAccessFilterImpl();
		listener = new DefaultWebListenerImpl();
	}

	@Override
	public void setAcessFilter(AccessFilter filter) {
		Validator.notNull(filter, "The filter can't be null!");
		this.filter = filter;
	}

	@Override
	public AccessFilter getAccessFilter() {
		return filter;
	}

	@Override
	public void setPort(int port) {
		Validator.mustTrue(port < 65535 && port > 0, "The port number must be between 0 and 65535!");
		this.port = port;
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public void setListener(WebListener webListener) {
		Validator.notNull(webListener, "The listener can't be null!");
		this.listener = webListener;
	}

	@Override
	public WebListener getWebListener() {
		return listener;
	}

	@Override
	public void setWorkerThreads(int amount) {
		Validator.mustTrue(amount > 0, "The amount of worker threads must be greater than 0!");
		this.wthreads = amount;
	}

	@Override
	public int getWorkerThreads() {
		return wthreads;
	}

	@Override
	public ServerProperties copy() {
		try {
			return (ServerProperties) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
