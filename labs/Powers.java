public class Powers 
{
	/**
	 * Find base^exp using an iterative approach (a for loop)
	 * 
	 * @param base - the base to raise
	 * @param exp - the power to raise it to
	 * @return - the result of multiplying base by itself exp times
	 * 
	 * Precondition: base and exp are not negative
	 */
	private static double powerUsingIteration(int base, int exp)
	{
		double result = 1;
		for (int rep = 0; rep < exp; rep++)
		{
			result *= base;
		}
		
		return result;
	}

	/**
	 * Find base^exp using a recursive approach
	 * 
	 * @param base - the base to raise
	 * @param exp - the power to raise it to
	 * @return - the result of multiplying base by itself exp times
	 * 
	 * Precondition: base and exp are not negative
	 */
	private static double powerUsingRecursion(int base, int exp)
	{
		if (exp == 1)
		{
			return base;
		}
		else
		{
			return powerUsingRecursion(base, exp - 1) * base;
		}
	}
	
	// test using each power function so we can confirm we get the 
	// same result.
	public static void main(String[] args) 
	{
		for (int i = 10; i <= 100; i += 10)
		{
			System.out.println("2^" + i + " is " + powerUsingIteration(2, i) + " using iteration");
			System.out.println("2^" + i + " is " + powerUsingRecursion(2, i) + " using recursion");
			System.out.println();
		}
	}
}