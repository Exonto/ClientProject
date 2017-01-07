package com.gmail.tylersyme.asciicards.connection.communicationcmdsrequests;

import java.util.List;

import com.gmail.tylersyme.asciicards.connection.ClientConnection;
import com.gmail.tylersyme.asciicards.events.EventHandler;
import com.gmail.tylersyme.asciicards.events.PlayerProfileUpdateEvent;

public class PlayerProfileCmd extends ServerCommand
{
	PlayerProfileCmd() 
	{
		super();
		
		this.setName("player_profile_cmd");
	}

	@Override
	public void execute(ClientConnection client, String... args)
	{
		List<String> sentFriendRequestsList = ArgumentHelper.convertToList(args[0]);
		List<String> receivedFriendRequestsList = ArgumentHelper.convertToList(args[1]);
		List<String> friends = ArgumentHelper.convertToList(args[2]);
		List<String> cardAttributes = ArgumentHelper.convertToList(args[3]);
		
		// Call player profile update event
		EventHandler.getHandler().callPlayerProfileUpdateEvent(
				new PlayerProfileUpdateEvent(
						sentFriendRequestsList, 
						receivedFriendRequestsList, 
						friends,
						cardAttributes));
	}
}
