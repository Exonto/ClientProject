package com.gmail.tylersyme.asciicards.connection.communicationcmdsrequests;

import com.gmail.tylersyme.asciicards.connection.ClientConnection;
import com.gmail.tylersyme.asciicards.events.EventHandler;
import com.gmail.tylersyme.asciicards.events.RegisterAccountEvent;

public class RegisterAccountCmd extends ServerCommand
{
	RegisterAccountCmd() 
	{
		super();
		
		this.setName("register_account_cmd");
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
		
		// Call Register Event
		EventHandler.getHandler().callRegisterAccountEvent(
				new RegisterAccountEvent(isSuccessful, username));
	}
}
