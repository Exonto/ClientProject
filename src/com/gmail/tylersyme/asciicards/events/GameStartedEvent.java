package com.gmail.tylersyme.asciicards.events;

public class GameStartedEvent
{
	private String opponent;
	
	public GameStartedEvent(String opponent)
	{
		this.opponent = opponent;
	}
	
// -----------------------------------------------------------------------------
// Getters and Setters
// -----------------------------------------------------------------------------

	public String getOpponent()
	{
		return opponent;
	}
	
}
