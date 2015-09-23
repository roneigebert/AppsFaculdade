package com.appsfaculdade.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import com.appsfaculdade.chat.util.ConsoleUtil;
import com.appsfaculdade.chat.util.Constants;

import lombok.val;

public class ClientStart {

	public static void main(String args[]) {
		try {
			startClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void startClient() throws Exception {
		val socket = new Socket(Constants.getHost(), Constants.SERVER_PORT);
		val output = new PrintStream(socket.getOutputStream());
		val keyboard = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("What's your name? \n>> ");
		val name = ConsoleUtil.readLine( keyboard );
		output.println(name.toUpperCase());
		val thread = new Client(socket);
		thread.start();
		while (true)
			sendMessageFromConsoleInput( keyboard, output );
	}

	private static void sendMessageFromConsoleInput( final BufferedReader keyboard, final PrintStream output ) throws IOException{
		System.out.print(">> ");
		val message = ConsoleUtil.readLine( keyboard );
		output.println(message);
	}
	
}
