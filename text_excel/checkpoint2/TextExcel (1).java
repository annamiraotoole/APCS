package textExcel2;

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
		if (theCommand.equals(""))
		{
			return;
		}
		while (!theCommand.equals("quit"))
		{
			if (theCommand.equals("print"))
			{
				// calling print method from Spreadsheet class
				theSheet.print();
			}
			else if (theCommand.contains("="))
			{
				// user wants to set cell value
				// INCLUDES EXTRA CREDIT
				try
				{
					CellRef cell = new CellRef(theCommand.substring(0, theCommand.indexOf('=')-1));
					theSheet.assign(cell, theCommand.substring(2+theCommand.indexOf("=")));
				}
				catch (Exception e)
				{
					System.out.println("Invalid command");
					System.out.print("Please enter a different command: ");
					theCommand = console.nextLine();
					CellRef cell = new CellRef(theCommand.substring(0, theCommand.indexOf('=')-1));
					theSheet.assign(cell, theCommand.substring(2+theCommand.indexOf("=")));
				}
			}
			else
			{
				// user wants to know value of cell
				// INCLUDES EXTRA CREDIT
				boolean quit = false;
				while (quit == false)
				{
					try
					{
						CellRef cell = new CellRef(theCommand);
						quit = true;
						if (theSheet.data[cell.row][cell.col] == null)
						{
							System.out.println(cell + " = <empty>");
						}
						else
						{
							System.out.println(cell + " = " + theSheet.data[cell.row][cell.col].getValue());
						}
						
					}
					catch (Exception e)
					{
						System.out.println("Invalid command");
						System.out.print("Please enter a different command: ");
						theCommand = console.nextLine();
						CellRef cell = new CellRef(theCommand);
					}
				}
			}
			
			// program has executed the command from user and asks for another
			theCommand = getCommand(console);
		}
	}

}