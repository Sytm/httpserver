package de.sytm.httpserver.internal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public final class Utils {

	private Utils() {
	}

	public static byte[] cleanBuffer(byte[] input) {
		ArrayList<Byte> result = new ArrayList<Byte>();
		boolean ignore = true;
		for (int index = input.length - 1; index >= 0; --index) {
			if (ignore) {
				if (input[index] != 0) {
					result.add(input[index]);
					ignore = false;
				}
			} else {
				result.add(input[index]);
			}
		}
		Collections.reverse(result);
		return convert(result.toArray(new Byte[result.size()]));
	}

	private static byte[] convert(Byte[] input) {
		byte[] result = new byte[input.length];
		for (int index = 0; index < input.length; ++index)
			result[index] = input[index].byteValue();
		return result;
	}

	public static String readFile(File file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String linesep = System.lineSeparator();
			StringBuilder sb = new StringBuilder();
			boolean first = true;
			String line;
			while ((line = reader.readLine()) != null) {
				if (first) {
					sb.append(line);
					first = false;
				} else {
					sb.append(linesep + line);
				}
			}
			return sb.toString();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
