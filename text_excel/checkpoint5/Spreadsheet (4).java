package textExcel5;

import java.util.Arrays;

/* Spreadsheet stores a 2d array of Cell objects and is able to 
 * modify the array and display it by printing to System.out.
 */
public class Spreadsheet
{
	public final static int COLS = 7;
	public final static int ROWS = 10;
	public final static int CELL_WIDTH = 12;

	// store the cells in a 2D array
	public Cell[][] data;
	
	// construct a new spreadsheet. each cell in 'data' will be
	// null initially, representing an empty cell.
	public Spreadsheet()
	{
		data = new Cell[ROWS][COLS];
	}
	
	// print the spreadsheet to system.out
	public void print()
	{
		printHorizontalLine();
		printColumnHeaders();
		printHorizontalLine();
		
		for (int r = 0; r < ROWS; r++)
		{
			printRow(r);
			printHorizontalLine();
		}
	}
	
	// check to see if 'ref' is a valid cell reference, like A3 or G10. if
	// ref can't be parsed as a column and row, or if it is not in the 
	// appropriate range for this spreadsheet, return false.
	public boolean isCellReference(String ref)
	{
		if (ref == null || ref.length() < 2 || ref.length() > 3)
			return false;
		
		if (ref.charAt(0) < 'A' || ref.charAt(0) > 'A' + COLS)
			return false;
		
		int row = Integer.parseInt(ref.substring(1));
		if (row < 1 || row > ROWS)
			return false;
		
		return true;
	}
	
	// display the value of a single cell, represented by ref, by printing
	// it to system.out.
	public void displayCell(String ref)
	{
		Cell c = data[getRow(ref)][getCol(ref)];
		String value = (c == null) ? "<empty>" : c.getOriginalValue(); // "ternary operator" - bing it :)
		
		System.out.println(ref + " = " + value);
	}
	
	// store a cell at the specified location in the grid, replacing whatever
	// might be there already.
	public void setCell(String ref, Cell cell)
	{
		data[getRow(ref)][getCol(ref)] = cell;
	}
	
	// given a string that is supposed to be a reference to a cell, parse the
	// row index from it (i.e. F4 will return 3 because 3 is the index of the
	// 4th row in 'data').
	public int getRow(String ref)
	{
		if (!isCellReference(ref))
			throw new IllegalArgumentException(ref + " is not a valid cell reference");
		
		return Integer.parseInt(ref.substring(1)) - 1;
	}

	// given a string that is supposed to be a reference to a column, parse
	// the column index from it (e.g. C7 will return 2, since C is the 3rd column
	// and its zero-based index in data would therefore be 2).
	public int getCol(String ref)
	{
		if (!isCellReference(ref))
			throw new IllegalArgumentException(ref + " is not a valid cell reference");
		
		return ref.charAt(0) - 'A';
	}
	
	// print one line of +------------+--- etc.
	private void printHorizontalLine()
	{
		for (int col = 0; col < COLS + 1; col++)
		{
			System.out.print("+");
			for (int ch = 0; ch < CELL_WIDTH; ch++)
				System.out.print("-");
		}
		System.out.println("+");
	}
	
	// print the column header row
	private void printColumnHeaders()
	{
		System.out.print("|" + center("", CELL_WIDTH)); // blank cell at top left
		
		for (int col = 0; col < COLS; col++)
		{
			System.out.print("|");
			System.out.print(center((char)(col + 'A') + "", CELL_WIDTH));
		}
		System.out.println("|");
	}
	
	// print the specified row of cells, including their left and right borders
	private void printRow(int row)
	{
		System.out.print("|" + center(row + 1 + "", CELL_WIDTH)); // the header column
		
		for (int col = 0; col < COLS; col++)
		{
			String value = (data[row][col] == null) ? "" : data[row][col].getDisplayValue();
			System.out.print("|" + center(value, CELL_WIDTH));
		}
		
		System.out.println("|");
	}

	// given a string 'text', truncate or pad it to make it fit exactly in  
	// 'width' characters
	private String center(String text, int width)
	{
		if (text.length() > width)
			return text.substring(0, width - 1) + ">";
		
		String centered = "";
		int leftSpaces = (width - text.length()) / 2;
		for (int c = 0; c < leftSpaces; c++)
			centered += " ";
		centered += text;
		for (int c = centered.length(); c < width; c++)
			centered += " ";
				
		return centered;
	}
	
