package com.gmail.tylersyme.asciicards.events;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * This is designed to be the "manager" of any events which occur throughout the
 * program. It handles lists of classes which desire to be alerted when a 
 * certain event occurs.
 * </p>
 * <p>
 * If a class wishes to be alerted whenever a certain event occurs, it must 
 * first ask this {@code EventHandler} to be added to the alert list. Whenever
 * that particular event occurs, this handler will alert every class listening 
 * for that event.
 * </p>
 * <p>
 * It is acceptable to have a class which calls an event and also listens for
 * that same event. 
 * </p>
 */
public class EventHandler
{
	private static EventHandler handler; // The static instance
	
	/**
	 * Returns a static instance of this handler.
	 */
	public static EventHandler getHandler()
	{
		if (handler == null) 
		{
			handler = new EventHandler();
		}
		
		return handler;
	}
	
// -----------------------------------------------------------------------------
	
	private Set<LoginListener> loginListeners = new HashSet<>();
	private Set<RegisterAccountListener> registerAccountListeners = new HashSet<>();
	private Set<FriendRequestListener> friendRequestListeners = new HashSet<>();
	private Set<FriendAcceptedListener> friendAcceptedListeners = new HashSet<>();
	private Set<PlayerProfileUpdateListener> playerProfileUpdateListeners = new HashSet<>();
	
	private EventHandler() { }
	
// -----------------------------------------------------------------------------
	
	public void callLoginEvent(LoginEvent e)
	{
		for (LoginListener l : loginListeners)
		{
			l.login(e);
		}
	}
	
	public void addLoginListener(LoginListener listener)
	{
		this.loginListeners.add(listener);
	}
	
// -----------------------------------------------------------------------------
	
	public void callRegisterAccountEvent(RegisterAccountEvent e)
	{
		for (RegisterAccountListener l : registerAccountListeners)
		{
			l.registerAccount(e);
		}
	}
	
	public void addRegisterAccountListener(RegisterAccountListener listener)
	{
		this.registerAccountListeners.add(listener);
	}
	
// -----------------------------------------------------------------------------

	public void callFriendRequestEvent(FriendRequestEvent e)
	{
		for (FriendRequestListener l : friendRequestListeners)
		{
			l.friendRequestOccured(e);
		}
	}
	
	public void addFriendRequestListener(FriendRequestListener listener)
	{
		this.friendRequestListeners.add(listener);
	}
	
// -----------------------------------------------------------------------------

	public void callFriendAcceptanceEvent(FriendAcceptedEvent e)
	{
		for (FriendAcceptedListener l : friendAcceptedListeners)
		{
			l.friendAcceptanceOccurred(e);
		}
	}
	
	public void addFriendAcceptanceListener(FriendAcceptedListener listener)
	{
		this.friendAcceptedListeners.add(listener);
	}
	
// -----------------------------------------------------------------------------

	public void callPlayerProfileUpdateEvent(PlayerProfileUpdateEvent e)
	{
		for (PlayerProfileUpdateListener l : playerProfileUpdateListeners)
		{
			l.updatePlayerProfile(e);
		}
	}
	
	public void addPlayerProfileUpdateListener(PlayerProfileUpdateListener listener)
	{
		this.playerProfileUpdateListeners.add(listener);
	}

}









