package de.sytm.httpserver.internal.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

import de.sytm.httpserver.internal.ByteBuf;

public final class IOUtils {

	private static HashMap<String, String> custommimetypes = new HashMap<String, String>();

	static {
		custommimetypes.put("jar", "application/java-archive");
		custommimetypes.put("ogg", "audio/ogg");
		custommimetypes.put("ogv", "video/ogg");
		custommimetypes.put("opus", "audio/ogg");
		custommimetypes.put("oga", "audio/ogg");
		custommimetypes.put("ogx", "application/ogg");
		custommimetypes.put("spx", "audio/ogg");
		custommimetypes.put("flac", "audio/ogg");
		custommimetypes.put("xspf", "application/xspf+xml");
		custommimetypes.put("less", "text/css");
		custommimetypes.put("scss", "text/css");
	}

	private IOUtils() {
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
	
	public static ByteBuf readFileToBuffer(File file) {
		ByteBuf buffer = new ByteBuf(file.length());
		try {
			buffer.read(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}
	
	public static ByteBuf readImageToBuffer(File file) {
		ByteBuf buffer = new ByteBuf(file.length());
		try {
			buffer.read(new FileImageInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
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

	public static byte[] readImageToBytes(File file) {
		if (file.length() > Integer.MAX_VALUE)
			return null;
		ByteArrayOutputStream out = new ByteArrayOutputStream((int) file.length());
		try {
			BufferedImage image = ImageIO.read(file);
			ImageIO.write(image, IOUtils.getImageType(file), out);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return out.toByteArray();
	}

	public static byte[] readFileToBytes(File file) {
		try {
			return Files.readAllBytes(file.toPath());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String getFileType(File file) {
		String extension = getExtension(file.getName());
		if (custommimetypes.containsKey(extension))
			return custommimetypes.get(extension);
		try {
			String content = Files.probeContentType(file.toPath());
			if (content != null)
				return content;
			else
				return "application/octet-stream";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "text/plain";
	}

	public static boolean isImage(File file) {
		return getFileType(file).contains("image");
	}

	public static String getImageType(File file) {
		return getFileType(file).split("/")[1];
	}

	public static boolean isBinaryFile(File file) {
		try {
			FileInputStream in = new FileInputStream(file);
			int size = in.available();
			if (size > 1024)
				size = 1024;
			byte[] data = new byte[size];
			in.read(data);
			in.close();
			int ascii = 0;
			int other = 0;
			for (int i = 0; i < data.length; i++) {
				byte b = data[i];
				if (b < 0x09) {
					return true;
				}
				if (b == 0x09 || b == 0x0A || b == 0x0C || b == 0x0D) {
					ascii++;
				} else if (b >= 0x20 && b <= 0x7E) {
					ascii++;
				} else {
					other++;
				}
			}
			if (other == 0) {
				return false;
			}
			return 100 * other / (ascii + other) > 95;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static String getExtension(String filename) {
		if (filename.contains(".")) {
			return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
		} else {
			return "";
		}
	}
}
