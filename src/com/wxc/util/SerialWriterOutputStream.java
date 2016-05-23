package com.wxc.util;

import java.io.*;

/**
 * 输出流，实现写的功能，为单例做准备
 * @author Minwell
 *
 */
public class SerialWriterOutputStream {
	private OutputStream out = null;

	public OutputStream getOut() {
		return out;
	}

	public void setOut(OutputStream out) {
		this.out = out;
	}
	
	public void write(String str) {
		byte[] b = str.getBytes();
		try {
			out.write(b);
			out.write(10);//new byte[]{13, 10}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
