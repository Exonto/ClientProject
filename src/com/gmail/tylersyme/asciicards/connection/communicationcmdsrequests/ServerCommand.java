package com.gmail.tylersyme.asciicards.connection.communicationcmdsrequests;

import java.util.HashSet;

import com.gmail.tylersyme.asciicards.connection.ClientConnection;
import com.gmail.tylersyme.asciicards.events.EventHandler;

/**
 * <p>
 * A {@code ServerCommand} represents a command which has been sent from the 
 * server to the client. It is up to client to determine how to handle a 
 * command.
 * </p>
 * <p>
 * There is typically a strong bond between {@code ServerCommands} and the
 * {@link EventHandler}. When a server command is received and executed, it 
 * should often also call its corresponding event.<br>
 * For example, if a {@code LoginCmd} is executed it should call a 
 * {@code LoginEvent} prior to any actual code execution (if any). This will
 * alert any subscribed classes that an event has occurred and allows reactions
 * to be made.
 * </p>
 * <p>
 * In addition to calling events, the other purpose of a {@code ServerCommand} 
 * is to parse its arguments in a meaningful way. This ensures that the 
 * arguments being passed are valid.
 * </p>
 */
public abstract class ServerCommand
{
	/**
	 * Represents the name of the command.
	 */
	private String name;
	
	/**
	 * <p>
	 * Causes the command's logic to be executed.
	 * </p>
	 * <p>
	 * If the arguments passed to this method are invalid, the execution will
	 * fail and an exception is thrown.
	 * 
	 * @param client The connection to the server
	 * @param args Contains each {@code String} argument in order
	 */
	public abstract void execute(ClientConnection client, String... args);
	
	protected ServerCommand()
	{
		if (Commands.serverCommands == null)
		{
			Commands.serverCommands = new HashSet<ServerCommand>();
		}
		Commands.serverCommands.add(this);
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
