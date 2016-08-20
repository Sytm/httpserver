package de.sytm.httpserver.api.virtualpage;

public interface VirtualPage {

	public PageResponse onRecieve(PageRequest request);
}
