package com.gmail.tylersyme.asciicards.connection.communicationcmdsrequests;

import java.util.Optional;

/**
 * Client requests are designed to be sent by the client to the server. A client
 * request's main purpose is to generate a properly formatted packet which can 
 * then be sent to the server.
 */
public abstract class ClientRequest
{
	private String name;
	
	/**
	 * Returns whether the given arguments in the given order are valid for the
	 * {@code ClientRequest}.
	 */
	public abstract boolean validate(String... args);
	
	/**
	 * Converts the request and its arguments into a single {@code String} which
	 * can be output to the Server as a packet request.
	 * 
	 * @param args The arguments, in order, which the request should passed
	 * @return An {@code Optional} which may contain the packet in {@code String}
	 * 		   form or will be empty if the arguments given to it were invalid.
	 */
	public Optional<String> toPacket(String... args)
	{
		Optional<String> packet = Optional.empty();
		if (this.validate(args))
		{
			String packetStr = this.getName();
			for (String arg : args)
			{
				packetStr += ":" + arg;
			}
			
			packet = Optional.of(packetStr);
		}
		
		return packet;
	}
	
// -----------------------------------------------------------------------------
// Getters and Setters
// -----------------------------------------------------------------------------
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
