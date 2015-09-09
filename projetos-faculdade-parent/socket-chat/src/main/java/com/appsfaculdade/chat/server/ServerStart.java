package com.appsfaculdade.chat.server;

import java.net.ServerSocket;
import java.net.Socket;

import com.appsfaculdade.chat.util.Constants;

public class ServerStart {

	private static ServerSocket server;

	public static void main(String args[]) {
		try {
			startServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void startServer() throws Exception {
		server = new ServerSocket(Constants.SERVER_PORT);
		System.out.println("Server starting on port " + Constants.SERVER_PORT);
		while (true) {
			final Socket conection = server.accept();
			final Thread thread = new Server(conection);
			thread.start();
		}
	}

}