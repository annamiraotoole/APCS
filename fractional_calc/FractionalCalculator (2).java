//Annamira O'Toole 10/17/2015

package fracCalc4;

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
			printResult(convertToMixed(reduce(calculate(operand1, operator, operand2))));
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
	
	public static void printResult(String input)
	{
		//prints the calculated result
		System.out.println(input);
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
	
	public static String calculate(String operand1, String operator, String operand2)
	{
		int num1 = Integer.parseInt(operand1.substring(0, operand1.indexOf("/")));
		int num2 = Integer.parseInt(operand2.substring(0, operand2.indexOf("/")));
		int den1 = Integer.parseInt(operand1.substring(operand1.indexOf("/")+1));
		int den2 = Integer.parseInt(operand2.substring(operand2.indexOf("/")+1));
		
		if (operator.contains("+"))
		{
			return num1 * den2 + num2 * den1 + "/" + den1 * den2;
		}
		if (operator.contains("-"))
		{
			return num1 * den2 - num2 * den1 + "/" + den1 * den2;
		}
		if (operator.contains("*"))
		{
			return num1 * num2 + "/" + den1 * den2;
		}
		else
		{
			return num1 * den2 + "/" + den1 * num2;
		}
	}
	
	public static int gcf(int a, int b)
	{
		//greatest common factor method
		
		int greatestSoFar = 1;
		
		for (int i = 1; i <= a; i++)
		{
			if (a % i == 0 && b % i == 0)
			{
				greatestSoFar = i;
			}
		}
		return greatestSoFar;
	}
	
	public static String reduce(String fraction)
	{
		//reduces positive fractions
		if ( !checkNegative(fraction) )
		{
			int num = Integer.parseInt(fraction.substring(0, fraction.indexOf("/")));
			int den = Integer.parseInt(fraction.substring(fraction.indexOf("/")+1));
			int gcf = gcf(num, den);
			return num / gcf + "/" + den / gcf;
		}
		//reduces negative fractions
		else
		{
			int num = Integer.parseInt(fraction.substring(fraction.indexOf("-")+1, fraction.indexOf("/")));
			int den = Integer.parseInt(fraction.substring(fraction.indexOf("/")+1));
			int gcf = gcf(num, den);
			return "-" + num / gcf + "/" + den / gcf;
		}
	}
	
	public static String convertToMixed(String fraction)
	{
		//converts negatives
		if ( checkNegative(fraction) )
		{
			int num = Integer.parseInt(fraction.substring(fraction.indexOf("-")+1, fraction.indexOf("/")));
			int den = Integer.parseInt(fraction.substring(fraction.indexOf("/")+1));
			
			//converts into whole number
			if (num % den == 0)
			{
				return "-" + Integer.toString(num / den);
			}
			//returns normal fraction
			if (num < den)
			{
				return fraction;
			}
			//converts into mixed number: if (num > den)
			else 
			{
				int whole = num / den;
				int newNum = num % den;
				return "-" + whole + "_" + newNum + "/" + den;
			}
		}
		
		//converts positives
		else
		{
			int num = Integer.parseInt(fraction.substring(0, fraction.indexOf("/")));
			int den = Integer.parseInt(fraction.substring(fraction.indexOf("/")+1));
			
			//converts into whole number
			if (num % den == 0)
			{
				return Integer.toString(num / den);
			}
			//returns normal fraction
			if (num < den)
			{
				return fraction;
			}
			//converts into mixed number: if (num > den)
			else 
			{
				int whole = num / den;
				int newNum = num % den;
				return whole + "_" + newNum + "/" + den;
			}
		}
		
	}
	
}
