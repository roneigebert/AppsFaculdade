package com.appsfaculdade.chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.appsfaculdade.chat.util.ConsoleUtil;

import lombok.val;

public class Server extends Thread {
    
	private final Socket connection;
	private final PrintStream output;
	private final BufferedReader input;
	
	private static final Map<String, PrintStream> clients = new HashMap<>();
    private static final List<String> namesList = new ArrayList<String>();
    
    private String clientName;

    public Server( final Socket connection ) throws IOException {
		this.connection = connection;
		val inputReader = new InputStreamReader( this.connection.getInputStream() );
		input = new BufferedReader( inputReader );
        output = new PrintStream(this.connection.getOutputStream());
        this.clientName = ConsoleUtil.readLine( input );
	}

    public void run() {
        try {
        	if ( !storeName() ){
        		processInvalidName();
        		return;
        	}
        	processServer();
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	exitMessage();
        	namesList.remove( this.clientName );
        	closeConnection();
        }
    }
    
    private void closeConnection() {
		try{
			this.connection.close();
		} catch ( Exception e ){
			e.printStackTrace();
		}
	}

	private void exitMessage() {
		try{
			send(output, " is exit!", "");
		} catch ( Exception e ){
			e.printStackTrace();
		}
		
	}

	private void processServer() throws Exception{
    	System.out.println( this.clientName + " : Connected on server" );
        showConnectedClients();
        clients.put(this.clientName, this.output);
        String inputLine;
        while ( (inputLine = input.readLine()) != null )
        	send(output, " > ", inputLine);
    }

	private void processInvalidName() throws IOException{
    	output.println("This name is already exists!!");
        return;
    }
    
    public boolean storeName() {
        for (int i = 0; i < namesList.size(); i++) 
            if (namesList.get(i).equals(this.clientName))
                return false;
        namesList.add(this.clientName);
        return true;
    }

    public void send( final PrintStream output, final String action, final String message ){
        for ( val client : clients.entrySet() ) {
            val chat = client.getValue();
            if (chat == output)
            	continue;
            chat.println(this.clientName + action + message);
        }
    }

    public void showConnectedClients() {
    	final StringBuilder builder = new StringBuilder( "Active clients: You " );
        for ( val nameList : namesList )
        	if ( !this.clientName.equalsIgnoreCase( nameList ) )
        		builder.append(nameList + " ");
        output.println(builder.toString());
        output.flush();
    	for ( val cliente : clients.entrySet() ){
        	val chat = cliente.getValue();
            chat.println( this.clientName + " is entered!" );
            chat.flush();
        }
    }
   
}