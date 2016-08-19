package de.sytm.httpserver.internal;

public final class Validate {

	private Validate() {
	}

	public static void notNull(Object object, String message) {
		mustTrue(object != null, message);
	}
	
	public static void mustTrue(boolean value, String message) {
		if (!value) {
			throw new IllegalArgumentException(message);
		}
	}
}
