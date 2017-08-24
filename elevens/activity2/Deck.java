package activity2; 
import java.util.ArrayList;

/**
 * The Deck class represents a shuffled deck of cards.
 * It provides several operations including
 *      initialize, shuffle, deal, and check if empty.
 */
public class Deck 
{
	
	public Card[] deck;
	public int pos;
	public int deckSize;
	
	/**
	 * Creates a new Deck instance.

	 * It pairs each element of ranks with each element of suits,
	 * and produces one of the corresponding card.
	 * @param ranks is an array containing all of the card ranks.
	 * @param suits is an array containing all of the card suits.
	 * @param values is an array containing all of the card point values.
	 */
	public Deck(String[] ranks, String[] suits, int[] values) 
	{
		int place = 0;
		deckSize = values.length * suits.length;
		deck = new Card[deckSize];
		for (int i = 0; i < suits.length; i++)
		{
			for (int j = 0; j < values.length; j++)
			{
				Card test = new Card( ranks[j], suits[i], values[j] );
				deck[place] = test;
				place++;
			}
		}
	}

	/**
	 * Determines if this deck is empty (no undealt cards).
	 * @return true if this deck is empty, false otherwise.
	 */
	public boolean isEmpty() 
	{
		if (deck.length > 0)
			return false;
		else
			return true;
	}

	/**
	 * Accesses the number of undealt cards in this deck.
	 * @return the number of undealt cards in this deck.
	 */
	public int getCardsLeft() 
	{
		return deck.length - pos;
	}

	/**
	 * Randomly permute the given collection of cards
	 * and reset the size to represent the entire deck.
	 */
	public void shuffle()
	{
		for (int j = 0; j < deck.length; j++)
		{
			int first = (int)(Math.random() * deckSize);
			int second = (int)(Math.random() * deckSize);
			swap(first, second);
		}
		pos = 0;
	}

	/**
	 * Deals a card from this deck.
	 * @return the card just dealt, or null if all the cards have been
	 *         previously dealt.
	 */
	public Card deal() 
	{
		if (pos >= deck.length)
			return null;
		Card dealtCard = deck[pos];
		pos++;
		return dealtCard;
	}
	
	public void swap(int first, int second)
	{
		Card hold = deck[first];
		deck[first] = deck[second];
		deck[second] = hold;
	}
}