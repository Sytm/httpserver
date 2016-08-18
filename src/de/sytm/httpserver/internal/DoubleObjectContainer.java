package de.sytm.httpserver.internal;

/**
 * A small class, providing two fields for two custom objects
 * @author Lukas
 *
 * @param <D> The first type
 * @param <E> The second type
 */
public final class DoubleObjectContainer<D, E> {

	private D d;
	private E e;

	/**
	 * Creates a new container
	 * @param first The first value
	 * @param second The second value
	 */
	public DoubleObjectContainer(D first, E second) {
		this.d = first;
		this.e = second;
	}

	/**
	 * 
	 * @return The first value
	 */
	public D getFirstValue() {
		return d;
	}
	
	/**
	 * 
	 * @return The second value
	 */
	public E getSecondValue() {
		return e;
	}
}
