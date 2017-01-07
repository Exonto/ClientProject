package com.gmail.tylersyme.asciicards.events;

public class FriendAcceptedEvent
{
	private boolean successful;
	private String sender;
	private String receiver;
	
	public FriendAcceptedEvent(boolean successful, String sender, String receiver)
	{
		this.successful = successful;
		this.sender = sender;
		this.receiver = receiver;
	}
	
// -----------------------------------------------------------------------------
// Getters and Setters
// -----------------------------------------------------------------------------

	/**
	 * Returns whether the friend acceptance attempt was successful.
	 */
	public boolean isSuccessful()
	{
		return successful;
	}

	public String getSender()
	{
		return sender;
	}

	public String getReceiver()
	{
		return receiver;
	}
	
}
