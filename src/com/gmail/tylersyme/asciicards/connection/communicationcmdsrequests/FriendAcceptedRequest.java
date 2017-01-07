package com.gmail.tylersyme.asciicards.connection.communicationcmdsrequests;


public class FriendAcceptedRequest extends ClientRequest
{
	FriendAcceptedRequest() 
	{ 
		this.setName("friend_accepted_request");
	}

	@Override
	public boolean validate(String... args)
	{
		return args.length == 1;
	}
}
