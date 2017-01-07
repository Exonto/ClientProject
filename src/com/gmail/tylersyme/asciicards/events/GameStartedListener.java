package com.gmail.tylersyme.asciicards.events;

public interface GameStartedListener
{
	/**
	 * Should occur when the server pairs any two opponents together and 
	 * a game is started.
	 * 
	 * @param e Contains basic event information
	 */
	public void gameStarted(FriendAcceptedEvent e);

}
