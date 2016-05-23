package com.wxc.util;

import java.io.*;
/**
 * 阻塞监听InputStream
 * @author Minwell
 */
public class SerialReaderInputStream implements Runnable{
	private InputStream in;
	File path;
	
	public SerialReaderInputStream(InputStream in, String name) {
		this.in = in;
		this.path = new File(name);
	}
	
	public SerialReaderInputStream(InputStream in) {
		this.in = in;
	}

	@Override
	public void run() {
		byte[] buffer = new byte[1024];
		int len = -1;
		StringBuilder sb = new StringBuilder("");
		try {
			while (!Thread.currentThread().isInterrupted() && 
					(len = this.in.read(buffer)) > -1) {
				sb.append(new String(buffer, 0, len));
			}
			if (path == null) {
				System.out.println("结束线程\n" + sb);
			} else {
				PrintWriter out = new PrintWriter(path);
				String str = new String(sb);
				out.println(str);
				out.close();
				System.out.println("接收文件已存入：" + path);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
