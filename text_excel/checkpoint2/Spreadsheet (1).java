package textExcel2;

// Spreadsheet.java --------------------------------------------------
public class Spreadsheet
{
	// these constants make it easy to remember the number of rows and
	// columns to use
	public final static int ROWS = 10;
	public final static int COLS = 7;

	private final static int CELL_WIDTH = 12;

	// the 2D array of cells
	public Cell[][] data;

	public Spreadsheet()
	{
		// constructor
		data = new Cell[ROWS][COLS]; // all the Cells will start with the value
										// null
	}

	public void print()
	{
		// prints spreadsheet with cell display values in their respective cells
		printHeading();
		System.out.println("");
		for (int i = 0; i < ROWS; i++)
		{
			printRow(i);
		}
	}

	private void printHorizontalLine()
	{
		// prints horizontal divider
		System.out.print("+");
		for (int j = 0; j < 8; j++)
		{
			for (int i = 0; i < CELL_WIDTH; i++)
			{
				System.out.print("-");
			}
			System.out.print("+");
		}
	}

	private void printHeading()
	{
		// prints header row of boxes that aren't able to contain values
		printHorizontalLine();
		
		// prints empty box
		System.out.println("");
		System.out.print("|");
		System.out.print(center(" "));
		System.out.print("|");
		
		// prints row of boxes with ABCD labels
		for (char i = 'A'; i < 'A' + COLS; i++)
		{
			String colHeading = i + "";
			System.out.print(center(colHeading));
			System.out.print("|");
		}
		
		System.out.println("");
		printHorizontalLine();
	}

	private void printRow(int row)
	{
		// prints first box containing the number of the row
		System.out.print("|");
		System.out.print(center(Integer.toString(row + 1)));
		System.out.print("|");

		// traverses and prints that row of the array
		for (int i = 0; i < COLS; i++)
		{
			printCell(row, i);
		}
		System.out.println("");
		printHorizontalLine();
		System.out.println("");
	}

	private String center(String value)
    {
		// centers value in the cell if value will fit
		// if value.length() is odd, extra space is placed on the right
    	if (value.length() <= CELL_WIDTH)
    	{
	    	int leftLength = (CELL_WIDTH - value.length()) / 2;
	    	int rightLength = leftLength + 1;
	    	
	    	if ((value.length() % 2) == 0)
	    	{
	    		rightLength = leftLength;
	    	}
	    	return repeatedChar(' ', leftLength) + value + repeatedChar(' ', rightLength);
    	}
    	
    	// if value won't fit, the value will take up the whole cell
    	// the last character of the cell is displayed as >
    	// this indicates to the user that there is more to the cell's value than what is displayed
    	else
    	{
    		return value.substring(0, CELL_WIDTH - 1) + ">";
    	}
    }

	private void printCell(int row, int col)
	{
		// takes in the coordinates of the cell's position
		// and prints a box displaying the value
		if (data[row][col] == null)
		{
			System.out.print(center(" "));
		}
		else
		{
			System.out.print(center(data[row][col].getDisplayValue()));
		}
		System.out.print("|");
	}

	private String repeatedChar(char a, int length)
	{
		// takes in a char and how many times it should be repeated
		// returns a string of that char repeated the given amount of times
		String str = "";
		for (int i = 0; i < length; i++)
		{
			str += a;
		}
		return str;
	}
	
	public void assign(CellRef ref, String value)
	{
		data[ref.row][ref.col] = CellFactory.makeCell(value);
	}

}