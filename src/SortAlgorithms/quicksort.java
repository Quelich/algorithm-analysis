import java.util.Random;

/*
 * Compare running and space complexity of 
 * quicksort-mergesort-heapsort 
 * with different size of data sets 
 */

public class quicksort {
	public static void main(String[] args) {
		int array_size = 67108864;
		int[] array = new int[array_size];
		long start_time, end_time, elapsed_time, elapsed_time_insertion, elapsed_time_merge;

		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		for (int i = 0; i < array_size; i++)
			array[i] = rand.nextInt(100);

		// print_array(array);
		// PART 1(b)
		// start_time = System.nanoTime();
		// randomized_quicksort(array, 0, array.length - 1);
		// end_time = System.nanoTime();
		// elapsed_time = end_time - start_time;
		// //print_array(array);
		// System.out.printf("Elapsed time in nanoseconds for quick sort: %d\n",
		// elapsed_time);

		// PART 1(c)
		int j = 1;
		int array_size_2 = 4;
		int array_limit = 4096;

		while (array_size_2 <= array_limit) {
			int[] array_2 = new int[array_size_2];
			int[] array_3 = new int[array_size_2];
			int[] array_4 = new int[array_size_2];
			Random rand_2 = new Random();
			rand_2.setSeed(System.currentTimeMillis());
			for (int i = 0; i < array_size_2; i++) {
				array_2[i] = rand.nextInt(100);
				array_3[i] = rand.nextInt(100);
				array_4[i] = rand.nextInt(100);
			}

			start_time = System.nanoTime();
			randomized_quicksort(array_2, 0, array_2.length - 1);
			end_time = System.nanoTime();
			elapsed_time = end_time - start_time;
			System.out.printf("Elapsed time in nanoseconds for quick sort, n = %d: %d\n", array_size_2, elapsed_time);

			start_time = System.nanoTime();
			heap_sort(array_3);
			end_time = System.nanoTime();
			elapsed_time = end_time - start_time;
			System.out.printf("Elapsed time in nanoseconds for heap sort, n = %d: %d\n", array_size_2, elapsed_time);

			start_time = System.nanoTime();
			merge_sort(array_4, 0, array_4.length - 1);
			end_time = System.nanoTime();
			elapsed_time = end_time - start_time;
			System.out.printf("Elapsed time in nanoseconds for merge sort, n = %d: %d\n", array_size_2, elapsed_time);

			array_size_2 = array_size_2 * 4;
		}

	}

	// Implement partition algorithm below
	public static int partition(int[] A, int p, int r) {
		int x = A[r];
		int i = p - 1;
		for (int j = p; j < r; j++) {
			if (A[j] < x) {
				i++;
				int temp = A[i];
				A[i] = A[j];
				A[j] = temp;
			}

		}
		int temp2 = A[i + 1];
		A[i + 1] = A[r];
		A[r] = temp2;
		return i + 1;
	}

	// Implement randomized partition algorithm below
	public static int randomized_partition(int[] A, int p, int r) {
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		int i = rand.nextInt(r - p) + p;

		int temp = A[r];
		A[r] = A[i];
		A[i] = temp;
		return partition(A, p, r);

	}

	// Implement randomized quick sort algorithm below
	public static void randomized_quicksort(int[] A, int p, int r) {
		if (p < r) {
			int q = partition(A, p, r);
			randomized_quicksort(A, p, q - 1);
			randomized_quicksort(A, q + 1, r);
		}

	}

	// assumes that index i starts from 1
	public static int parent(int i) {
		return (int) Math.floor(i / 2);
	}

	// assumes that index i starts from 1
	public static int left(int i) {
		return 2 * i;
	}

	// assumes that index i starts from 1
	public static int right(int i) {
		return (2 * i + 1);
	}

	// assumes that index i starts from 1
	public static void max_heapify(int[] A, int array_size, int i) {
		int left_index, right_index, index_of_largest;
		int temp;

		left_index = left(i);
		right_index = right(i);

		if ((left_index <= array_size) && (A[left_index - 1] > A[i - 1]))
			index_of_largest = left_index;
		else
			index_of_largest = i;

		if ((right_index <= array_size) && (A[right_index - 1] > A[index_of_largest - 1]))
			index_of_largest = right_index;

		if (index_of_largest != i) {
			temp = A[i - 1];
			A[i - 1] = A[index_of_largest - 1];
			A[index_of_largest - 1] = temp;
			max_heapify(A, array_size, index_of_largest);
		}
	}

	public static void build_max_heap(int[] A) {
		int middle_index = (int) Math.floor(A.length / 2);
		int array_size = A.length;

		for (int i = middle_index; i >= 1; i--)
			max_heapify(A, array_size, i);
	}

	public static void heap_sort(int[] A) {
		int temp;
		int array_size = A.length;
		build_max_heap(A);

		for (int i = A.length; i >= 2; i--) {
			temp = A[0];
			A[0] = A[i - 1];
			A[i - 1] = temp;
			array_size--;
			max_heapify(A, array_size, 1);
		}
	}

	// indices p and r can start from 0
	public static void merge_sort(int[] A, int p, int r) {
		int q;

		if (p < r) {
			q = (int) Math.floor((p + r) / 2);
			merge_sort(A, p, q);
			merge_sort(A, q + 1, r);
			merge(A, p, q, r);
		}
	}

	public static void merge(int[] A, int p, int q, int r) {
		int n1, n2;
		int i, j;

		n1 = q - p + 1;
		n2 = r - q;

		int[] L = new int[n1];
		int[] R = new int[n2];

		for (i = 0; i < n1; i++)
			L[i] = A[p + i];

		for (i = 0; i < n2; i++)
			R[i] = A[q + i + 1];

		i = 0;
		j = 0;

		for (int k = p; k <= r; k++) {
			if (i >= n1) // the left array finished, copy from right array
			{
				A[k] = R[j];
				j++;
				continue;
			}

			if (j >= n2) // the right array finished, copy from left array
			{
				A[k] = L[i];
				i++;
				continue;
			}

			if (L[i] <= R[j]) {
				A[k] = L[i];
				i++;
			} else {
				A[k] = R[j];
				j++;
			}
		}
	}

	// prints the elements of the array A on the screen
	public static void print_array(int[] A) {
		System.out.printf("[");
		for (int i = 0; i < A.length - 1; i++) {
			System.out.printf("%d, ", A[i]);
		}

		System.out.printf("%d]\n", A[A.length - 1]);

	}
}
