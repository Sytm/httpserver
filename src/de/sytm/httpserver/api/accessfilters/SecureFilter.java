package de.sytm.httpserver.api.accessfilters;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import de.sytm.httpserver.api.AccessFilter;

public class SecureFilter implements AccessFilter {

	private static List<String> unsafefilesextensions;

	private static void init() {
		if (unsafefilesextensions == null) {
			unsafefilesextensions = new ArrayList<String>();
			unsafefilesextensions.add("php");
			unsafefilesextensions.add("php3");
			unsafefilesextensions.add("php4");
			unsafefilesextensions.add("php5");
			unsafefilesextensions.add("asp");
			unsafefilesextensions.add("phpx");
			unsafefilesextensions.add("cgi");
		}
	}

	public SecureFilter() {
	}

	@Override
	public boolean check(InetAddress address) {
		return true;
	}

	@Override
	public boolean check(InetAddress address, String path) {
		String[] parts = path.toLowerCase().split("\\.(?=[^\\.]+$)");
		String extension = (parts.length == 1) ? null : parts[1];
		if (extension != null) {
			init();
			if (unsafefilesextensions.contains(extension))
				return false;
		}
		return true;
	}

}
