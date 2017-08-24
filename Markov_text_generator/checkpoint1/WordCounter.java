package MTG1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class WordCounter implements TextAnalyzer
{
	HashMap<String, Integer> words = new HashMap<String, Integer>();

	public WordCounter(String filename) throws FileNotFoundException
	{
		FileReader file = new FileReader(filename);
		Scanner fileScanner = new Scanner(file);
		
		HashMap<String, Integer> hold = new HashMap<String, Integer>();
		
		while (fileScanner.hasNext())
		{
			String word = fileScanner.next();
			if (hold.containsKey(word))
			{
				int numberOfTimes = hold.get(word);
				hold.put(word, numberOfTimes + 1);
			}
			else
			{
				hold.put(word, 1);
			}
		}
		
		words = hold;
	}
	
	@Override
	public int getWordCounts(String word)
	{
		return words.get(word);
	}

	@Override
	public boolean startsWithCapitalLetter(String word)
	{
		Character letter = word.charAt(0);
		return Character.isUpperCase(letter);
	}

	@Override
	public boolean isSentenceEndingPunctuation(String word)
	{
		if (word == "." || word == "?" || word == "!")
			return true;
		return false;
	}

	@Override
	public ArrayList<String> getWordsThatCouldComeNext(String prevWord)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getAllWordsThatStartWithACapitalLetter()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numberOfSentences()
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
