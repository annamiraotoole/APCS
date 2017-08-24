package textExcel1;

import java.util.*;

// TextExcel.java ----------------------------------------------------
public class TextExcel
{

	public static void main(String[] args)
	{
		// creates new spreadsheet first
		Spreadsheet sheet = new Spreadsheet();

		// welcome message
		System.out.println("Welcome to Text Excel!");

		// set up a scanner to get input
		Scanner console = new Scanner(System.in);
		
		// main string of called methods:
		String command1 = getCommand(console);
		doCommands(command1, sheet, console);

		// final quitting message
		System.out.println("Farewell!");
		console.close();
	}
	
	public static String getCommand(Scanner console)
	{
		// here is where you might define a method that gets a command
		// and shows a prompt first (you'd need to pass in the console
		// and return command, of course)
		
		System.out.print("Enter a command: ");
		return console.nextLine();
	}

	
	public static void doCommands(String theCommand, Spreadsheet theSheet, Scanner console)
	{
		// a method for doing something with the command
		
		while (!theCommand.equals("quit"))
		{
			if (theCommand.equals("print"))
			{
				// calling print method from Spreadsheet class
				theSheet.print();
			}
			theCommand = getCommand(console);
		}
	}

}