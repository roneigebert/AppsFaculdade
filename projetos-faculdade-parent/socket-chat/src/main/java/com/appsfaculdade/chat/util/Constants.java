package com.appsfaculdade.chat.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import lombok.val;

public class Constants {
	
	public static int SERVER_PORT = 5555;
	
	public static String getHost(){
		try{
			val keyboard = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("What is the server host? \n>> ");
			val ipAddress = ConsoleUtil.readLine( keyboard );
			return ipAddress;
		} catch ( Exception e ){
			System.out.println( "Host invalido!!!" );
			e.printStackTrace();
			return getHost();
		}
	}

}
