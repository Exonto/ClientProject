package com.gmail.tylersyme.asciicards.events;

public class RegisterAccountEvent
{
	private boolean successful;
	private String username;
	
	public RegisterAccountEvent(boolean successful, String username)
	{
		this.successful = successful;
		this.username = username;
	}
	
// -----------------------------------------------------------------------------
// Getters and Setters
// -----------------------------------------------------------------------------

	/**
	 * Returns whether the registration attempt was successful.
	 */
	public boolean isSuccessful()
	{
		return successful;
	}
	
	public String getUsername()
	{
		return username;
	}
	
}
