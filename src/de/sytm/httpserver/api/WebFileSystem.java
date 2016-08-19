package de.sytm.httpserver.api;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.sytm.httpserver.internal.Validate;

public class WebFileSystem {

	private File root;
	private List<String> indexfiles;

	/**
	 * 
	 * <i>Informations for the parameter indexfiles</i>:<br>
	 * As first, let's construct a new {@link List}&#60;{@link String}&#62; and
	 * put some values in:
	 * 
	 * <pre>
	 * <code>
	 * List&#60;String&#62; indexfiles = new ArrayList&#60;String&#62;();
	 * indexfiles.add("index.html");
	 * indexfiles.add("index.htm");
	 * indexfiles.add("index.js");
	 * </code>
	 * </pre>
	 * 
	 * Now you'll see, how the file will be resolved, when
	 * {@link #toFile(String)} gets called, the requested path a directory is
	 * and exists:<br>
	 * 
	 * The class goes throug all index filenames and check if it exists. And if
	 * it exists the file gets returned. If the searcher is at the end of the
	 * list and has nothing found existent, the method simply returns the
	 * directory<br>
	 * <br>
	 * <ol>
	 * <li>index.html If this doesn't exists, it goes to the next</li>
	 * <li>index.htm If this doesn't exists, it goes to the next</li>
	 * <li>index.js If this doesn't exists, it returns simple the directory</li>
	 * </ol>
	 * 
	 * @param root
	 *            the rootdirectory for the website
	 * @param indexfiles
	 *            The indexfiles, explaination above
	 * @throws IllegalArgumentException
	 *             If the argument root is null
	 * @throws IllegalArgumentException
	 *             If the root directory doesn't exists
	 * @throws IllegalArgumentException
	 *             If the list indexfiles is null
	 */
	public WebFileSystem(File root, List<String> indexfiles) {
		Validate.notNull(root, "The rootdirectory can't be null!");
		Validate.mustTrue(root.exists(), "The root directory must exists!");
		Validate.notNull(indexfiles, "The indexfiles can't be null!");
		this.root = root;
		this.indexfiles = indexfiles;
	}

	/**
	 * Returns the rootdirectory for the website
	 * 
	 * @return The rootdirectory
	 */
	public File getRoot() {
		return root;
	}

	/**
	 * Relativizes the file path to the rootdirectory
	 * 
	 * @param file
	 *            The file for the relativizeing
	 * @return The relativized path, or null, if the file isn't a parent of the
	 *         root
	 * @throws IllegalArgumentException
	 *             If the file is null
	 */
	public String relativize(File file) {
		Validate.notNull(file, "The file for the relativizeing can't be null!");
		String rootpath = this.root.getAbsolutePath();
		String path = file.getAbsolutePath();
		if (path.indexOf(rootpath) >= 0) {
			return Strings.replaceAll(path, new Mapper<String, String>().add(rootpath, "").add("\\", "/"));
		}
		return null;
	}

	/**
	 * Converts the requested path to a file<br>
	 * <br>
	 * Returns <code>toFile("/")</code>, if the path is absolute and not a
	 * parent of the root directory.<br>
	 * <br>
	 * 
	 * <i>Informations for the parameter indexfiles from the constructor and
	 * it's usage</i>:<br>
	 * <br>
	 * As first, let's construct a new {@link List}&#60;{@link String}&#62; and
	 * put some values in:
	 * 
	 * <pre>
	 * <code>
	 * List&#60;String&#62; indexfiles = new ArrayList&#60;String&#62;();
	 * indexfiles.add("index.html");
	 * indexfiles.add("index.htm");
	 * indexfiles.add("index.js");
	 * </code>
	 * </pre>
	 * 
	 * Now you'll see, how the file will be resolved, when
	 * {@link #toFile(String)} gets called, the requested path a directory is
	 * and exists:<br>
	 * 
	 * The class goes throug all index filenames and check if it exists. And if
	 * it exists the file gets returned. If the searcher is at the end of the
	 * list and has nothing found existent, the method simply returns the
	 * directory<br>
	 * <br>
	 * <ol>
	 * <li>index.html If this doesn't exists, it goes to the next</li>
	 * <li>index.htm If this doesn't exists, it goes to the next</li>
	 * <li>index.js If this doesn't exists, it returns simple the directory</li>
	 * </ol>
	 * 
	 * @param requestedPath
	 *            The path requested from the webclient
	 * @return The file, which were requested
	 * @throws IllegalArgumentException
	 *             If the requested path is null
	 */
	public File toFile(String requestedPath) {
		Validate.notNull(requestedPath, "The requested path can't be null!");
		File file = new File(root, requestedPath);
		if (relativize(file) == null)
			return toFile("/");
		if (file.isFile())
			return file;
		if (file.isDirectory() && !file.exists())
			return file;
		return toFile(requestedPath, new ArrayList<String>(indexfiles));
	}

	private File toFile(String requestedPath, ArrayList<String> remaining) {
		if (remaining.size() == 0)
			return new File(root, requestedPath);
		String indexfile = remaining.remove(0);
		File file = new File(root, requestedPath + indexfile);
		if (file.exists())
			return file;
		return toFile(requestedPath, remaining);
	}
}
