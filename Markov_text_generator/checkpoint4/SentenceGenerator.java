package MTG4;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SentenceGenerator
{
    TextAnalyzer counter;
    Random random;

    public SentenceGenerator(String filename) throws FileNotFoundException
    {
        counter = new WordCounter(filename);
        this.random = new Random();
    }

    public SentenceGenerator(String filename, Random random) throws FileNotFoundException
    {
        counter = new WordCounter(filename);
        this.random = random;
    }

    // given any word, randomly choose a word that could come next, using the
    // TextAnalyzer wordsThatCouldComeNext
    // return that sentence as a String
    public String randomlyChooseNextWord(String prevWord)
    {
    	ArrayList<String> list = counter.getWordsThatCouldComeNext(prevWord);
    	return randomlySelectWord(list);
    }

    // given a starting word, generate a random word that could come next, and a
    // random word that could come after that, etc.
    // keep going until you generate punctuation that could end a sentence, as
    // determined by TextAnalyzer isSentenceEndingPunctuation
    public String generateRandomSentence(String firstWord)
    {
        String sentence = firstWord;
        String add = randomlyChooseNextWord(firstWord);
        while (!(add.equals(".") || add.equals("!") || add.equals("?"))){
        	if (add.equals(",")){
        		sentence += add;
        	}
        	else{
        		sentence += " " + add;
        	}
        	add = randomlyChooseNextWord(add);
        }
        sentence += add;
        return sentence;
    }

    // randomly choose a word that starts with a capital letter, and use that as
    // the first word of the sentence
    // then randomly generate the rest of the sentence using
    // generateRandomSentence(String firstWord)
    public String generateRandomSentence()
    {
    	ArrayList<String> list = counter.getAllWordsThatStartWithACapitalLetter();
    	return generateRandomSentence(randomlySelectWord(list));
    }

    public String generateRandomSentenceWithoutDuplicates(String firstWord) throws NullPointerException
    {
    	HashMap<String, Integer> used = new HashMap<String, Integer>();
    	String sentence = firstWord;
    	
    	String add = randomlyChooseNextWord(firstWord);
    	used.put(add, 0);
    	
    	try{
    		 while (!(add.equals(".") || add.equals("!") || add.equals("?"))){
    	        	if (add.equals(",")){
    	        		sentence += add;
    	        	}
    	        	else{
    	        		sentence += " " + add;
    	        	}
    	        	int num = 0;
    	        	while (used.containsKey(add) || num < 200){
    	            	add = randomlyChooseNextWord(add);
    	            	num++;
    	        	}
    	        	used.put(add, 0);
    	        }
    	        sentence += add;
    	        return sentence;
    	}
        catch (Exception e){
        	return sentence;
        }
    }
    
    public String generateRandomSentenceWithoutDuplicates()
    {
    	ArrayList<String> list = counter.getAllWordsThatStartWithACapitalLetter();
    	return generateRandomSentenceWithoutDuplicates(randomlySelectWord(list));
    }
    
    private String randomlySelectWord(ArrayList<String> possibleWords)
    {
        int randomIndex = random.nextInt(possibleWords.size());
        return possibleWords.get(randomIndex);
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        SentenceGenerator sg = new SentenceGenerator("alice_tokenized.txt");

        System.out.println(sg.generateRandomSentenceWithoutDuplicates());
        System.out.println(sg.generateRandomSentenceWithoutDuplicates());
        System.out.println(sg.generateRandomSentenceWithoutDuplicates());
        System.out.println(sg.generateRandomSentenceWithoutDuplicates());
        System.out.println("The End");
    }
}