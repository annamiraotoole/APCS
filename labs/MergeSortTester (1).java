//Kelly Robinson and Annamira O'Toole

/* In the depth of recursion that splitting the array creates, 
 * (a tree dividing into branches of half the size each layer)
 * each layer traverses the array once: the lengths of each piece
 * added together, (as every element in each layer must be considered
 * when merging) equal the length of the original array.
 * So with the cost of N (original array length) on each layer of the tree,
 * how many layers are there?  It takes log base 2 of N layers to hit the
 * bottom (to have divided the array completely). Since we are
 * dividing the array in half each time;
 * divide in half ^ log base 2 of N = N.
 * We have N on each layer * log N times = O(n*log(n)).
 */
import java.util.Arrays;

// allows you to write an merge sort and verify that it works
public class MergeSortTester
{
	// keeps track of the approximate number of operations it takes to
	// do a merge sort
	private static int operations = 0;
	
	// sorts numbers using the merge sort algorithm
	private static void mergeSort(int[] numbers)
	{
		if (numbers.length > 1)
		{
			int[] left = Arrays.copyOfRange(numbers, 0, numbers.length / 2);
			int[] right = Arrays.copyOfRange(numbers, numbers.length / 2, numbers.length);
			mergeSort(left);
			mergeSort(right);
			merge(numbers, left, right);
		}
	}

	private static void merge(int[] numbers, int[] left, int[] right)
	{
		int L = 0;
		int R = 0;

		while (R < right.length && L < left.length)
		{
			if (left[L] < right[R])
			{
				numbers[L + R] = left[L];
				L++;
			}
			else if (left[L] > right[R])
			{
				numbers[L + R] = right[R];
				R++;
			}
			else
			{
				numbers[L + R] = left[L];
				L++;
				numbers[L + R] = right[R];
				R++;
			}
				
		}
	
		while (R < right.length)
		{
			numbers[L + R] = right[R];
			R++;
		}
	
		while (L < left.length)
		{
			numbers[L + R] = left[L];
			L++;
		}
		
	}
	// creates an unsorted array and sorts it using your merge sort method
	// and the Arrays.sort method, then compares the result to see if you got
	// your merge sort correct. You don't need to modify anything from
	// here down, though you can feel free to look at it.

	public static void main(String[] args)
	{
		// if you want a bigger or smaller array for testing, you can change the
		// parameters
		// to createRandomNumberArray. The first parameter is how many elements
		// go into
		// the array.
		int[] yourNumbers = createRandomNumberArray(10, 0, 100);
		int[] myNumbers = Arrays.copyOf(yourNumbers, yourNumbers.length);
		System.out.println("Array to be sorted: " + Arrays.toString(yourNumbers));
		System.out.println();
		mergeSort(yourNumbers);
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
			result[i] = (int) (Math.random() * (maxValue - minValue)) + minValue;
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