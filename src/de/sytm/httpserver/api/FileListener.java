package de.sytm.httpserver.api;

import java.io.File;
import java.util.List;

import de.sytm.httpserver.internal.Validate;
import de.sytm.httpserver.internal.utils.IOUtils;

public class FileListener implements WebListener {

	private WebFileSystem wfs;

	/**
	 * Creates a new FileListener instance<br>
	 * <br>
	 * <br>
	 * If the requested file is a directory, the class goes throug all index
	 * filenames and check if it is there.
	 * <ol>
	 * <li>index.html If this doesn't exists, it goes to the next</li>
	 * <li>index.htm If this doesn't exists, it goes to the next</li>
	 * <li>index.js If this doesn't exists, it returns simple the directory</li>
	 * </ol>
	 * 
	 * @param rootdirectory
	 *            The root directory for all files
	 * @param indexfiles
	 *            All indexfiles
	 */
	public FileListener(File rootdirectory, List<String> indexfiles) {
		Validate.mustTrue(rootdirectory.exists(), "The rootdirectory must exists!");
		this.wfs = new WebFileSystem(rootdirectory, indexfiles);
	}

	@Override
	public Response recieve(RequestData requestData) {
		File requestedFile = wfs.toFile(requestData.getRequestedPath());
		if (requestedFile.isDirectory() || !requestedFile.exists()) {
			return Response.NOT_FOUND;
		}
		Response response = Response.newResponse(true);
		if (IOUtils.isImage(requestedFile)) {
			response.getHeaders().put("Content-Type", IOUtils.getFileType(requestedFile));
			Attachment attach = Attachment.createAttachment();
			attach.setContent(IOUtils.readImageToBuffer(requestedFile));
			response.setAttachment(attach);
			return response;
		}
		if (IOUtils.isBinaryFile(requestedFile)) {
			response.getHeaders().put("Content-Type", IOUtils.getFileType(requestedFile));
			response.getHeaders().put("Content-Disposition",
					"attachment; filename=\"" + requestedFile.getName() + "\"");
			Attachment attach = Attachment.createAttachment();
			attach.setContent(IOUtils.readFileToBuffer(requestedFile));
			response.setAttachment(attach);
		}
		response.getHeaders().put("Content-Type", IOUtils.getFileType(requestedFile));
		response.setBody(IOUtils.readFile(requestedFile));
		return response;
	}
}
