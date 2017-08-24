package activity2;
import testHelp.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class DeckTests
{
	
	@Test	
	 public	void DeckConstructorShouldHandleSingleCard()	
	 {	
	 	 Deck d =	new	Deck( new String[] {"TheRank"}, new String[] {"TheSuit"}, new int[] {1});	
	 	 verify.that(d.getCardsLeft()).isEqualTo(1);	
	 }
	
	@Test
	public void DealShouldReturnCorrectCard()
	{
		Card expected = new Card("rank", "suit", 1);
		
		Deck d = new Deck( new String[] {"rank"}, new String[] {"suit"}, new int[] {1});
	
		Card dealt = d.deal();	
	 	verify.that(dealt.equals(expected));
	}

	@Test
	public void DeckIsEmptyPostBeingDealt()
	{
		Deck d = new Deck( new String[] {"a", "b", "c"}, new String[] {"female", "male"}, new int[] {1, 2, 3} );
		for (int i = 0; i < 6; i++)
		{
			d.deal();
		}
		verify.that(d.getCardsLeft() == 0);
	}
	
	@Test
	public void SizeIsOneLessAfterDealingOnce()
	{
		Deck d = new Deck( new String[] {"a", "b", "c"}, new String[] {"female", "male"}, new int[] {1, 2, 3});
		int initialSize = d.getCardsLeft();
		d.deal();
		verify.that( (initialSize - d.getCardsLeft()) == 1 );
	}
	
	@Test
	public void DealShouldDealAllCards()//TODO
	{
		Deck d = new Deck( new String[] {"a", "b", "c"}, new String[] {"female"}, new int[] {1, 2, 3});
		Card[] hold = new Card[3];
		int i = 0;
		while (!(d.getCardsLeft() == 0))
		{
			hold[i] = d.deal();
			i++;
		}
		verify.that(hold[0].toString().equals("a of female (point value = 1)"));
		verify.that(hold[1].toString().equals("b of female (point value = 2)"));
		verify.that(hold[2].toString().equals("c of female (point value = 3)"));
	}			
				
	@Test
	public void DealReturnsNullForEmptyDeck()
	{
		Deck d = new Deck( new String[] {"a", "b", "c"}, new String[] {"female", "male"}, new int[] {1, 2, 3} );
		while (!(d.getCardsLeft() == 0))
		{
			d.deal();
		}
		verify.that(d.deal() == null);
	}
	
	@Test
	public void ShuffleResetsSizeOfDeck()
	{
		Deck d = new Deck( new String[] {"a", "b", "c"}, new String[] {"female", "male"}, new int[] {1, 2, 3} );
		int startSize = d.getCardsLeft();
		d.deal();
		d.deal();
		d.deal();
		verify.that(d.getCardsLeft() == startSize).isFalse();
		d.shuffle();
		verify.that(d.getCardsLeft() == startSize);
	}
	
	@Test
	public void ShuffleChangesOrderOfCard()
	{
		Deck d = new Deck( new String[] {"a", "b", "c"}, new String[] {"female", "male"}, new int[] {1, 2, 3} );
		d.shuffle();
		
		int different = 0;
		
		if (d.deal().toString() == "a of female (point value = 1)") different++;
		if (d.deal().toString() == "b of female (point value = 2)") different++;
		if (d.deal().toString() == "c of female (point value = 3)") different++;
		if (d.deal().toString() == "a of male (point value = 1)") different++;
		if (d.deal().toString() == "b of male (point value = 2)") different++;
		if (d.deal().toString() == "c of male (point value = 3)") different++;
		
		verify.that(different > 2);
	}
}
