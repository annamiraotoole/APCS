package textExcel2;

public abstract class Cell
{
	private String value;
	
	public Cell(String inputValue)
	{
		value = inputValue;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public abstract String getDisplayValue();
}
