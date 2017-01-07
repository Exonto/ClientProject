package com.gmail.tylersyme.asciicards.connection.communicationcmdsrequests;


public class RegisterAccountRequest extends ClientRequest
{
	RegisterAccountRequest() 
	{
		this.setName("register_account_request");
	}
	
	@Override
	public boolean validate(String... args)
	{
		return args.length == 2;
	}
}
