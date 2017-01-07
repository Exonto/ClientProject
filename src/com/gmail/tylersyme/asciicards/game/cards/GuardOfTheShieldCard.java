package com.gmail.tylersyme.asciicards.game.cards;

public class GuardOfTheShieldCard extends CreatureCard
{
	public GuardOfTheShieldCard()
	{
		
	}
	
	public GuardOfTheShieldCard(CreatureCard toCopy)
	{
		super(toCopy);
	}
	
	@Override
	public Object clone()
	{
		return new GuardOfTheShieldCard(this);
	}

}
