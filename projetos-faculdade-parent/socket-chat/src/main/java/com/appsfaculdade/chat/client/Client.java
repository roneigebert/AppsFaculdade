package com.appsfaculdade.chat.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import com.appsfaculdade.chat.util.ConsoleUtil;

import lombok.AllArgsConstructor;
import lombok.val;

@AllArgsConstructor
public class Client extends Thread {

	private final  Socket connection;

	public void run() {
		try {
			loopMessages();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loopMessages() throws Exception {
		val input = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
		while (true){
			val message = ConsoleUtil.readLine( input );
			System.out.println( "\n" + message);
			System.out.print(">> ");
		}
	}

}
