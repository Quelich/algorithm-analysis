import java.util.Arrays;
import java.util.Random;

public class max_subarray
{
	public static void main(String [] args)
	{
		int array_size = 65536;
		int [] A = new int[array_size];
		int [] test_A = new int[5];
		int [] diff_test_A = new int[4];
		int [] diff_A = new int[array_size-1];
		int [] outputs = new int[3];
		int best_left_index = 0;
		int best_right_index = 0;
		int max_difference = 0;

		long start_time, end_time, elapsed_time;
		long elapsed_time_brute_force, elapsed_time_divide_and_conquer;

		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		//part 1(b)
		test_A[0] = 10;
		test_A[1] = 11;
		test_A[2] = 7;
		test_A[3] = 10;
		test_A[4] = 6;

		brute_force(test_A);

		//initialize elements of array with random integers and compute difference array
		for (int i = 0; i < A.length; i++)
		{
			A[i] = rand.nextInt(100);
			if (i > 0)
				diff_A[i-1] = A[i] - A[i-1];
		}

		//part 1(c) compute the elapsed time for brute-force algorithm
		start_time = System.nanoTime();
		brute_force(A);
		end_time = System.nanoTime();
		elapsed_time = end_time - start_time;
		System.out.printf("Brute-force algorithm elapsed time to find the maximum subarray in nanoseconds: %d\n", elapsed_time);


		//part 2(b)
		diff_test_A[0] = 1;
		diff_test_A[1] = -4;
		diff_test_A[2] = 3;
		diff_test_A[3] = -4;

		//print the left index, right index and maximum difference (i.e. net profit)
		find_maximum_subarray(diff_test_A,0,diff_test_A.length - 1, outputs);
		best_left_index = outputs[0] + 1;
		best_right_index =  outputs[1] + 1;
		max_difference = outputs[2];
		System.out.printf("left: %d, right: %d, max difference: %d\n", best_left_index, best_right_index, max_difference);

		//part 2(c) compute the elapsed time for divide and conquer algorithm
		for (int i = 0; i < A.length; i++)
		{
               A[i] = rand.nextInt(100);
               if (i > 0)
               		diff_A[i-1] = A[i] - A[i-1];
        }

		start_time = System.nanoTime();
		find_maximum_subarray(A,0,A.length - 1, outputs);
		end_time = System.nanoTime();
		elapsed_time_divide_and_conquer = end_time - start_time;

		best_left_index = outputs[0] + 1;
		best_right_index =  outputs[1] + 1;
		max_difference = outputs[2];
		System.out.printf("left: %d, right: %d, max difference: %d\n", best_left_index, best_right_index, max_difference);
		System.out.printf("Part (c) Elapsed time:  %d\n", elapsed_time_divide_and_conquer);

		//print the left index, right index and maximum difference (i.e. net profit)

		//part 3

		int max_array_size = 1000;
		int [] A_2 = new int[max_array_size];
		int [] diff_A_2 = new int[max_array_size-1];

		//initialize elements of array with random integers and compute difference array
		for (int i = 0; i < A_2.length; i++)
		{
              A_2[i] = rand.nextInt(100);
              if (i > 0)
              		diff_A_2[i-1] = A_2[i] - A_2[i-1];
       }

		for (array_size = 2; array_size <= max_array_size; array_size++)
		{

			start_time = System.nanoTime();
			brute_force(A);
			end_time = System.nanoTime();
			elapsed_time_brute_force = end_time - start_time;

			start_time = System.nanoTime();
			find_maximum_subarray(A_2,0,A_2.length - 1, outputs);
			end_time = System.nanoTime();
			elapsed_time_divide_and_conquer = end_time - start_time;

			if (elapsed_time_divide_and_conquer > elapsed_time_brute_force)
			{
				break;
			}
		}

		System.out.printf("n0: %d: %\n", array_size);


	}

	//part 1(a) implementing the brute-force algorithm
	public static void brute_force(int [] A)
	{
		//print the left index, right index and maximum difference (i.e. net profit)
		int n = A.length;
		int maxDiff = 0;
		int max_i = 0;
		int max_j = 0;
		for(int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				int diff = A[j] - A[i];
				if (diff > maxDiff)
				{
					maxDiff = diff;
					max_i = i;
					max_j = j;
				}
			}
		}
		System.out.printf("i = %d, j = %d, Max Difference: %d\n", max_i + 1, max_j + 1, maxDiff);
	}

        public static void brute_force_2(int [] A, int array_size)
        {       

                //print the left index, right index and maximum difference (i.e. net profit)
        
        }

        //part 2(a) implementing the recursive algorithm that uses divide and conquer and finds the maximum subarray
        public static void find_maximum_subarray(int [] diff_A, int low, int high, int [] outputs)
        {       
			//print the left index, right index and maximum difference (i.e. net profit)
			if (low > high)
				return;

			if (low == high)
			{
				outputs[0] = low;
				outputs[1] = high;
				outputs[2] = diff_A[low];

				return;
			}

			int mid = (low + high) / 2;

			find_maximum_subarray(diff_A, low, mid, outputs);
			int[] left = new int[3];
			left[0]= outputs[0];
			left[1] = outputs[1];
			left[2] = outputs[2];
			find_maximum_subarray(diff_A, mid + 1, high, outputs);
			int[] right = new int[3];
			right[0]= outputs[0];
			right[1] = outputs[1];
			right[2] = outputs[2];
			find_max_crossing_subarray(diff_A, low, mid, high, outputs);
			int[] cross = new int[3];
			cross[0]= outputs[0];
			cross[1] = outputs[1];
			cross[2] = outputs[2];

			if (left[2] >= right[2] && left[2] >= cross[2])
			{
				outputs[0] = left[0];
				outputs[1] = left[1];
				outputs[2] = left[2];

			} else if (right[2] >= left[2] && right[2] >= cross[2]) {
				outputs[0] = right[0];
				outputs[1] = right[1];
				outputs[2] = right[2];
			}
			else
			{
				outputs[0] = cross[0];
				outputs[1] = cross[1];
				outputs[2] = cross[2];
			}
        }

        public static void find_max_crossing_subarray(int [] diff_A, int low, int mid, int high, int [] outputs)
		{
			int left_sum = Integer.MIN_VALUE;
			int sum = 0;
			int leftIdx = 0;
			for (int i = mid; i > low; i--) {
				sum += diff_A[i];
				if (sum > left_sum)
				{
					left_sum = sum;
					leftIdx = i;
				}
			}

			int right_sum = Integer.MIN_VALUE;
			sum = 0;
			int rightIdx = 0;
			for (int j = mid + 1; j < high; j++) {
				sum += diff_A[j];
				if (sum > right_sum) {
					right_sum = sum;
					rightIdx = j;
				}
			}

			outputs = new int[]{leftIdx, rightIdx, left_sum + right_sum};
		}

	//prints the elements of the array A on the screen
	public static void print_array(int [] A)
	{
		System.out.printf("[");
		for (int i = 0; i < A.length-1; i++)
		{
			System.out.printf("%d, ", A[i]);
		}
		
		System.out.printf("%d]\n", A[A.length-1]);

	}
}

