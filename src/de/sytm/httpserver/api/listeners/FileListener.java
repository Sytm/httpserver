package de.sytm.httpserver.api.listeners;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import de.sytm.httpserver.api.Attachment;
import de.sytm.httpserver.api.RequestData;
import de.sytm.httpserver.api.Response;
import de.sytm.httpserver.api.WebListener;
import de.sytm.httpserver.internal.utils.IOUtils;
import de.sytm.httpserver.internal.utils.WebFileSystem;

public class FileListener implements WebListener {

	private WebFileSystem wfs;

	public FileListener(File rootdirectory, List<String> indexfiles) {
		this.wfs = new WebFileSystem(rootdirectory, indexfiles);
	}

	@Override
	public Response recieve(RequestData requestData) {
		File requestedFile = wfs.toFile(requestData.getRequestPath());
		if (requestedFile.isDirectory() || !requestedFile.exists()) {
			return Response.NOT_FOUND;
		}
		Response response = Response.newResponse(true);
		if (IOUtils.isImage(requestedFile)) {
			response.getHeaders().put("Content-Type", IOUtils.getFileType(requestedFile));
			Attachment attach = Attachment.createAttachment();
			attach.setContent(readImage(requestedFile));
			response.addAttachment(attach);
			return response;
		}
		if (IOUtils.isBinaryFile(requestedFile)) {
			response.getHeaders().put("Content-Type", IOUtils.getFileType(requestedFile));
			response.getHeaders().put("Content-Disposition",
					"attachment; filename=\"" + requestedFile.getName() + "\"");
			Attachment attach = Attachment.createAttachment();
			attach.setContent(IOUtils.readFileToBytes(requestedFile));
			response.addAttachment(attach);
		}
		response.getHeaders().put("Content-Type", IOUtils.getFileType(requestedFile));
		response.setContent(IOUtils.readFile(requestedFile));
		return response;
	}

	private static byte[] readImage(File file) {
		if (file.length() > Integer.MAX_VALUE)
			return null;
		ByteArrayOutputStream out = new ByteArrayOutputStream((int) file.length());
		try {
			BufferedImage image = ImageIO.read(file);
			ImageIO.write(image, IOUtils.getImageType(file), out);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return out.toByteArray();
	}
}
