package com.gmail.tylersyme.asciicards.events;

public interface FriendRequestListener
{
	/**
	 * <p>
	 * Should occur when the server gives the client information about a
	 * friend request which was sent. This means that the event will occur if
	 * either this player sends a request or if a request by another player
	 * is received.
	 * </p>
	 * <p>
	 * <b>Warning:</b> This will occur even if the friend request was not 
	 * successful <b>unless</b> the request was sent by another player to this
	 * player.
	 * </p>
	 * 
	 * @param e Contains basic event information
	 */
	public void friendRequestOccured(FriendRequestEvent e);

}
