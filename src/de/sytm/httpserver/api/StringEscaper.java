package de.sytm.httpserver.api;

/**
 * With this class you can escape normal text into html entities
 * 
 * @author Lukas
 *
 */
public final class StringEscaper {

	public static final int ESCAPE_DOUBLE_QUOTES = 1;
	public static final int ESCAPE_QUOTES = 2;
	public static final int ESCAPE_BIGGERTHAN_SIGN = 4;
	public static final int ESCAPE_SMALLERTHAN_SIGN = 8;
	public static final int ESCAPE_ALL_CHARS = 16;

	private StringEscaper() {
	}

	private static final String PATTERN = "&#%byte%;";

	public static String encode(String input, int... actions) {
		int sum = 0;
		if (actions != null)
			for (int number : actions)
				sum += number;
		StringBuilder builder = new StringBuilder();
		if ((sum & ESCAPE_ALL_CHARS) != 0x0) {
			for (char c : input.toCharArray())
				builder.append(encode(c));
			return builder.toString();
		}
		if ((sum & ESCAPE_DOUBLE_QUOTES) != 0x0) {
			for (char c : input.toCharArray())
				if (c == '\"')
					builder.append(encode(c));
			input = builder.toString();
			builder = new StringBuilder();
		}
		if ((sum & ESCAPE_QUOTES) != 0x0) {
			for (char c : input.toCharArray())
				if (c == '\"')
					builder.append(encode(c));
			input = builder.toString();
			builder = new StringBuilder();
		}
		for (char c : input.toCharArray())
			builder.append(encode(c));
		return builder.toString();
	}

	private static String encode(char character) {
		return PATTERN.replace("%byte%", Byte.toString(getByte(character)));
	}

	private static byte getByte(char character) {
		return new String(new char[] { character }).getBytes()[0];
	}
}
