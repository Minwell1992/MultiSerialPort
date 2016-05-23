package com.wxc.util;

import gnu.io.*;

import java.io.*;
/**
 * ���Ӷ˿ڣ�����ȡ�ö˿ڵ�IO
 * @author Minwell
 * ���캯�����մ�����
 */
public class OpenSerialPort {
	
	private IOObject ioObject = new IOObject();
	private String portName;
	
	public OpenSerialPort(String portName) {
		this.portName = portName;
	}
	
	public IOObject connect() throws Exception {
		CommPortIdentifier portIdentifier = 
				CommPortIdentifier.getPortIdentifier(portName);
		if (portIdentifier.isCurrentlyOwned()) {
			System.err.println(portName + "is in use");
			System.exit(-1);
		} else {
			CommPort commPort = null;
			try {
				commPort = portIdentifier.open(this.getClass().getName(), 2000);
			} catch (Exception e) {
				System.err.println("����" + portName + "��ʧ�ܣ�");
				System.exit(-1);
			}
			System.out.println("�ɹ��򿪴���" + portName);
			if (commPort instanceof SerialPort) {
				SerialPort serialPort = (SerialPort) commPort;
				serialPort.setSerialPortParams(9600,
												SerialPort.DATABITS_8,
												SerialPort.STOPBITS_1,
												SerialPort.PARITY_NONE);
				InputStream in = serialPort.getInputStream();
				OutputStream out = serialPort.getOutputStream();
				
				ioObject.setIn(in);
				ioObject.setOut(out);
				
				//(new Thread(new SystemInInputStream(in))).start();
				//(new Thread(new SerialWriterClass(out))).start();
				//Com1Out.setOut(out);
			} else {
				System.err.println(portName + "is not serial port");
			}
		}
		return ioObject;
	}
}
