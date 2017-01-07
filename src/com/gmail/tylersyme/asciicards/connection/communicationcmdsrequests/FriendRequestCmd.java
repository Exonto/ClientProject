package com.gmail.tylersyme.asciicards.connection.communicationcmdsrequests;

import com.gmail.tylersyme.asciicards.connection.ClientConnection;
import com.gmail.tylersyme.asciicards.events.EventHandler;
import com.gmail.tylersyme.asciicards.events.FriendRequestEvent;

public class FriendRequestCmd extends ServerCommand
{
	FriendRequestCmd() 
	{
		super();
		
		this.setName("friend_request_cmd");
	}

	@Override
	public void execute(ClientConnection client, String... args)
	{
		boolean isSuccessful;
		String sender = null;
		String receiver = null;
		if (isSuccessful = Boolean.parseBoolean(args[0]))
		{
			sender = args[1];
			receiver = args[2];
		}
		
		// Call Register Event
		EventHandler.getHandler().callFriendRequestEvent(
				new FriendRequestEvent(isSuccessful, sender, receiver));
	}
}
