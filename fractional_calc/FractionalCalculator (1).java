//Annamira O'Toole 10/9/15

package fracCalc2;

import java.util.Scanner;

public class FractionalCalculator
{
	
	public static void main(String[] args)
	{
		//variables:
		String expression;
		String operand1;
		String operand2;
		String operator;
		Scanner console = new Scanner(System.in);
		System.out.println("Welcome to the Fraction Calculator!");
		
		expression = getInput(console);
		while (!expression.equals("quit"))
		{
			operand1 = getOperand1(expression);
			operator = getOperator(expression);
			operand2 = getOperand2(expression);
			operand1 = convertToFraction(operand1);
			operand2 = convertToFraction(operand2);
			printParts(operand1, operator, operand2);
			expression = getInput(console);
		}
		
		System.out.println("Goodbye!");
	}

	public static String getInput(Scanner console)
	{
		//asks for an expression from human,
		//and sets the string variable called expression to the human's input
		
		
		System.out.print("Enter an expression (or " + "\"quit\"" + "): ");
		return console.nextLine();
	}
	
	public static String getOperand1(String str)
	{
		return str.substring(0, str.indexOf(" "));
	}
	
	public static String getOperator(String str)
	{
		return str.substring(str.indexOf(" ")+1, str.lastIndexOf(" "));
	}
	
	public static String getOperand2(String str)
	{
		return str.substring(str.lastIndexOf(" ")+1);
	}
	
	public static void printParts(String operand1, String operator, String operand2)
	{
		//prints the left operand, operator, and right operand
		
		System.out.println("Left operand: " + operand1);
		System.out.println("Operator: " + operator);
		System.out.println("Right operand: " + operand2);
	}
	
	public static String convertToFraction(String input)
	{
		//checks what type of number was entered
		//calls respective converting method
		//returns converted input
		
		if (input.contains("_"))
		{
			return convertMixed(input);
		}
		else if (!input.contains("/"))
		{
			return convertWhole(input);
		}
		else
		{
			return input;
		}
	}
	
	public static String convertMixed(String input)
	{
		//find _ and / and break inputing parameter into the whole, num, and den
		//then rewrites input as a fraction
		//deals with negatives
		
		int underscore = input.indexOf("_");
		int slash = input.indexOf("/");
		
		if ( checkNegative(input) )
		{
			int whole = Integer.parseInt(input.substring(1, underscore));
			int num = Integer.parseInt(input.substring(underscore+1, slash));
			int den = Integer.parseInt(input.substring(slash+1));
			int numOfWholeConverted = whole * den;
			return "-" + (numOfWholeConverted + num) + "/" + den;	
		}
		else
		{
			int whole = Integer.parseInt(input.substring(0, underscore));
			int num = Integer.parseInt(input.substring(underscore+1, slash));
			int den = Integer.parseInt(input.substring(slash+1));
			int numOfWholeConverted = whole * den;
			return (numOfWholeConverted + num) + "/" + den;	
		}
	}
	
	public static String convertWhole(String input)
	{
		//converts integer into a fraction form
		
		if ( checkNegative(input) )
		{
			int whole = Integer.parseInt(input.substring(1));
			return "-" + whole + "/" + "1";
		}
		else
		{
			int whole = Integer.parseInt(input);
			return whole + "/" + "1";
		}
	}
	
	public static boolean checkNegative(String input)
	{
		//checks if input is negative and returns true if so
		
		if (input.contains("-"))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
}
