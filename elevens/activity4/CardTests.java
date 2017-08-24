package activity4;

import static org.junit.Assert.*;
import org.junit.Test;
import testHelp.*;

public class CardTests
{

	@Test
	public void test()
	{
		Card test = new Card("Two", "Hearts", 2);
		verify.that(test).isOfType(Card.class);
	}
	
	@Test
	public void cardShouldReturnRank()
	{
		Card test = new Card("Two", "Hearts", 2);
		verify.that(test.getRank()).isEqualTo("Two");
	}

	@Test
	public void differentValueReturnsFalseForEquals()
	{
		Card a = new Card("Ace", "Spades", 1);
		Card b = new Card("Ace", "Spades", 2);
		verify.that(a.equals(b)).isFalse();
	}
	
	@Test
	public void differentSuitReturnsFalseForEquals()
	{
		Card a = new Card("Ace", "Spades", 1);
		Card b = new Card("Ace", "Diamonds", 1);
		verify.that(a.equals(b)).isFalse();
	}
	
	@Test
	public void differentRankReturnsFalseForEquals()
	{
		Card a = new Card("Ace", "Spades", 1);
		Card b = new Card("Nine", "Spades", 1);
		verify.that(a.equals(b)).isFalse();
	}
	
	@Test
	public void onlySameRankReturnsFalseForEquals()
	{
		Card a = new Card("Ace", "Spades", 1);
		Card b = new Card("Ace", "Clubs", 2);
		verify.that(a.equals(b)).isFalse();
	}
	
	@Test
	public void onlySameSuitReturnsFalseForEquals()
	{
		Card a = new Card("Ace", "Spades", 1);
		Card b = new Card("Eight", "Spades", 2);
		verify.that(a.equals(b)).isFalse();
	}
	
	@Test
	public void onlySameValueReturnsFalseForEquals()
	{
		Card a = new Card("Ace", "Spades", 1);
		Card b = new Card("Eight", "Clubs", 1);
		verify.that(a.equals(b)).isFalse();
	}
	
	@Test
	public void returnsEqualForEquals()
	{
		Card a = new Card("Ace", "Spades", 1);
		Card b = new Card("Ace", "Spades", 1);
		verify.that(a.equals(b)).isTrue();
	}
	
	@Test
	public void returnsFalseForTotallyDifferent()
	{
		Card a = new Card("Ace", "Spades", 1);
		Card b = new Card("Two", "Clubs", 2);
		verify.that(a.equals(b)).isFalse();
	}
	
	@Test
	public void returnsFalseWhenObjectIsNotACard()
	{
		Object test = new Object();
		verify.that(test instanceof Card).isFalse();
	}
	
	@Test
	public void returnsFalseWhenCardIsNull()
	{
		Card test = new Card("Ace", "Spades", 4);
		verify.that(test.getRank() == null && test.getSuit() == null && test.getValue() == -1).isFalse();
	}
	
	@Test
	public void returnsTrueWhenComparedToItself()
	{
		Card a = new Card("Ace", "Spades", 1);
		verify.that(a.equals(a)).isTrue();
	}
	
	@Test
	public void returnsProperValueForToString()
	{
		Card a = new Card("Ace", "Spades", 1);
		verify.that(a.toString().equals("Ace of Spades (point value = 1)")).isTrue();
	}
	
}
