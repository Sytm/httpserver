package de.sytm.httpserver.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.stream.ImageInputStream;

public class ByteBuf implements Iterable<byte[]> {

	private static final long ONEGiB = 1024 * 1024 * 1024;

	private long size;
	private byte[][] data;

	public ByteBuf(long size) {
		this.size = size;
		if (size == 0) {
			data = new byte[0][];
		} else {
			int parts = (int) (size / ONEGiB);

			int remainder = (int) (size - ((long) parts) * ONEGiB);

			data = new byte[parts + (remainder == 0 ? 0 : 1)][];

			for (int index = parts; index >= 0; --index) {
				data[index] = new byte[(int) ONEGiB];
			}

			if (remainder != 0) {
				data[parts] = new byte[remainder];
			}
		}
	}

	public void read(InputStream in) throws IOException {
		if (size == 0) {
			return;
		}
		for (int index = 0; index < data.length; ++index) {
			if (in.read(data[index]) != data[index].length) {
				throw new IOException("short read");
			}
		}
		in.close();
	}
	
	public void read(ImageInputStream in) throws IOException {
		if (size == 0) {
			return;
		}
		for (int index = 0; index < data.length; ++index) {
			if (in.read(data[index]) != data[index].length) {
				throw new IOException("short read");
			}
		}
		in.close();
	}

	public long size() {
		return size;
	}

	@Override
	public Iterator<byte[]> iterator() {
		return new Iterator<byte[]>() {

			private byte[][] data = ByteBuf.this.data.clone();
			private int cursor = 0;

			@Override
			public byte[] next() {
				return data[cursor++];
			}

			@Override
			public boolean hasNext() {
				return cursor < data.length;
			}
		};
	}
}
