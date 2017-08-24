package textExcel2;

public class CellRef
{
	public int row;
	public int col;
	
	public CellRef(String ref)
	{
		if (!isValid(ref))
		{
			throw new IllegalArgumentException("Bad Cell reference: " + ref);
		}
		setRow(ref);
		setCol(ref);
	}
	
	private void setRow(String ref)
	{
		row = Integer.parseInt(ref.substring(1)) - 1;
	}
	
	private void setCol(String ref)
	{
		col = ref.charAt(0) - 'A';
	}
	
	public static boolean isValid(String ref)
	{
		// tests if letter and number values in the reference
		// have existing positions in the sheet/array
		if (ref.charAt(0) < 'A' || ref.charAt(0) > 'A' + Spreadsheet.COLS)
		{
			return false;
		}
		int tryRow = Integer.parseInt(ref.substring(1)) - 1;
		if (tryRow < 0 || tryRow > Spreadsheet.ROWS)
		{
			return false;
		}
		return true;
	}
	
	public String toString()
	{
		return  "" + (char)('A' + col) + (row + 1);
	}
}
