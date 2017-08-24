//Annamira O'Toole

package fracCalc1;

import java.util.Scanner;

public class FractionalCalculator
{
	static String expression;
	static String input1;
	static String input2;
	static String operator;
	
	public static void main(String[] args)
	{
		System.out.println("Welcome to the Fraction Calculator!");
		getInput();
		breakInput();
		printParts();
		System.out.println("Goodbye!");
	}

	public static void getInput()
	{
		//asks for an expression from human,
		//and sets the String variable called expression to the human's input
		Scanner console = new Scanner(System.in);
		System.out.println("Enter an expression (or " + "\"quit\"" + "):");
		expression = console.nextLine();
	}
	
	public static void breakInput()
	{
		//breaks the input expression from human into three parts,
		//and assigns each part to its respective String variable
		input1 = expression.substring(0, expression.indexOf(" "));
		operator = expression.substring(expression.indexOf(" ")+1, expression.lastIndexOf(" "));
		input2 = expression.substring(expression.lastIndexOf(" ")+1);
	}
	
	public static void printParts()
	{
		//prints the left operand, operator, and right operand
		System.out.println("Left operand: " + input1);
		System.out.println("Operator: " + operator);
		System.out.println("Right operand: " + input2);
	}
	
}
