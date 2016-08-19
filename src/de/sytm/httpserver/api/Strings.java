package de.sytm.httpserver.api;

import java.util.Map;

import de.sytm.httpserver.internal.Validate;

/**
 * A class providing some useful tools, when working with strings
 * 
 * @author Lukas
 *
 */
public class Strings {

	/**
	 * This method allows you to replace in one string multiple targets, and
	 * this faster than {@link String#replaceAll(String, String)} in strings
	 * with a length between 0 and 40.<br>
	 * Because it ignores the escaping character <b>\</b>, you're allowed to
	 * replace the character <b>\</b> with anything you want.<br>
	 * <br>
	 * To make it a one-liner, you can use {@link Mapper} for a simple
	 * map-creation
	 * 
	 * @param input
	 *            The input string to process
	 * @param replace
	 *            A map containig all regexes and replacements
	 * @return The string, with the replaced regex
	 * 
	 */
	public static String replaceAll(String input, Map<String, String> replace) {
		String workon = input;
		StringBuilder sb = new StringBuilder();
		for (String oldstring : replace.keySet()) {
			String newstring = replace.get(oldstring);
			for (int index = 0; index < workon.length(); ++index) {
				char c = workon.charAt(index);
				boolean append = true;
				if (index + oldstring.length() <= workon.length()) {
					if (workon.substring(index, index + oldstring.length()).equals(oldstring)) {
						sb.append(newstring);
						append = false;
						index += (oldstring.length() - 1);
					}
				}
				if (append)
					sb.append(c);
			}
			workon = sb.toString();
			sb = new StringBuilder();
		}
		return workon;
	}

	/**
	 * Replaces all substrings in <i>input</i> that are equal to <i>regex</i>
	 * with <i>replacement</i>
	 * 
	 * @param input
	 *            The input string to process
	 * @param regex
	 *            The string to search
	 * @param replacement
	 *            The replacement for the regex
	 * @return The string, with the replaced regex
	 * @see #replaceAll(String, Map)
	 * @see Mapper
	 */
	public static String replaceAll(String input, String regex, String replacement) {
		Validate.notNull(input, "The input can't be null!");
		Validate.notNull(regex, "The regex can't be null!");
		Validate.notNull(replacement, "The replacement can't be null!");
		return replaceAll(input, new Mapper<String, String>().add(regex, replacement));
	}
}
