package de.sytm.httpserver.internal.impl;

import java.net.InetAddress;

import de.sytm.httpserver.api.AccessFilter;

class DefaultAccessFilterImpl implements AccessFilter {

	DefaultAccessFilterImpl() {

	}

	@Override
	public boolean check(InetAddress address) {
		return true;
	}

	@Override
	public boolean check(InetAddress address, String path) {
		return true;
	}
}
