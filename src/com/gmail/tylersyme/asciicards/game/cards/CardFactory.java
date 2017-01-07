package com.gmail.tylersyme.asciicards.game.cards;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * This factory is used to create new cards using {@code CardFactory} 
 * default templates. Each card will have a default template which can be
 * accessed statically through this {@code CardFactory} class. These templates
 * will ensure that all default card attributes for each individual card are
 * set appropriately before creation.
 * </p>
 * <p>
 * <b>Warning:</b> Templates must be loaded from an outside source before they
 * can be properly used by calling the 
 * {@link CardFactory#loadDefaultTemplates(List)}. This allows for default
 * templates to be loaded from a text file or even changed during runtime, 
 * though this is discouraged.
 * </p>
 */
public class CardFactory
{
	private GuardOfTheShieldCard guardOfTheShield;
	
	private Set<Card> templates = new HashSet<>();
// -----------------------------------------------------------------------------

	public CardFactory(List<Card> defaultTemplates)
	{
		defaultTemplates.forEach((card) -> 
		{
			
			String qualifiedName = card.getQualifiedName();
			
			if (qualifiedName.equals("guard_of_the_shield"))
			{
				this.guardOfTheShield = new GuardOfTheShieldCard((CreatureCard) card);
				this.templates.add(guardOfTheShield);
			} /*else if (qualifiedName.equals("guard_of_the_shield")) {
				
			}*/
			
		});
		
	}
	
// -----------------------------------------------------------------------------
	
	/**
	 * <p>
	 * Takes a template card and returns a cloned copy of the template.
	 * </p>
	 * <p>
	 * {@code CardFactory} contains many default card template which should
	 * be used when creating a new card with default values pre-assigned.
	 * </p>
	 * 
	 * @param template The card to be copied
	 * @return The clones copy of the template
	 */
	public Card createCard(Card template)
	{
		return (Card) template.clone();
	}
	
	public static CreatureCard createGenericCreatureCard(
			String qualifiedName, 
			String title, 
			String description,
			String quote,
			String cardType,
			String manaType,
			int manaCost,
			int health,
			int cooldown,
			int damage,
			boolean isEnabled)
	{
			return new CreatureCard(
					qualifiedName, 
					title,
					description,
					quote,
					cardType,
					manaType,
					manaCost,
					health,
					cooldown,
					damage,
					isEnabled);
	}
	
// -----------------------------------------------------------------------------
// Getters and Setters
// -----------------------------------------------------------------------------

	public GuardOfTheShieldCard getGuardOfTheShield()
	{
		return guardOfTheShield;
	}
	
}













