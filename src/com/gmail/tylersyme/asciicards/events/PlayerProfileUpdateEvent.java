package com.gmail.tylersyme.asciicards.events;

import java.util.ArrayList;
import java.util.List;

/**
 * This event will occur when the server sends the client a player profile
 * update.
 */
public class PlayerProfileUpdateEvent
{
	private List<String> sentFriendRequests = new ArrayList<>();
	private List<String> receivedFriendRequests = new ArrayList<>();
	private List<String> friends = new ArrayList<>();
	private List<String> cardAttributes = new ArrayList<>();
	
	public PlayerProfileUpdateEvent(List<String> sentFriendRequests, 
									List<String> receivedFriendRequests,
									List<String> friends,
									List<String> cardAttributes)
	{
		this.sentFriendRequests = sentFriendRequests;
		this.receivedFriendRequests = receivedFriendRequests;
		this.friends = friends;
		this.cardAttributes = cardAttributes;
	}
	
// -----------------------------------------------------------------------------
// Getters and Setters
// -----------------------------------------------------------------------------

	public List<String> getSentFriendRequests()
	{
		return sentFriendRequests;
	}

	public List<String> getReceivedFriendRequests()
	{
		return receivedFriendRequests;
	}

	public List<String> getFriends()
	{
		return friends;
	}

	public List<String> getCardAttributes()
	{
		return cardAttributes;
	}
}
