//Annamira O'Toole
//changed int value = numbers[i],
//changed i += 2 to i++ (not sure why anyone would do += 2)
//edited the else statment so it would actually switch values
//by creating an int hold that saved the value of numbers[k + 1]

import java.util.Arrays;

// contains a buggy implementation of insertion sort for you to fix.
public class InsertionSortWithBugs 
{
	// keeps track of the approximate number of operations it takes to
	// do a insertion sort
	private static int operations = 0;
	
	// sorts numbers using the insertion sort algorithm
	private static void insertionSort(int[] numbers)
	{
		// we always assume the left half of the array is already sorted and the
		// right half needs to be sorted, so we can initially start at element 1
		// instead of element 0 (since the 1-element array at the left is 'sorted')
		for (int i = 1; i < numbers.length; i++)
		{
			// we store the value we're currently looking at, since we'll probably
			// shift some sorted values on top of it
			int value = numbers[i];
			
			// starting at the end of the sorted part of the array (so, to the left
			// of the value we're looking at) and going down to zero, try to find
			// the right spot for the element
			for (int k = i - 1; k >= 0; k--)
			{
				operations++; // for the comparison
				
				// if the value is bigger than the number we're looking at, we found
				// the right spot. The value will be inserted to the right of element k.
				// We can break here; no need to iterate further.
				if (value > numbers[k])
				{
					operations++; // for the assignment
					numbers[k + 1] = value;
					break;
				}
				// otherwise, we can move element k's value into the k+1 slot and
				// tentatively store our value in element k. As we continue,
				// we may shift it further to the left but this is ok for this iteration.
				else
				{
					operations += 2; // for the assignments
					int hold = numbers[k + 1];
					numbers[k + 1] = numbers[k];
					numbers[k] = hold;
				}
			}
		}
	}
	
	// creates an unsorted array and sorts it using your insertion sort method
	// and the Arrays.sort method, then compares the result to see if you got
	// your insertion sort correct. You don't need to modify anything from
	// here down, though you can feel free to look at it.
	public static void main(String[] args) 
	{
		// if you want a bigger or smaller array for testing, you can change the parameters
		// to createRandomNumberArray. The first parameter is how many elements go into
		// the array.
		int[] yourNumbers = createRandomNumberArray(10, 0, 100);
		int[] myNumbers = Arrays.copyOf(yourNumbers, yourNumbers.length);
		
		System.out.println("Array to be sorted: " + Arrays.toString(yourNumbers));
		System.out.println();

		insertionSort(yourNumbers);
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