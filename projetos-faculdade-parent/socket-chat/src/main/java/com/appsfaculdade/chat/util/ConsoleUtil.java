package com.appsfaculdade.chat.util;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleUtil {
	
	public static String readLine( final BufferedReader reader ) throws IOException{
		String line = null;
		while ( line == null || line.isEmpty() )
			line = reader.readLine();
		return line.trim();
	}

}
