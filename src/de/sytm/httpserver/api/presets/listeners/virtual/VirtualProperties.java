package de.sytm.httpserver.api.presets.listeners.virtual;

import java.util.List;

import de.sytm.httpserver.api.WebFileSystem;
import de.sytm.httpserver.internal.impl.VirtualPropertiesImpl;

/**
 * With this class you can modify the settings of your virtual page.<br>
 * <br>
 * Create a new instance with {@link #createDefault()}
 * 
 * @author Lukas
 *
 */
public interface VirtualProperties {

	/**
	 * Sets the state for page resolver.<br>
	 * <br>
	 * <i>!This is by default disabled!</i><br>
	 * <br>
	 * An simple explanation:<br>
	 * If the requested path is an directory, isn't registered and this is set
	 * to true, the page-resolver tries to find a specified index-page which you
	 * can set with {@link #setIndexPages(List)}<br>
	 * <br>
	 * For an more in-detail explanation look at this and replace every "file"
	 * with "page" ;) : <br>
	 * {@link WebFileSystem#toFile(String)}
	 * 
	 * @param state
	 *            If this service should be eneabled or not
	 */
	public void setUsePageResolver(boolean state);

	/**
	 * Sets the index-pages for the page-resolver. Not necessary, if
	 * {@link #setUsePageResolver(boolean)} is set to <code>false</code>
	 * 
	 * @param indexpages
	 *            The list of pages
	 * @throws IllegalArgumentException
	 *             If <i>indexpages</i> is null
	 */
	public void setIndexPages(List<String> indexpages);

	/**
	 * Returns true, if the page-resolver should be used, else false
	 * 
	 * @return The state
	 */
	public boolean usePageResolver();

	/**
	 * If you want, when the client requests the page
	 * <code>http://example.com/INDEX.html</code> that he gets the page for
	 * <code>http://example.com/index.html</code>, then set this to false<br>
	 * <br>
	 * <i>!The default is true!</i>
	 * 
	 * @param state
	 *            The state for this setting
	 */
	public void setCaseSensitive(boolean state);

	/**
	 * Returns true, if the request-paths are interpreted strict, else false
	 * 
	 * @return The settet state
	 */
	public boolean isCaseSensitive();

	/**
	 * Returns the previous settet index-page names
	 * 
	 * @return The index-pages
	 */
	public List<String> getIndexPages();

	/**
	 * Creates a copy of this properties instance
	 * 
	 * @return The copied instance
	 */
	public VirtualProperties copy();

	/**
	 * Creates a new instance of this class
	 * 
	 * @return The new created instance
	 */
	public static VirtualProperties createDefault() {
		return new VirtualPropertiesImpl();
	}
}
