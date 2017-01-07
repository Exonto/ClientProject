package com.gmail.tylersyme.asciicards.connection.communicationcmdsrequests;


public class PlayerProfileRequest extends ClientRequest
{
	PlayerProfileRequest() 
	{
		this.setName("player_profile_request");
	}

	@Override
	public boolean validate(String... args)
	{
		return true;
	}
}
