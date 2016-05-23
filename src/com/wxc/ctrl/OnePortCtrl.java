package com.wxc.ctrl;

import java.util.concurrent.*;

import com.wxc.service.*;
import com.wxc.util.*;

/**
 * ²âÊÔÒ»¸ö´®¿Ú
 */
public class OnePortCtrl {

	
	public static void main(String[] args) throws Exception {
		IOObject ioCom1 = new OpenSerialPort("COM1").connect();
		SerialPortOut.setOut("COM1", ioCom1.getOut());
		Thread input = new Thread(new SerialReaderInputStream(ioCom1.getIn()));
		input.start();
		
		SerialPortOut.getOut("COM1").write("SPD3200;");
		TimeUnit.SECONDS.sleep(1);
		SerialPortOut.getOut("COM1").write("STP3200;");
		TimeUnit.SECONDS.sleep(1);
		SerialPortOut.getOut("COM1").write("ENA;");
		TimeUnit.SECONDS.sleep(1);
		SerialPortOut.getOut("COM1").write("STP3200;");
		
		input.interrupt();
		TimeUnit.SECONDS.sleep(1);
		
		System.exit(0);
	}

}
