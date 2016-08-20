package de.sytm.httpserver.api.virtualpage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.sytm.httpserver.api.Attachment;
import de.sytm.httpserver.api.Mapper;
import de.sytm.httpserver.api.RequestData;
import de.sytm.httpserver.api.Response;
import de.sytm.httpserver.api.WebListener;
import de.sytm.httpserver.internal.Validate;
import de.sytm.httpserver.internal.impl.DefaultVirtualPageImpl;
import de.sytm.httpserver.internal.impl.PageRequestImpl;

/**
 * This class allows you to simply manage a virtual website completely written
 * in java.<br>
 * <br>
 * 
 * @author Lukas
 *
 */
public class VirtualWebSite implements WebListener {

	private VirtualProperties properties;
	private HashMap<String, VirtualPage> pages;

	/**
	 * Create a new and empty virtual website
	 */
	public VirtualWebSite(VirtualProperties properties) {
		this.properties = properties.copy();
		this.pages = new HashMap<String, VirtualPage>();
		if (!this.properties.isCaseSensitive()) {
			List<String> indexpages = this.properties.getIndexPages();
			for (int index = 0; index < indexpages.size(); ++index) {
				indexpages.set(index, indexpages.get(index).toLowerCase());
			}
		}
	}

	@Override
	public Response recieve(RequestData requestData) {
		if (pages.isEmpty()) {
			TextResponse tresponse = (TextResponse) DefaultVirtualPageImpl.getDefault().onRecieve(null);
			Response response = Response.newResponse();
			response.setBody(tresponse.getBody());
			response.setHeaders(response.getHeaders());
			response.setResponseCode(tresponse.getResponseCode());
			return response;
		}
		String reqPath = requestData.getRequestedPath();
		if (!properties.isCaseSensitive())
			reqPath = reqPath.toLowerCase();
		PageRequest request = new PageRequestImpl(requestData.getType(), requestData.getData(),
				requestData.getAddress());
		if (!pages.containsKey(reqPath)) {
			if (properties.usePageResolver()) {
				reqPath = fixPath(reqPath, new ArrayList<String>(properties.getIndexPages()));
			}
		}
		if (pages.containsKey(reqPath)) {
			PageResponse pageresponse = pages.get(reqPath).onRecieve(request);
			if (pageresponse instanceof TextResponse) {
				TextResponse tresponse = (TextResponse) pageresponse;
				Response response = Response.newResponse();
				response.setBody(tresponse.getBody());
				response.setHeaders(response.getHeaders());
				response.setResponseCode(tresponse.getResponseCode());
				return response;
			}
			if (pageresponse instanceof DownloadableFile) {
				DownloadableFile dresponse = (DownloadableFile) pageresponse;
				Response response = Response.newResponse();
				response.setHeaders(
						new Mapper<String, String>().add("Content-Lenght", Integer.toString(dresponse.getFileSize()))
								.add("Content-Type", dresponse.getFileType()).add("Content-Disposition",
										"attachment; filename=\"" + dresponse.getFileName() + "\""));
				Attachment attachment = Attachment.createAttachment();
				attachment.setContent(dresponse.getBytes());
				response.setAttachment(attachment);
				return response;
			}
			if (pageresponse instanceof DisplayableImage) {
				DisplayableImage iresponse = (DisplayableImage) pageresponse;
				Response response = Response.newResponse();
				response.getHeaders().put("Content-Type", iresponse.getImageType());
				Attachment attachment = Attachment.createAttachment();
				attachment.setContent(iresponse.getBytes());
				response.setAttachment(attachment);
				return response;
			}
		}
		return Response.NOT_FOUND;
	}

	/**
	 * Registers a new page for the given path
	 * 
	 * @param path
	 *            The path to your page
	 * @param virtualPage
	 *            The page
	 * @throws IllegalArgumentException
	 *             If the path is null
	 * @throws IllegalArgumentException
	 *             If the page is null
	 */
	public void registerPage(String path, VirtualPage virtualPage) {
		Validate.notNull(path, "The path to your page can't be null!");
		Validate.notNull(virtualPage, "The page can't be null!");
		if (!properties.isCaseSensitive())
			path = path.toLowerCase();
		pages.put(path, virtualPage);
	}

	/**
	 * Returns a unmodifiable view of the current registered pages
	 * 
	 * @return All registered pages
	 */
	public Map<String, VirtualPage> getAllPages() {
		return Collections.unmodifiableMap(pages);
	}

	private String fixPath(String requestedPath, ArrayList<String> indexpages) {
		if (indexpages.isEmpty())
			return requestedPath;
		String indexpage = indexpages.remove(0);
		if (pages.containsKey(indexpage))
			return indexpage;
		return fixPath(requestedPath, indexpages);
	}
}
