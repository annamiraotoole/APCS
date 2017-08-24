package textExcel1;
// Spreadsheet.java --------------------------------------------------
public class Spreadsheet
{
    // these constants make it easy to remember the number of rows and 
    // columns to use
    private final static int ROWS = 10;
    private final static int COLS = 7;

    private final static int CELL_WIDTH = 12;

    // the 2D array of cells
    private Cell[][] data;

  
    public Spreadsheet()
    {
    	// constructor
        data = new Cell[ROWS][COLS]; // all the Cells will start with the value null
    }
    
    public void print()
    {
      
    	printHeading();
    	System.out.println("");
    	for (int i = 0; i < ROWS; i++)
    	{ 		
    		printRow(i);
    	}
    	
    }
    
    private void printHorizontalLine()
    {
    	
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
    	printHorizontalLine();
    	System.out.println("");
    	System.out.print("|");
    	System.out.print(center(" "));
    	System.out.print("|");
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
    	int leftLength = (CELL_WIDTH - value.length()) / 2;
    	int rightLength = leftLength + 1;
    	
    	if ((value.length() % 2) == 0)
    	{
    		rightLength = leftLength;
    	}
    		
    	return repeatedChar(' ', leftLength) + value + repeatedChar(' ', rightLength);
    }
    
    private void printCell(int row, int col)
    {
        if (data[row][col] == null)
        {
        	System.out.print(center(" "));
        }
        else
        {
        	System.out.print(center(data[row][col].getValue()));
        }
        System.out.print("|");
    }

    private String repeatedChar(char a, int length)
    {
    	// takes in a char and how many times it should be repeated
    	String str = "";
    	for (int i = 0; i < length; i++)
    	{
    		str += a;
    	}
    	return str;
    }
    
    
    // test code: if you run this class's main instead of your TextExcel, you should
    // see a grid printed that has 3 cells filled in.
    public static void main(String[] args)
    {
        // these lines should create a spreadsheet, add 3 cells to it,
        // and print the whole grid. 
        Spreadsheet sheet = new Spreadsheet();
        sheet.data[0][0] = new Cell("hi");
        sheet.data[3][6] = new Cell("text");
        sheet.data[7][4] = new Cell("excel");
        sheet.print();
    }

}