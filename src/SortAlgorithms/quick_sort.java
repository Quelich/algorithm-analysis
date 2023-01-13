import java.util.Random;

/*
 * Compare running and space complexity of 
 * quicksort-mergesort-heapsort 
 * with different size of data sets 
 */

public class quick_sort {
	public static void main(String[] args) {
		int array_size = 67108864;
		int[] array = new int[array_size];
		long start_time, end_time, elapsed_time, elapsed_time_insertion, elapsed_time_merge;

		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		for (int i = 0; i < array_size; i++)
			array[i] = rand.nextInt(100);

		randomized_quicksort(array, 0, array.length - 1);
		end_time = System.nanoTime();
		elapsed_time = end_time - start_time;
		System.out.printf("Elapsed time in nanoseconds for quick sort, n = %d: %d\n", array_siarray_sizeze_2, elapsed_time);

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
	
	// prints the elements of the array A on the screen
	public static void print_array(int[] A) {
		System.out.printf("[");
		for (int i = 0; i < A.length - 1; i++) {
			System.out.printf("%d, ", A[i]);
		}

		System.out.printf("%d]\n", A[A.length - 1]);

	}
}
