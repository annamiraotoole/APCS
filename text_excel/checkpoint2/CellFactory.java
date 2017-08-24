package textExcel2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CellFactory
{
	public static Cell makeCell(String text)
	{
		//if it's a string return a string cell
		if (text.charAt(0) == '"' && text.charAt(text.length()-1) == '"')
		{
			return new StringCell(text);
		}
		
		//if it's a double return a double cell
		try
		{
			double test = Double.parseDouble(text);
			return new NumberCell(text);
		}
		catch (Exception e)
		{
			
		}
		
		//if it's a date return a date cell
		try
		{
			// This SimpleDateFormat object is already created for you.
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            
            Date.parse(text);
            System.out.println("It's a date!");
            return new DateCell(text);
		}
		catch (Exception e)
		{
			
		}
		
		// when it gets to this line, it will have failed to construct any type of cell
		// and instead throws an exception 
		throw new IllegalArgumentException("Not a cell type: " + text);
		
	}
}
