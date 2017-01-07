package com.gmail.tylersyme.asciicards.events;

public interface RegisterAccountListener
{
	/**
	 * Should occur when the server gives the client a registration specific message.<br>
	 * <b>Warning:</b> This will occur even if the registration was not successful.
	 * 
	 * @param e Contains basic event information
	 */
	public void registerAccount(RegisterAccountEvent e);

}
