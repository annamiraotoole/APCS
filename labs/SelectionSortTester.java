//Annamira O'Toole 12/5/2015
/* Selection sort is an iterative method that sweeps the array N^2 times. 
 * For every spot in the array, selection sort looks for a new minimum value.
 * To look for a min element, selection (effectively as N goes to infinity) traverses the array once.
 * This gives us N spots * N comparisons for min ====> O(N^2).
 */

import java.util.Arrays;

// allows you to write a selection sort and verify that it works
public class SelectionSortTester 
{
	// keeps track of the approximate number of operations it takes to
	// do a selection sort
	private static int operations = 0;
	
	// sorts numbers using the selection sort algorithm
	private static void selectionSort(int[] numbers)
	{
		
		for (int j = 0; j < numbers.length; j++)
		{
			int min = j;
			for (int i = j + 1; i < numbers.length; i++)
			{
				
				if (numbers[i] < numbers[min])
				{
					min = i;
				}
				
			}
			swap(j, min, numbers);
		}
	
	}
	
	private static void swap(int a, int b, int[] numbers)
	{
		int A = numbers[a];
		numbers[a] = numbers[b];
		numbers[b] = A;
	}

	public static void main(String[] args) 
	{
		// if you want a bigger or smaller array for testing, you can change the parameters
		// to createRandomNumberArray. The first parameter is how many elements go into
		// the array.
		int[] yourNumbers = createRandomNumberArray(10, 0, 100);
		int[] myNumbers = Arrays.copyOf(yourNumbers, yourNumbers.length);
		
		System.out.println("Array to be sorted: " + Arrays.toString(yourNumbers));
		System.out.println();

		selectionSort(yourNumbers);
		Arrays.sort(myNumbers);
		
		System.out.println("Your sorted array:  " + Arrays.toString(yourNumbers));
		System.out.println("My sorted array:    " + Arrays.toString(myNumbers));
		System.out.println();
		System.out.println("Your sort took about " + operations + " operations.");
		System.out.println();

		verifySorting(yourNumbers, myNumbers);
	}

	// creates an array with random numbers in it
	private static int[] createRandomNumberArray(int size, int minValue, int maxValue)
	{
		int[] result = new int[size];
		for (int i = 0; i < size; i++)
			result[i] = (int)(Math.random() * (maxValue - minValue)) + minValue;
		
		return result;
	}
	
	// checks to see that two arrays line up and prints the result
	private static void verifySorting(int[] yours, int[] mine)
	{
		boolean foundError = false;
		for (int i = 0; i < yours.length; i++)
		{
			if (yours[i] != mine[i])
			{
				System.out.println("Your array is not sorted correctly. The element at index " + i + " is wrong.");
				foundError = true;
				break;
			}
		}
		
		if (!foundError)
			System.out.println("Good job! The sorted arrays match.");
	}
}