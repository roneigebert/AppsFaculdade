package com.appsfaculdade.labirinto.utils;

public class Utils {
	
	public static void sleep(int m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

}
