package com.gmail.tylersyme.asciicards.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.gmail.tylersyme.asciicards.connection.communicationcmdsrequests.ClientRequest;
import com.gmail.tylersyme.asciicards.connection.communicationcmdsrequests.Commands;

public class ClientConnection extends Thread
{
	private Socket client;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String ipAddress = "localhost"; // Put external ip address here
	private int port = 45552;
	
	public ClientConnection()
	{
		
	}
	
	@Override
	public void run()
	{
		System.out.println("Attempting Connection..");
		
		// Handle Connection Requests
		boolean connected = connectToServer();
		if (connected)
		{
			System.out.println("Successfully connected..");
			
			// Establish output and input streams for communication
			try
			{
				System.out.println("Establish Communication Streams..");
				output = new ObjectOutputStream(this.client.getOutputStream());
				output.flush();
				
				input = new ObjectInputStream(this.client.getInputStream());
				System.out.println("Communication Streams Successfully Deployed..");
				
				
			} catch (SocketException connEnded) {
				
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			
			boolean isConnected = true;
			
			// Begin listening for Server commands
			String command;
			while (isConnected)
			{
				try
				{
					command = (String) input.readObject();
					
					// Process client requests
					System.out.println("Received Command: " + command);
					this.process(command);
				} catch (SocketException connEnded) {
					System.out.println(
							"Connection to " 
							+ this.ipAddress 
							+ " was Dropped");

					isConnected = false; 
				} catch (ClassNotFoundException e)
				{
					e.printStackTrace();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("Could not connect to " + this.ipAddress);
		}
	}
	
// -----------------------------------------------------------------------------
// IO Communication Between Server and Client
// -----------------------------------------------------------------------------

	/**
	 * When a player attempts to login, a connection request must first be 
	 * accepted. Following this, a login request should be received by the 
	 * server. If the login attempt is successful, the connection will remain.
	 * If, however, the login attempt fails, the connection will be dropped.
	 */
	private boolean connectToServer()
	{
		try
		{
			client = new Socket(InetAddress.getByName(this.ipAddress), this.port);
			return true;
		} catch (UnknownHostException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
		
	public void sendRequest(ClientRequest request, String... args)
	{
		try
		{
			this.output.writeObject(request.toPacket(args).orElseThrow(
					() -> new IllegalArgumentException(
							"The arguments supplied "
							+ "to this request were invalid.")));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void process(String serverCommand)
	{
		Commands.executeUnparsed(this, serverCommand);
	}
	
}














