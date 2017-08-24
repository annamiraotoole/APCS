package MTG3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class WordCounter implements TextAnalyzer
{
	HashMap<String, Integer> words;
	HashMap<String, ArrayList<String>> nextWords;
	ArrayList<String> capitalWords; 
	int numOfSentences;

	public WordCounter(String filename) throws FileNotFoundException
	{
		FileReader file = new FileReader(filename);
		Scanner fileScanner = new Scanner(file);
		
		words = new HashMap<String, Integer>();
		nextWords = new HashMap<String, ArrayList<String>>();
		capitalWords = new ArrayList<String>();
		numOfSentences = 0;
		
		String prevWord = null;
		
		while (fileScanner.hasNext())
		{
			String word = fileScanner.next();
			
			if (words.containsKey(word)){
				int numberOfTimes = words.get(word);
				words.put(word, numberOfTimes + 1);
			}
			else{
				words.put(word, 1);
			}
			
			if (prevWord != null){
				ArrayList<String> value;
				if (nextWords.containsKey(prevWord)){
					value = nextWords.get(prevWord);
				}
				else{
					value = new ArrayList<String>();
				}
				value.add(word);
				nextWords.put(prevWord, value);
			}
			
			if (startsWithCapitalLetter(word)){
				capitalWords.add(word);
			}
			
			if (isSentenceEndingPunctuation(word)){
				numOfSentences++;
			}
			
			prevWord = word;
		}
		numOfSentences++;
		
	}
	
	@Override
	public int getWordCounts(String word){
		return words.get(word);
	}

	@Override
	public boolean startsWithCapitalLetter(String word){
		Character letter = word.charAt(0);
		return Character.isUpperCase(letter);
	}

	@Override
	public boolean isSentenceEndingPunctuation(String word){
		if (word == "." || word == "?" || word == "!")
			return true;
		return false;
	}

	@Override
	public ArrayList<String> getWordsThatCouldComeNext(String prevWord){
		return nextWords.get(prevWord);
	}

	@Override
	public ArrayList<String> getAllWordsThatStartWithACapitalLetter(){
		return capitalWords;
	}

	@Override
	public int numberOfSentences(){
		return numOfSentences;
	}
	
}
