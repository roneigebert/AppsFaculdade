package com.appsfaculdade.binaryconverters;

import java.util.Scanner;

public class Utils {

	private static final Scanner consoleInput = new Scanner(System.in);
	
	public static String readText(final String message){
		System.out.println(message);
		return readText();
	}
	
	public static int readInt(final String message){
		System.out.println(message);
		return readInt();
	}
	
	public static int readInt(){
		try {
			return Integer.parseInt(readText());
		} catch (final Exception e){
			System.out.println("Valor inválido!");
			return readInt();
		}
	}
	
	public static String readText(){
		return consoleInput.nextLine();
	}
	
}
