package textExcel4;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class FormulaCell extends Cell
{
	private String expression;
	private Spreadsheet sheet;
	
	public FormulaCell(String expression, Spreadsheet theSheet)
	{
		super(expression);
		sheet = theSheet;
		this.expression = expression;
	}
	
	public String getDisplayValue()
	{
		return "" + getNumberValue();
	}
	
	private static ArrayList<String> convertToList(String str)
	{
		
		ArrayList<String> myList = new ArrayList<String>();
		str = str.substring(1, str.length() - 1);
		while (str.contains(" "))
		{
			myList.add(str.substring(0, str.indexOf(" ")));
			str = str.substring(str.indexOf(" ") + 1);
		}
		myList.add(str);
		
		return myList;
	}

	private static int findIndexOfNextOperator(ArrayList<String> tokens)
	{
		int index;
		for (int i = 0; i < tokens.size(); i++)
		{
			if (tokens.get(i).equals("*") || tokens.get(i).equals("/"))
			{
				index = i;
				return index;
			}
		}
		for (int i = 0; i < tokens.size(); i++)
		{
			if (tokens.get(i).equals("+") || tokens.get(i).equals("-"))
			{
				index = i;
				return index;
			}
		}
		throw new InvalidParameterException("No operator found in token list");
	}

	private static String calculate(String left, String op, String right)
	{
		double numL = Double.parseDouble(left);
		double numR = Double.parseDouble(right);
		
		double myResult;
		
		if (op.equals("+"))
		{
			myResult = numL + numR;
			return "" + myResult;
		}
		if (op.equals("-"))
		{
			myResult = numL - numR;
			return "" + myResult;
		}
		if (op.equals("*"))
		{
			myResult = numL * numR;
			return "" + myResult;
		}
		if (op.equals("/"))
		{
			myResult = numL / numR;
			return "" + myResult;
		}
		
		throw new InvalidParameterException("'" + op + "' is not a valid operator");
	}

	private static void replaceInList(ArrayList<String> items, int firstIndex, int howManyItems, String newValue)
	{
		items.set(firstIndex, newValue);
		for (int i = 1; i < howManyItems; i++)
		{
			items.remove(firstIndex + 1);
		}
	}
	
	public boolean isCellReference(String ref)
	{
		if (ref == null || ref.length() < 2 || ref.length() > 3)
			return false;
		
		if (ref.charAt(0) < 'A' || ref.charAt(0) > 'A' + Spreadsheet.COLS)
			return false;
		
		int row = Integer.parseInt(ref.substring(1));
		if (row < 1 || row > Spreadsheet.ROWS)
			return false;
		
		return true;
	}
	
	public void resolveCellReferences(ArrayList<String> list, Spreadsheet sheet)
	{
		
		for (int i = 0; i < list.size(); i++)
		{
			if (sheet.isCellReference(list.get(i)))
			{
				String ref = list.get(i);
				int row = sheet.getRow(ref);
				int col = sheet.getCol(ref);
				list.set(i, "" + sheet.data[row][col].getDisplayValue());
			}
		}
	}
	
	public void resolveSumAvg(ArrayList<String> list, Spreadsheet sheet)
	{
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i).contains("sum"))
			{
				list.set(i + 1, "" + sheet.getSum(list.get(i + 1)));
				list.remove(i);
			}
			else if (list.get(i).contains("avg"))
			{
				list.set(i + 1, "" + ( sheet.getSum(list.get(i + 1)) / sheet.countCellsInRange(list.get(i + 1))) );
				list.remove(i);
			}
		}
	}
	
	public double getNumberValue()
	{
		ArrayList<String> tokens = convertToList(expression);
		resolveSumAvg(tokens, sheet);
		resolveCellReferences(tokens, sheet);

		while (tokens.size() > 1)
		{
			int operatorIndex = findIndexOfNextOperator(tokens);
			
			String leftOperand = tokens.get(operatorIndex - 1);
			String operator = tokens.get(operatorIndex);
			String rightOperand = tokens.get(operatorIndex + 1);
			String result = calculate(leftOperand, operator, rightOperand);
			
			replaceInList(tokens, operatorIndex - 1, 3, result);
		}
		
		return Double.parseDouble(tokens.get(0));
		
	}
	
}
