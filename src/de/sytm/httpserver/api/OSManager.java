package de.sytm.httpserver.api;

import java.math.BigDecimal;

public class OSManager {

	private static final String os;
	private static OSType type;

	static {
		os = System.getProperty("os.name").toLowerCase();
	}

	/**
	 * Defines your operating system with an simple enum
	 * 
	 * @return Your os type
	 */
	public static OSType getOSType() {
		if (type != null)
			return type;
		if (os.indexOf("win") >= 0) {
			return type = OSType.WINDOWS;
		} else if (os.indexOf("mac") >= 0) {
			return type = OSType.OSX;
		} else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os.indexOf("aix") > 0) {
			return type = OSType.UNIX;
		} else if (os.indexOf("sunos") >= 0) {
			return type = OSType.SOLARIS;
		} else {
			return type = OSType.UNDEFINED;
		}
	}

	public static enum OSType {

		/**
		 * Indicates, that your operating system is windows<br>
		 * <br>
		 * Examples:
		 * <ul>
		 * <li>Windows XP</li>
		 * <li>Windows Vista</li>
		 * <li>Windows 7</li>
		 * <li>Windows 8
		 * <ul>
		 * <li>Windows 8.1</li>
		 * </ul>
		 * </li>
		 * <li>Windows 10</li>
		 * </ul>
		 */
		WINDOWS,

		/**
		 * Indicates, that your operating system is mac osx
		 */
		OSX,

		/**
		 * Indicates, that your operating system is a unix system<br>
		 * <br>
		 * Examples:
		 * <ul>
		 * <li>Linux
		 * <ul>
		 * <li>Debian</li>
		 * <li>Ubuntu</li>
		 * </ul>
		 * </il>
		 * <li>FreeBSD</li>
		 * <li>BSDNET</li>
		 * </ul>
		 */
		UNIX,

		/**
		 * Indicates, that your operating system is a solaris distribution
		 */
		SOLARIS,

		/**
		 * Uhhm, yeah... You have an operating system, that I don't know
		 */
		UNDEFINED;

		private String version;

		private OSType() {
			version = formatVersion(new BigDecimal(System.getProperty("os.version")).doubleValue());
		}

		@Override
		public String toString() {
			String result = super.toString().toLowerCase();
			return Character.toUpperCase(result.charAt(0)) + result.substring(1) + " " + version;
		}

		private static String formatVersion(double value) {
			if (value == (long) value)
				return String.format("%d", (long) value);
			else
				return String.format("%s", value);
		}
	}
}