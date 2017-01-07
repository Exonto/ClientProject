package com.gmail.tylersyme.asciicards.game;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gmail.tylersyme.asciicards.game.cards.Card;

/**
 * <p>
 * This is designed to cache general player information which should be loaded
 * upon the player's login event and updated if a change should occur. 
 * </p>
 * <p>
 * This is <b>not</b> used for storing temporary gameplay data. This should
 * only cache the permanent, long term user information which is kept by the 
 * server's database.
 * </p>
 */
public class PlayerProfile
{
	// The player's username
	private String username;
	
	// Whether the player has logged in
	private boolean isLoggedIn = false;
	
	// The player's friends
	private Set<String> friends = new HashSet<>();
	
	// Stores the list of requests sent by this player to other players
	private Set<String> sentFriendRequests = new HashSet<>();
	
	// Stores the list of requests sent by other players to this player
	private Set<String> receivedFriendRequests = new HashSet<>();
	
	private Set<Card> cardCollection = new HashSet<>();
	
	public PlayerProfile() { }
	
	/**
	 * Causes the player to be considered "Logged In" and requires a username
	 * to be specified.
	 * 
	 * @param username The username the player logged into
	 */
	public void login(String username)
	{
		this.isLoggedIn = true;
		this.username = username;
	}
	
// -----------------------------------------------------------------------------
// Friends and Friend Requests
// -----------------------------------------------------------------------------
	
	/**
	 * Adds a new friend to the friends list.
	 * 
	 * @param friendUsername The new friend's username
	 */
	public void addFriend(String friendUsername)
	{
		this.friends.add(friendUsername);
	}
	
	/**
	 * <p>
	 * Adds a new friend request to this player's profile. 
	 * </p>
	 * 
	 * @param sender The request sender's username
	 * @param receiver The request receiver's username
	 */
	public void addFriendRequest(String sender, String receiver)
	{
		if (this.username.equals(sender))
		{
			// If this player is the sender, store the player who received the
			// request
			this.sentFriendRequests.add(receiver);
		} else {
			// If this player is the receiver, store the player who sent the
			// request
			this.receivedFriendRequests.add(sender);
		}
	}
	
	public void removeFriendRequest(String sender, String receiver)
	{
		if (this.username.equals(sender))
		{
			// If this player is the sender, store the player who received the
			// request
			this.sentFriendRequests.remove(receiver);
		} else {
			// If this player is the receiver, store the player who sent the
			// request
			this.receivedFriendRequests.remove(sender);
		}
	}
	
	public void addFriends(List<String> friends)
	{
		this.friends.addAll(friends);
	}
	
	public void addSentFriendRequests(List<String> sentFriendRequests)
	{
		this.sentFriendRequests.addAll(sentFriendRequests);
	}
	public void addReceivedFriendRequests(List<String> receivedFriendRequests)
	{
		this.receivedFriendRequests.addAll(receivedFriendRequests);
	}
	
	/**
	 * This is used to convert all request receivers into an array. This was
	 * designed to fulfill a {@code JList's} requirements for changing its
	 * contents.
	 * 
	 * @return A String array of usernames who received this player's requests
	 */
	public String[] getSentFriendRequestsAsArray()
	{
		String[] receivers = new String[this.sentFriendRequests.size()];
		receivers = this.sentFriendRequests.toArray(receivers);
		
		return receivers;
	}
	
	/**
	 * This is used to convert all request senders into an array. This was
	 * designed to fulfill a {@code JList's} requirements for changing its
	 * contents.
	 * 
	 * @return A String array of usernames who sent this player requests
	 */
	public String[] getReceivedFriendRequestsAsArray()
	{
		String[] senders = new String[this.receivedFriendRequests.size()];
		senders = this.receivedFriendRequests.toArray(senders);
		
		return senders;
	}
	
// -----------------------------------------------------------------------------
// Getters and Setters
// -----------------------------------------------------------------------------

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public Set<String> getFriends()
	{
		return this.friends;
	}

	/**
	 * <p>
	 * Returns whether the player has been logged in.
	 * </p>
	 * 
	 */
	public boolean isLoggedIn()
	{
		return isLoggedIn;
	}
}
