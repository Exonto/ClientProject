package com.gmail.tylersyme.asciicards.connection.communicationcmdsrequests;


public class LoginRequest extends ClientRequest
{
	LoginRequest() 
	{
		this.setName("login_request");
	}

	@Override
	public boolean validate(String... args)
	{
		return args.length == 2;
	}
}
