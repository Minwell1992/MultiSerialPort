package com.wxc.file;

import java.io.*;

public class WriteFile {

	/**
	 * Ð´ÈëÎÄ¼þ
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("H:/a.txt");
		PrintWriter out = new PrintWriter(file);
		String s = "12345fsd6789";
		out.println(s);
		out.close();
	}

}
