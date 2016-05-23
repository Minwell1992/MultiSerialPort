package com.wxc.ctrl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.wxc.service.*;
import com.wxc.util.*;


/**
 * ��4�����ڷֱ������ݣ�
 * COM1���ź�Դ��COM2������Դ��COM3����Ƭ����COM4��ת̨��
 * @author Minwell
 *
 */
public class SerialPortCtrl {
	//��������ļ�·�������ļ�·��
	static String SAVEPATH = "H:\\a.txt",
			OPENPATH = "H:\\test.txt";
	//��������
	Thread input;
	
	public void initialPort() throws Exception {
		//��ȡÿ������IO
		IOObject ioCom1 = new OpenSerialPort("COM1").connect();
		IOObject ioCom2 = new OpenSerialPort("COM2").connect();
		IOObject ioCom3 = new OpenSerialPort("COM3").connect();
		IOObject ioCom4 = new OpenSerialPort("COM4").connect();
		//������ע��out
		SerialPortOut.setOut("COM1", ioCom1.getOut());
		SerialPortOut.setOut("COM2", ioCom2.getOut());
		SerialPortOut.setOut("COM3", ioCom3.getOut());
		SerialPortOut.setOut("COM4", ioCom4.getOut());
		//�����߳�
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
					System.out.println("���ͽ�����");
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
			System.out.println("�ļ�����");
		}
		try {
			TimeUnit.MILLISECONDS.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		//��ʼ�������Լ�IO��
		SerialPortCtrl spc = new SerialPortCtrl();
		spc.initialPort();
		spc.sendFile(OPENPATH);
		
		TimeUnit.SECONDS.sleep(1);
		System.exit(0);
		
	}
}
