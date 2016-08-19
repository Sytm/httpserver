package de.sytm.httpserver.api;

import java.util.HashMap;
import java.util.Map;

/**
 * This class allows you to add keys and values to a map with an one-liner!<br>
 * <br>
 * <i>All maps created with this class are a subclass of {@link HashMap}</i>
 * 
 * @author Lukas
 *
 * @param <K>
 *            The key type for your map
 * @param <V>
 *            The value type for your map
 */
public class Mapper<K, V> extends HashMap<K, V> {

	private static final long serialVersionUID = 5596789896910515274L;

	/**
	 * Creates a new empty map with {@link HashMap#HashMap()}
	 */
	public Mapper() {
	}

	/**
	 * Creates a new map with the contents of the other with
	 * {@link HashMap#HashMap(Map)}
	 * 
	 * @param m
	 *            The old map to clone
	 */
	public Mapper(Map<? extends K, ? extends V> m) {
		super(m);
	}

	/**
	 * Constructs an empty HashMap with the specified initial capacity with
	 * {@link HashMap#HashMap(int)}
	 * 
	 * @param initialCapacity
	 *            the initial capacity
	 */
	public Mapper(int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * Adds an keypair to the map with {@link #put(Object, Object)}
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            value to be associated with the specified key
	 * @return This class itself
	 */
	public Mapper<K, V> add(K key, V value) {
		put(key, value);
		return this;
	}
}
