package com.gmail.tylersyme.asciicards.game.cards;

public abstract class Card implements Cloneable
{
	
	protected String qualifiedName; // The name which appears in the database
	protected String title; // The title which appears at the top of the card
	protected String description;
	protected String quote;
	protected String cardType;
	protected String manaType;
	protected int manaCost;
	protected boolean isEnabled = true;
	
	@Override
	public abstract Object clone();
	
// -----------------------------------------------------------------------------
// Getters and Setters
// -----------------------------------------------------------------------------
	
	public String getQualifiedName()
	{
		return qualifiedName;
	}
	
	public String getTitle()
	{
		return title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getQuote()
	{
		return quote;
	}

	public void setQuote(String quote)
	{
		this.quote = quote;
	}

	public String getCardType()
	{
		return cardType;
	}

	public void setCardType(String cardType)
	{
		this.cardType = cardType;
	}

	public String getManaType()
	{
		return manaType;
	}

	public void setManaType(String manaType)
	{
		this.manaType = manaType;
	}

	public int getManaCost()
	{
		return manaCost;
	}

	public void setManaCost(int manaCost)
	{
		this.manaCost = manaCost;
	}

	public boolean isEnabled()
	{
		return isEnabled;
	}
}