	public void clearAll()
	{
		for (int i = 0; i < COLS; i++)
		{
			for (int j = 0; j < ROWS; j++)
			{
				data[j][i] = null;
			}
		}
	}
	
	public void clearCell(String ref)
	{
		data[ Integer.parseInt(ref.substring(1)) - 1 ][ ref.charAt(0) - 'A' ] = null;
	}
	
	public double getSum(String expression)
	{
		int colon = expression.indexOf(":");
		
		String ref1 = expression.substring(0, colon);
		int col1 = getCol(ref1);
		int row1 = getRow(ref1);
		
		String ref2 = expression.substring(colon + 1);
		int col2 = getCol(ref2);
		int row2 = getRow(ref2);
		
		double sum = 0;
		
		for (int i = row1; i <= row2; i++)
		{
			for (int j = col1; j <= col2; j++)
			{
				sum += Double.parseDouble(data[i][j].getDisplayValue());
			}
		}
		
		return sum;
	}
		
	public int countCellsInRange(String expression)
	{
		int colon = expression.indexOf(":");
		
		String ref1 = expression.substring(0, colon);
		int col1 = getCol(ref1);
		int row1 = getRow(ref1);
		
		String ref2 = expression.substring(colon + 1);
		int col2 = getCol(ref2);
		int row2 = getRow(ref2);
		
		int num = (row2 - row1 + 1)*(col2 - col1 +1);
		
		return num;
	}
	
	public void resolveSorts(String command, Spreadsheet sheet)
	{
		String range = command.substring(6);
		Cell[] sorted = sheet.sort(range);
		int colon = range.indexOf(":");
		
		String ref1 = range.substring(0, colon);
		int col1 = getCol(ref1);
		int row1 = getRow(ref1);
		
		String ref2 = range.substring(colon + 1);
		int col2 = getCol(ref2);
		int row2 = getRow(ref2);
		
		if (command.contains("sorta"))
		{
			int pos = 0;
			//puts back values--ascending
			for (int i = row1; i <= row2; i++)
			{
				for (int j = col1; j <= col2; j++)
				{
					data[i][j] = sorted[pos];
					pos++;
				}
			}
		}
		else if (command.contains("sortd"))
		{
			int pos = 0;
			//puts back value--descending
			for (int i = row2; i >= row1; i--)
			{
				for (int j = col2; j >= col1; j--)
				{
					data[i][j] = sorted[pos];
					pos++;
				}
			}
		}
	}
	
	public Cell[] sort(String expression)
	{
		int colon = expression.indexOf(":");
		
		String ref1 = expression.substring(0, colon);
		int col1 = getCol(ref1);
		int row1 = getRow(ref1);
		
		String ref2 = expression.substring(colon + 1);
		int col2 = getCol(ref2);
		int row2 = getRow(ref2);
		
		Cell[] sorting = new Cell[countCellsInRange(expression)];
		
		int pos = 0;
		
		for (int i = row1; i <= row2; i++)
		{
			for (int j = col1; j <= col2; j++)
			{
				sorting[pos] = data[i][j];
				pos++;
			}
		}
		
		mergeSort(sorting);
		
		return sorting;
	}
	
	private static void mergeSort(Cell[] numbers)
	{
		if (numbers.length > 1)
		{
			Cell[] left = Arrays.copyOfRange(numbers, 0, numbers.length / 2);
			Cell[] right = Arrays.copyOfRange(numbers, numbers.length / 2, numbers.length);
			mergeSort(left);
			mergeSort(right);
			merge(numbers, left, right);
		}
	}

	private static void merge(Cell[] numbers, Cell[] left, Cell[] right)
	{
		int L = 0;
		int R = 0;

		while (R < right.length && L < left.length)
		{
			if (Double.parseDouble(left[L].getDisplayValue()) < Double.parseDouble(right[R].getDisplayValue()))
			{
				numbers[L + R] = left[L];
				L++;
			}
			else if (Double.parseDouble(left[L].getDisplayValue()) > Double.parseDouble(right[R].getDisplayValue()))
			{
				numbers[L + R] = right[R];
				R++;
			}
			else
			{
				numbers[L + R] = left[L];
				L++;
				numbers[L + R] = right[R];
				R++;
			}
				
		}
	
		while (R < right.length)
		{
			numbers[L + R] = right[R];
			R++;
		}
	
		while (L < left.length)
		{
			numbers[L + R] = left[L];
			L++;
		}
		
	}

}