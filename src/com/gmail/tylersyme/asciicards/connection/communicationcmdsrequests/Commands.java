package com.gmail.tylersyme.asciicards.connection.communicationcmdsrequests;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Set;

import com.gmail.tylersyme.asciicards.connection.ClientConnection;

public class Commands
{
	public static ClientConnection conn;
	
	
	public static final LoginRequest LOGIN_REQUEST = new LoginRequest();
	public static final LoginCmd LOGIN_CMD = new LoginCmd();
	
	public static final RegisterAccountRequest REGISTER_ACCOUNT_REQUEST = new RegisterAccountRequest();
	public static final RegisterAccountCmd REGISTER_ACCOUNT_CMD = new RegisterAccountCmd();
	
	public static final FriendRequestRequest FRIEND_REQUEST_REQUEST = new FriendRequestRequest();
	public static final FriendRequestCmd FRIEND_REQUEST_CMD = new FriendRequestCmd();
	
	public static final FriendAcceptedRequest FRIEND_ACCEPTED_REQUEST = new FriendAcceptedRequest();
	public static final FriendAcceptedCmd FRIEND_ACCEPTED_CMD = new FriendAcceptedCmd();
	
	public static final PlayerProfileRequest PLAYER_PROFILE_REQUEST = new PlayerProfileRequest();
	public static final PlayerProfileCmd PLAYER_PROFILE_CMD = new PlayerProfileCmd();
	
// -----------------------------------------------------------------------------
	
	private Commands() { } // Cannot Instantiate
	
// -----------------------------------------------------------------------------
	
	/**
	 * Maintains a list of distinct {@code ServerCommands}.
	 */
	static Set<ServerCommand> serverCommands; 
	
	/**
	 * Takes an unparsed packet sent by the server and both unparses and executes
	 * the {@code ServerCommand} associated with the command name contained
	 * within the packet.
	 * 
	 * @param client The connection to the server
	 * @param unparsed The unparsed packet in {@code String} form
	 * 
	 * @see ServerCommand
	 */
	public static void executeUnparsed(ClientConnection client, String unparsed)
	{
		String[] parsed = unparsed.split(":");
		
		String cmdName = parsed[0];
		String[] args = Arrays.copyOfRange(parsed, 1, parsed.length);
		
		// Get the server command from the server commands list based upon
		// the cmdName
		ServerCommand serverCmd = 
				serverCommands
				.stream()
				.filter((cmd) -> cmd.getName().equals(cmdName))
				.findFirst()
				.orElseThrow(() -> new NoSuchElementException(
					"The server command \"" + cmdName + "\" does not exist."));
		
		serverCmd.execute(client, args); // Executes the command
	}
	
	/**
	 * Sends the given request to the server.
	 * 
	 * @param request The specified request being sent
	 * @param args The arguments passed along with the request
	 */
	public static void sendRequest(ClientRequest request, String... args)
	{
		conn.sendRequest(request, args);
	}
}












