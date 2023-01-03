import java.util.Arrays;
import java.util.Random;

public class count_sort {
    public static void main(String[] args) {
        int array_size = 10;
        int A[] = new int[array_size]; // input array
        int B[] = new int[array_size]; // output array

        initialize_array_random(A);
        int max = Arrays.stream(A).max().getAsInt();

        print_array(A);
        counting_sort(A, B, max);
        print_array(B);
    }

    public static void counting_sort(int[] A, int[] B, int k) {
        int[] C = new int[k + 1];

        int n = A.length;
        for (int i = 0; i < k; i++) {
            C[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            C[A[i]] = C[A[i]] + 1;
        }

        for (int i = 1; i < k + 1; i++) {
            C[i] = C[i] + C[i - 1];
        }

        for (int j = n - 1; j >= 0; j--) {
            B[C[A[j]] - 1] = A[j];
            C[A[j]] = C[A[j]] - 1;
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