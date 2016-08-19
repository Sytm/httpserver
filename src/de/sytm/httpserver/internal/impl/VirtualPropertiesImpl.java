package de.sytm.httpserver.internal.impl;

import java.util.ArrayList;
import java.util.List;

import de.sytm.httpserver.api.presets.listeners.virtual.VirtualProperties;
import de.sytm.httpserver.internal.Validate;

public class VirtualPropertiesImpl implements VirtualProperties, Cloneable {

	private boolean strict;
	private boolean useresolver;
	private List<String> indexpages;

	public VirtualPropertiesImpl() {
		useresolver = false;
		strict = true;
		indexpages = new ArrayList<String>();
	}

	@Override
	public void setUsePageResolver(boolean state) {
		this.useresolver = state;
	}

	@Override
	public void setIndexPages(List<String> indexpages) {
		Validate.notNull(indexpages, "The indexpages can't be null!");
		this.indexpages = new ArrayList<String>(indexpages);
	}

	@Override
	public boolean usePageResolver() {
		return useresolver;
	}

	@Override
	public List<String> getIndexPages() {
		return indexpages;
	}

	@Override
	public VirtualProperties copy() {
		try {
			return (VirtualProperties) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setCaseSensitive(boolean state) {
		this.strict = state;
	}

	@Override
	public boolean isCaseSensitive() {
		return strict;
	}
}
