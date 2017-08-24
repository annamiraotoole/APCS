package activity4;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;
import java.util.Arrays;
import testHelp.verify;

public class ElevensBoardUnitTests
{
	@Test
	public void NewGameDealsBoard()
	{
		ElevensBoard b = new ElevensBoard();
		b.newGame();
		for (int i = 0; i < 9; i++){
			verify.that(b.onBoard[i]).isOfType(Card.class);
		}
	}

	@Test
	public void IsEmptyReturnsTrueForEmptyBoard()
	{
		ElevensBoard b = new ElevensBoard();
		verify.that(b.isEmpty());
	}

	@Test
	public void IsEmptyReturnsFalseForBoardWithCards()
	{
		ElevensBoard b = new ElevensBoard();
		b.newGame();
		verify.that(b.isEmpty()).isFalse();
	}

	@Test
	public void DealDealsCardToCorrectPosition()
	{
		ElevensBoard b = new ElevensBoard();
		b.deal(0);
		verify.that(b.onBoard[0]).isOfType(Card.class);
	}

	@Test
	public void GetCardReturnsCorrectCard()
	{
		ElevensBoard b = new ElevensBoard();
		b.deal(0);
		verify.that(b.onBoard[0].equals(b.getCard(0)));
	}
	
	@Test
	public void ReplaceSelectedCardsReplacesInputPositions()
	{
		ElevensBoard b = new ElevensBoard();
		b.newGame();
		Card[] initialBoard = b.onBoard.clone();
		b.replaceSelectedCards(Arrays.asList(0, 1, 2));
		for (int i = 0; i < 3; i++){
			verify.that(b.onBoard[i].equals(initialBoard[i])).isFalse();
		}
		for (int j = 3; j < b.onBoard.length; j++){
			verify.that(b.onBoard[j].equals(initialBoard[j]));
		}
	}
	
	@Test
	public void GetCardIndicesReturnsCorrectIndices()
	{
		ElevensBoard b = new ElevensBoard();
		b.newGame();
		for (int i = 1; i < b.onBoard.length; i++){
			b.onBoard[i] = null;
		}
		verify.that(b.getCardIndices().contains(0));
	}
	
	@Test
	public void GameIsWonReturnsTrueForEmptyDeckAndBoard()
	{
		ElevensBoard b = new ElevensBoard();
		for (Card c : b.cards.deck){
			b.cards.deal();
		}
		verify.that(b.gameIsWon());
	}
	
	@Test
	public void GameIsWonReturnsFalseForOnlyEmptyDeck()
	{
		ElevensBoard b = new ElevensBoard();
		b.newGame();
		for (Card c : b.cards.deck){
			b.cards.deal();
		}
		verify.that(b.gameIsWon()).isFalse();
	}
	
	@Test
	public void GameIsWonReturnsFalseForOnlyEmptyBoard()
	{
		ElevensBoard b = new ElevensBoard();
		verify.that(b.gameIsWon()).isFalse();
	}
	
	@Test
	public void IsLegalReturnsTrueForSumOf11()
	{
		ElevensBoard b = new ElevensBoard();
		for (int i = 0; i < b.onBoard.length; i++){
			b.onBoard[i] = b.cards.deal();
		}
		List<Integer> list = Arrays.asList(1, 8);
		verify.that(b.isLegal(list));
	}
	
	@Test
	public void IsLegalReturnsTrueForLegalFaceCards()
	{
		ElevensBoard b = new ElevensBoard();
		for (int i = 0; i < 2; i++){
			for (int j = 0; j < b.onBoard.length; j++){
				b.onBoard[j] = b.cards.deal();
			}
		}
		List<Integer> list = Arrays.asList(1, 2, 3);
		verify.that(b.isLegal(list));
	}
	
	@Test
	public void IsLegalReturnsFalseForSumOf10()
	{
		ElevensBoard b = new ElevensBoard();
		for (int i = 0; i < b.onBoard.length; i++){
			b.onBoard[i] = b.cards.deal();
		}
		List<Integer> list = Arrays.asList(2, 8);
		verify.that(b.isLegal(list)).isFalse();
	}
	
	@Test
	public void IsLegalReturnsTrueForIllegalFaceCards()
	{
		ElevensBoard b = new ElevensBoard();
		for (int i = 0; i < 2; i++){
			for (int j = 0; j < b.onBoard.length; j++){
				b.onBoard[j] = b.cards.deal();
			}
		}
		for (int k = 3; k < b.onBoard.length; k++){
			b.onBoard[k] = b.cards.deal();
		}
		List<Integer> list = Arrays.asList(1, 2, 8);
		verify.that(b.isLegal(list)).isFalse();
	}
	
	@Test
	public void AnotherPlayIsPossibleReturnsTrue()
	{
		ElevensBoard b = new ElevensBoard();
		for (int i = 0; i < b.onBoard.length; i++){
			b.onBoard[i] = b.cards.deal();
		}
		verify.that(b.anotherPlayIsPossible());
	}
	
	@Test
	public void AnotherPlayIsPossibleReturnsFalse()
	{
		ElevensBoard b = new ElevensBoard();
		b.onBoard[0] = b.cards.deal();
		for (int j = 1; j < b.onBoard.length; j++){
			b.onBoard[j] = b.onBoard[0];
		}
		verify.that(b.anotherPlayIsPossible()).isFalse();
	}
	
}

