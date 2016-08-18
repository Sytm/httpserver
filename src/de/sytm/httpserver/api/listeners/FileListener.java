package de.sytm.httpserver.api.listeners;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import de.sytm.httpserver.api.RequestData;
import de.sytm.httpserver.api.Response;
import de.sytm.httpserver.api.WebListener;
import de.sytm.httpserver.internal.Utils;

public class FileListener implements WebListener {

	private File root;

	public FileListener(File rootdirectory) {
		this.root = rootdirectory;
	}

	@Override
	public Response recieve(RequestData requestData) {
		File requestedFile = new File(root, requestData.getRequestPath());
		if (requestedFile.isDirectory()) {
			File newfile = new File(requestedFile, "index.html");
			if (!newfile.exists()) {
				newfile = new File(requestedFile, "index.htm");
				if (!newfile.exists()) {
					newfile = new File(requestedFile, "index.js");
					if (!newfile.exists()) {
						newfile = new File(requestedFile, "index.txt");
					}
				}
			}
			requestedFile = newfile;
		}
		if (!requestedFile.exists()) {
			return Response.NOT_FOUND;
		}
		Response response = Response.newResponse(true);
		if (isImage(toURL(requestedFile))) {
			response.getHeaders().put("", getRawType(requestData.getRequestPath()) + ";base64");
			response.setContent(convertImage(requestedFile));
		}
		response.getHeaders().put("Content-Type", getRawType(toURL(requestedFile)));
		response.setContent(Utils.readFile(requestedFile));
		return response;
	}

	private static String convertImage(File file) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			ImageIO.write(ImageIO.read(file), getImageType(toURL(file)), output);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return DatatypeConverter.printBase64Binary(output.toByteArray());
	}

	private static boolean isImage(String path) {
		return getRaw(path)[0].equals("image");
	}

	private static String getImageType(String path) {
		return getRaw(path)[1];
	}

	private static String[] getRaw(String path) {
		return getRawType(path).split("/");
	}

	private static String getRawType(String path) {
		try {
			return Files.probeContentType(new File(getFileName(path)).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	private static String toURL(File file) {
		try {
			return file.toURI().toURL().toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return "";
	}

	private static String getFileName(String url) {
		url = "/" + url;
		return url.substring(url.lastIndexOf("/") + 1);
	}
}
