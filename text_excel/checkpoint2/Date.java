package textExcel2;

public class Date
{
    private int year;
    private int month;
    private int day;
    
    public Date(int year, int month, int day)
    {
       setYear(year);
       setMonth(month);
       setDay(day);
    }
    
    public Date(String input)
    {
        // correct format = MM/DD/YYYY
        
        // check for '/' separators
    	int firstSeparator = input.indexOf('/');
    	int secondSeparator = input.indexOf('/', firstSeparator+1);
    	
    	if (firstSeparator < 0 || secondSeparator < 0)
    	{
    		throw new IllegalArgumentException("Please use correct date format: MM/DD/YYYY");
    	}
    	
    	//setting(parsed int(substring of value))
    	setMonth(Integer.parseInt(input.substring(0, firstSeparator)));
    	setDay(Integer.parseInt(input.substring(firstSeparator+1, secondSeparator)));
    	setYear(Integer.parseInt(input.substring(secondSeparator+1)));
		
    }
    
    public void setYear(int year)
    {
        if ((year > 9999) || (year < 0))
        {
            throw new IllegalArgumentException("Bad year: " + year);
        }
        this.year = year;
    }
    
    public void setMonth(int month)
    {
    	if ((month > 12) || (month < 0))
        {
            throw new IllegalArgumentException("Bad month: " + month);
        }
        this.month = month;
    }
    
    public void setDay(int day)
    {
    	//precondition: year and month have been properly set
    	if (!(day <= daysInMonth(month) || (day < 1)))
		{
    		throw new IllegalArgumentException("Bad day: " + day);
		}
        this.day = day;
    }
    
    private int daysInMonth(int month)
    {
         //30 days: September April June November
         //February: 28, if it's a leap year: 29
         //rest have 31
        
    	if (month == 4 || month == 5 || month == 9 || month == 11)
    	{
    		return 30;
    	}
    	if (month == 2)
    	{
    		if (leapYear(year))
    		{
    			return 29;
    		}
    		else 
    		{
    			return 28;
    		}
    	}
    	else
    	{
    		return 31;
    	}
    }
 
    private boolean leapYear(int year)
    {
        //helpful:
        //http://en.wikipedia.org/wiki/Leap_year#Algorithm
    	if (!(year % 4 == 0))
    	{
    		return false;
    	}
    	else if (!(year % 100 == 0))
    	{
    		return true;
    	}
    	else if (!(year % 400 == 0))
    	{
    		return false;
    	}
    	else
    	{
    		return true;
    	}
    }
    
    public String toString()
    {
        return String.format("%02d", month) + "/" + String.format("%02d", day) + "/" + String.format("%04d", year);
    }
    
    public static void main(String[] args)
    {
        Date d1 = new Date(2001, 2, 4);
        System.out.println(d1);
        Date d2 = new Date("02/04/2001");
        System.out.println(d2);
    }
}