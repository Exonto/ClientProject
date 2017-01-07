package com.gmail.tylersyme.asciicards.game;


public class Player
{
	// The player's general information
	private PlayerProfile profile;
	
	public Player() 
	{ 
		this.profile = new PlayerProfile();
	}
	public Player(PlayerProfile profile) 
	{ 
		this.profile = profile;
	}
	
// -----------------------------------------------------------------------------
// Getters and Setters
// -----------------------------------------------------------------------------
	
	public PlayerProfile getProfile()
	{
		return profile;
	}

	public void setProfile(PlayerProfile profile)
	{
		this.profile = profile;
	}
	
	// Quick access method
	public String getUsername()
	{
		return this.profile.getUsername();
	}
	
}
