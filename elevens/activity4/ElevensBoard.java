package activity4;

import java.util.ArrayList;
import java.util.List;

public class ElevensBoard implements IBoard
{
    Deck cards;
    Card[] onBoard = new Card[9];
    
    public ElevensBoard()
    {
        String[] suits = {"Diamonds", "Hearts", "Spades", "Clubs"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        cards = new Deck(ranks, suits, values);
    }
    
    public ElevensBoard(Deck d)
    {
        cards = d;
    }

	@Override
	public void newGame()
	{
		cards.shuffle();
		for (int i = 0; i < onBoard.length; i++)
		{
			onBoard[i] = cards.deal();
		}
	}

	@Override
	public int getBoardSize() //TODO
	{
		return onBoard.length;
	}

	@Override
	public boolean isEmpty()
	{
		for (int i = 0; i < onBoard.length; i++)
		{
			if (!(onBoard[i] == null))
				return false;
		}
		return true;
	}

	@Override
	public void deal(int k)
	{
		onBoard[k] = cards.deal();	
	}

	@Override
	public int getCardsLeftInDeck()
	{
		return cards.getCardsLeft();
	}

	@Override
	public Card getCard(int k)
	{
		return onBoard[k];
	}

	@Override
	public void replaceSelectedCards(List<Integer> selectedCards)
	{
		for (int i = 0; i < selectedCards.size(); i++){
			deal(selectedCards.get(i));
		}
		
	}

	@Override
	public List<Integer> getCardIndices()
	{
		List<Integer> indices = new ArrayList<Integer>();
		for (int i = 0; i < onBoard.length; i++)
		{
			if (!(onBoard[i] == null))
				indices.add(i);
		}
		return indices;
	}

	@Override
	public boolean gameIsWon()
	{
		if (cards.getCardsLeft() == 0 && cards.isEmpty() == true)
			return true;
		return false;
	}

	@Override
	public boolean isLegal(List<Integer> selectedCards)
	{
		int card1 = onBoard[selectedCards.get(0)].getValue();
		int card2 = onBoard[selectedCards.get(1)].getValue();
		if (selectedCards.size() == 2){
			if (card1 + card2 == 11)
				return true;
		}
		if (selectedCards.size() == 3){
			int card3 = onBoard[selectedCards.get(2)].getValue();
			int[] arr = {card1, card2, card3};
			insertionSort(arr);
			if (arr[0] == 11 && arr[1] == 12 && arr[2] == 13)
				return true;
		}
		return false;
	}

	@Override
	public boolean anotherPlayIsPossible()
	{
		for (int i = 0; i < onBoard.length; i++){
			for (int j = i + 1; j < onBoard.length; j++){
				int card1 = onBoard[i].getValue();
				int card2 = onBoard[j].getValue();
				if (card1 + card2 == 11)
					return true;
			}
		}
		for (int i = 0; i < onBoard.length; i++){
			for (int j = i + 1; j < onBoard.length; j++){
				for (int k = j + 1; k < onBoard.length; k++){
					int card1 = onBoard[i].getValue();
					int card2 = onBoard[j].getValue();
					int card3 = onBoard[k].getValue();
					int[] arr = {card1, card2, card3};
					insertionSort(arr);
					if (arr[0] == 11 && arr[1] == 12 && arr[2] == 13)
						return true;
				}
			}
		}
		return false;
	}

	//used in required methods: isLegal, anotherPlayIsPossible
	private static void insertionSort(int[] numbers)
	{
		for (int i = 1; i < numbers.length; i++)
		{
			
			int value = numbers[i];
			for (int k = i - 1; k >= 0; k--)
			{
				if (value > numbers[k])
				{
					numbers[k + 1] = value;
					break;
				}
				else
				{
					int hold = numbers[k + 1];
					numbers[k + 1] = numbers[k];
					numbers[k] = hold;
				}
			}
		}
	}
	
	
}