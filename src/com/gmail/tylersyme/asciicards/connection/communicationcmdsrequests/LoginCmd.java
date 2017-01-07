package com.gmail.tylersyme.asciicards.connection.communicationcmdsrequests;

import com.gmail.tylersyme.asciicards.connection.ClientConnection;
import com.gmail.tylersyme.asciicards.events.EventHandler;
import com.gmail.tylersyme.asciicards.events.LoginEvent;

public class LoginCmd extends ServerCommand
{
	LoginCmd() 
	{
		super();
		
		this.setName("login_cmd");
	}

	@Override
	public void execute(ClientConnection client, String... args)
	{
		boolean isSuccessful;
		String username = null;
		if (isSuccessful = Boolean.parseBoolean(args[0]))
		{
			username = args[1];
		}
		
		// Call Login Event
		EventHandler.getHandler().callLoginEvent(
				new LoginEvent(isSuccessful, username));
	}
}
