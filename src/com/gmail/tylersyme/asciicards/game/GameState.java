package com.gmail.tylersyme.asciicards.game;

import java.util.List;

import com.gmail.tylersyme.asciicards.game.cards.CardFactory;

/**
 * <p>
 * This is used to represent a card game currently being played. It should be 
 * used as the primary module for handling game logic and updates.
 * </p>
 * <p>
 * Graphics should <b>not</b> be drawn using this class but should instead be
 * used by the related {@code JFrame} or {@code JPanel} in order to provide
 * crucial information about what needs to be drawn.
 * </p>
 */
public class GameState
{
	private static CardFactory cardFactory;
	
	/**
	 * Returns the standard card factory which is used to cache all important
	 * information about each card in the game. New card instances may only be
	 * created using this or another card factory. 
	 * 
	 * @see GameState#initializeCardFactory(CardFactory)
	 * 
	 * @throws NullPointerException If the factory has not been initialized
	 */
	public static CardFactory getCardFactory() 
	{ 
		if (GameState.cardFactory == null)
		{
			throw new NullPointerException(
					"The card factory has not been initialize.");
		}
		
		return cardFactory; 
	}
	
	/**
	 * The static {@code CardFactory} class should be loaded from an outside
	 * source. This is done to allow an outside resource loader to initialize 
	 * the factory <b>just once</b> during runtime. This should occur at or 
	 * before the user logs in to ensure that all required card data has been
	 * cached.
	 * 
	 * @see CardFactory#CardFactory(List)
	 * 
	 * @param factory The factory to be initialized with
	 * @throws IllegalStateException If the factory has already been initialized
	 */
	public static void initializeCardFactory(CardFactory cardFactory)
	{
		// Throws an exception if card factory was already initialized
		if (GameState.cardFactory != null) 
		{
			throw new IllegalStateException(
					"The card factory has already been initialized.");
		}
		
		GameState.cardFactory = cardFactory;
	}
	
	private Player player;
	private Player opponent;
	
	public GameState() { }
	public GameState(Player player, Player opponent)
	{
		this.player = player;
		this.opponent = opponent;
	}
	
// -----------------------------------------------------------------------------
// Getters and Setters
// -----------------------------------------------------------------------------
	
	public Player getPlayer()
	{
		return player;
	}
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	public Player getOpponent()
	{
		return opponent;
	}

}
