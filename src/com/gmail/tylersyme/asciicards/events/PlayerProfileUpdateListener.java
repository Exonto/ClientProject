package com.gmail.tylersyme.asciicards.events;

public interface PlayerProfileUpdateListener
{
	/**
	 * <p>
	 * Should occur when the server sends the client an player profile update.
	 * </p>
	 * 
	 * @param e Contains basic event information
	 */
	public void updatePlayerProfile(PlayerProfileUpdateEvent e);

}
