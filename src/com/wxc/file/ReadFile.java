package com.wxc.file;

import java.io.*;

/**
 * 读取txt文件
 */
public class ReadFile {
	String filename;
	
	public ReadFile(String filename) {
		super();
		this.filename = filename;
	}
	
	public  void readFile() {
		BufferedReader in;
		String str;
		String[] subStrs;
		try {
			in = new BufferedReader(new FileReader(filename));
			while ((str = in.readLine()) != null) {
				//System.out.println(s);
				if (str.equals("$$")) {
					//结束符$$
					System.out.println("END");
					System.exit(0);
				}
				subStrs = str.split("\\$");
				for (String subStr : subStrs) {
					String com = subStr.substring(0, 2);
					String send = subStr.substring(2, subStr.length());
					System.out.println(com + ":" +send);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new ReadFile("H:\\test.txt").readFile();
	}
}
