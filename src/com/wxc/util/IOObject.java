package com.wxc.util;

import java.io.*;

/**
 * IO对象,即输入和输出流
 * @author Minwell
 *
 */
public class IOObject {
	private InputStream in;
	private OutputStream out;
	public InputStream getIn() {
		return in;
	}
	public void setIn(InputStream in) {
		this.in = in;
	}
	public OutputStream getOut() {
		return out;
	}
	public void setOut(OutputStream out) {
		this.out = out;
	}
	
	
}
