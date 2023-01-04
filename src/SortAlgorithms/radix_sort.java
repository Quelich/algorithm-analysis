import java.util.Arrays;
import java.util.Random;

/*
 * Radix sort requires a stable sorting algorithm
 * in this case, counting sort is used.
 */

public class radix_sort {

    public static void main(String[] args) {
        int array_size = 10;
        int A[] = new int[array_size];

        initialize_array_random(A);
        print_array(A);
        radix(A, array_size);
        print_array(A);
    }

    // Omega(d(n+k)), d-digit numbers
    public static void radix(int A[], int n) {

        int max = Arrays.stream(A).max().getAsInt();
        // start sorting from least significant digit first.
        for (int i = 1; max / i > 0; i *= 10) {
            counting_sort(A, n, i);
        }
    }

    public static void counting_sort(int[] A, int n, int place) {
        int countLen = 10; // For decimal digits, each column uses only 10 places
        int[] output = new int[n + 1];
        int[] counter = new int[countLen];

        // Initialize the elements
        for (int i = 0; i < countLen; i++) {
            counter[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            counter[(A[i] / place) % countLen]++;
        }

        for (int i = 1; i < countLen; i++) {
            counter[i] += counter[i - 1];
        }

        // Place the elements sorted
        for (int j = n - 1; j >= 0; j--) {
            output[counter[(A[j] / place) % 10] - 1] = A[j];
            counter[(A[j] / place) % 10]--;
        }

        // Output
        for (int i = 0; i < n; i++) {
            A[i] = output[i];
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

    public static void initialize_array_random(int[] A) {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        for (int i = 0; i < A.length; i++) {
            A[i] = rand.nextInt(100);
        }
    }
}
