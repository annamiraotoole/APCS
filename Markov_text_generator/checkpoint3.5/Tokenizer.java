package MTGtokenizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Tokenizer
{

	public Tokenizer(String filename) throws FileNotFoundException
	{
		File outfile = new File(filename.substring(0, filename.length() - 4) + "_tokenized.txt");
		FileReader file = new FileReader(filename);
		Scanner fileScanner = new Scanner(file);
		PrintStream output = new PrintStream(outfile);
		
		output.print( tokenizedStr( tokens(filename) ) );
	}
	
	public ArrayList<String> tokens(String filename) throws FileNotFoundException
	{
		FileReader file = new FileReader(filename);
		Scanner fileScanner = new Scanner(file);
		ArrayList<String> list = new ArrayList<String>();
		while (fileScanner.hasNext()){
			list.add(fileScanner.next());
		}
		
		for (int pos = 0; pos < list.size(); pos++){
			String token = list.get(pos);
			char lastChar = token.charAt(token.length() - 1);
			if (!Character.isAlphabetic(lastChar)){
				list.set(pos, token.substring(0, token.length() - 1));
				list.add(pos + 1, lastChar + "");
				pos++;
			}
		}
		
		return list;
	}
	
	public String tokenizedStr(ArrayList<String> list)
	{
		String str = "";
		str += list.get(0);
		for (int i = 1; i < list.size(); i++){
			str += " " + list.get(i);
		}
		
		return str;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		
		Scanner console = new Scanner(System.in);
		
		System.out.println("Enter filename:");
		String name = console.next();
		Tokenizer Elton = new Tokenizer(name);
		
		System.out.println(Elton.tokenizedStr(Elton.tokens(name)));
	}
}

