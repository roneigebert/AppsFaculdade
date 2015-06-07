package com.appsfaculdade.labirinto.utils;

public class Utils {
	
	public static void sleep(final int millis) {
		try {
			Thread.sleep(millis);
		} catch (final InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
