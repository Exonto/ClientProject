package com.gmail.tylersyme.asciicards.connection.communicationcmdsrequests;


public class FriendRequestRequest extends ClientRequest
{
	FriendRequestRequest() 
	{ 
		this.setName("friend_request_request");
	}

	@Override
	public boolean validate(String... args)
	{
		return args.length == 1;
	}
}
