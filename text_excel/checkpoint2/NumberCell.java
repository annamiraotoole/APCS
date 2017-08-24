package textExcel2;

public class NumberCell extends Cell
{
	double numberValue;
	
	public NumberCell(String value)
	{
		super(value);
		numberValue = Double.parseDouble(value);
	}
	
	public String getDisplayValue()
	{
		return "" + numberValue;
	}

}
