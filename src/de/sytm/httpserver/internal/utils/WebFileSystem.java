package de.sytm.httpserver.internal.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WebFileSystem {

	private File root;
	private List<String> indexfiles;

	public WebFileSystem(File root, List<String> indexfiles) {
		this.root = root;
		this.indexfiles = indexfiles;
	}

	public File getRoot() {
		return root;
	}

	public File toFile(String requestedPath) {
		File file = new File(root, requestedPath);
		if (file.isFile())
			return file;
		if (file.isDirectory() && !file.exists())
			return file;
		return toFile(requestedPath, new ArrayList<String>(indexfiles));
	}

	private File toFile(String requestedPath, ArrayList<String> remaining) {
		if (remaining.size() == 0)
			return new File(root, requestedPath);
		String indexfile = remaining.get(0);
		remaining.remove(0);
		File file = new File(root, requestedPath + indexfile);
		if (file.exists())
			return file;
		return toFile(requestedPath, remaining);
	}
}
