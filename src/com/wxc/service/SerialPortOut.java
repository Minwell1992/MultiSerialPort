package com.wxc.service;

import java.io.*;

import com.wxc.util.*;

/**
 * 单例控制输出流，只需控制该例即可控制输出流
 * 
 * @author Minwell
 * 
 */
public class SerialPortOut {
	private static SerialWriterOutputStream com1Out;
	private static SerialWriterOutputStream com2Out;
	private static SerialWriterOutputStream com3Out;
	private static SerialWriterOutputStream com4Out;

	private SerialPortOut() {
	}

	static {
		com1Out = new SerialWriterOutputStream();
		com2Out = new SerialWriterOutputStream();
		com3Out = new SerialWriterOutputStream();
		com4Out = new SerialWriterOutputStream();
	}

	public static void setOut(String portNum, OutputStream out) {
		switch (portNum) {
		case "COM1":
			com1Out.setOut(out);
			break;
		case "COM2" :
			com2Out.setOut(out);
			break;
		case "COM3" :
			com3Out.setOut(out);
			break;
		case "COM4" :
			com4Out.setOut(out);
			break;
		default :
			System.out.println("没有此串口");
			return;
		}
	}

	public static SerialWriterOutputStream getOut(String portNum) {
		switch (portNum) {
		case "COM1":
			return com1Out;
		case "COM2" :
			return com2Out;
		case "COM3" :
			return com3Out;
		case "COM4" :
			return com4Out;
		default :
			System.out.println("没有此串口");
			return null;
		}
	}

}
