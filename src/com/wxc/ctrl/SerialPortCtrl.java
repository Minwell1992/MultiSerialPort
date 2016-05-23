package com.wxc.ctrl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.wxc.service.*;
import com.wxc.util.*;


/**
 * 向4个串口分别发送数据；
 * COM1：信号源；COM2：电流源；COM3：单片机；COM4：转台。
 * @author Minwell
 *
 */
public class SerialPortCtrl {
	//保存接收文件路径，打开文件路径
	static String SAVEPATH = "H:\\a.txt",
			OPENPATH = "H:\\test.txt";
	//监听对象
	Thread input;
	
	public void initialPort() throws Exception {
		//获取每个串口IO
		IOObject ioCom1 = new OpenSerialPort("COM1").connect();
		IOObject ioCom2 = new OpenSerialPort("COM2").connect();
		IOObject ioCom3 = new OpenSerialPort("COM3").connect();
		IOObject ioCom4 = new OpenSerialPort("COM4").connect();
		//向单例中注入out
		SerialPortOut.setOut("COM1", ioCom1.getOut());
		SerialPortOut.setOut("COM2", ioCom2.getOut());
		SerialPortOut.setOut("COM3", ioCom3.getOut());
		SerialPortOut.setOut("COM4", ioCom4.getOut());
		//接收线程
		input = new Thread(new SerialReaderInputStream(
				ioCom1.getIn(), SAVEPATH));
		input.start();
	}
	
	public void sendFile(String filename) {
		BufferedReader in;
		String str;
		String[] subStrs;
		try {
			in = new BufferedReader(new FileReader(filename));
			while ((str = in.readLine()) != null) {
				if (str.equals("$$")) {
					System.out.println("发送结束！");
					input.interrupt();
					break;
				}
				subStrs = str.split("\\$");
				for (String subStr : subStrs) {
					sendMsg(subStr);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMsg (String subStr) {
		String com = subStr.substring(0, 2);
		String send = subStr.substring(2, subStr.length());
		if (send.equals(""))
			return;
		switch (com) {
		case "C1" :
			SerialPortOut.getOut("COM1").write(send);
			break;
		case "C2" :
			SerialPortOut.getOut("COM2").write(send);
			break;
		case "C3" :
			SerialPortOut.getOut("COM3").write(send);
			break;
		case "C4" :
			SerialPortOut.getOut("COM4").write(send);
			break;
		default:
			System.out.println("文件错误！");
		}
		try {
			TimeUnit.MILLISECONDS.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		//初始化串口以及IO流
		SerialPortCtrl spc = new SerialPortCtrl();
		spc.initialPort();
		spc.sendFile(OPENPATH);
		
		TimeUnit.SECONDS.sleep(1);
		System.exit(0);
		
	}
}
