package com.wxc.util;

import java.io.IOException;
import java.io.OutputStream;

public class SystemInOutputStream implements Runnable {
		private OutputStream out;
		
		public SystemInOutputStream(OutputStream out) {
			this.out = out;
		}

		@Override
		public void run() {
			try {
				int c = 0;
				while ((c = System.in.read()) > -1) {
					this.out.write(c);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
