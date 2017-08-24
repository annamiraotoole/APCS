package textExcel2;

public class StringCell extends Cell
{
	public StringCell(String value)
	{
		// sets the cell value to the string within the "
		// doesn't include " in the string value
		super(value.substring(1, value.length()-1));
	}
	
	public String getDisplayValue()
	{
		return super.getValue();
	}
	
	public String getValue()
	{
		// adds " to the cell value so that when the user asks for the value of the cell,
		// it will display the string value in "
		return '"' + super.getValue() + '"';
	}
	
}
