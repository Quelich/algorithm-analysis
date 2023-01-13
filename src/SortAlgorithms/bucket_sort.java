import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class bucket_sort {

    public static void main(String[] args) {

        // input array values must be between [0,1) for bucket sort
        int array_size = 10;
        float[] A = new float[array_size];

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        for (int i = 0; i < array_size; i++)
            A[i] = rand.nextFloat(1);

        print_array(A);
        bucket_sort(A);
        print_array(A);
    }

    public static void bucket_sort(float[] A) {
        int n = A.length;
        ArrayList<Float>[] B = new ArrayList[n];

        // Make an empty list
        for (int i = 0; i < n; i++) {
            B[i] = new ArrayList<>();
        }

        // insert A[i] into list B[floor(n * A[İ]))
        for (float v : A) {
            B[(int) (v * n)].add(v);
        }

        // sort list B[i]
        for (int i = 0; i < n; i++) {
            Collections.sort(B[i]);
            /*
             * In case you want to use insertion sort, you can use the following code
             * just make sure to use a stable algorithm
             */

            // float[] temp = toFloatArray(B[i]);
            // insertion_sort(temp);
        }

        // Concatenate the lists
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < B[i].size(); j++) {
                A[k] = B[i].get(j);
                k++;
            }
        }
    }

    public static float[] toFloatArray(ArrayList<Float> A) {
        float[] B = new float[A.size()];
        for (int i = 0; i < A.size(); i++) {
            B[i] = A.get(i);
        }
        return B;
    }

    public static void insertion_sort(float A[]) {
        for (int j = 1; j < A.length; j++) {
            float key = A[j];
            int i = j - 1;
            while (i >= 0 && A[i] > key) {
                A[i + 1] = A[i];
                i--;
            }
            A[i + 1] = key;
        }
    }

    public static void initialize_array_random(float[] A) {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        for (int i = 0; i < A.length; i++) {
            A[i] = rand.nextFloat(1);
        }
    }

    // prints the elements of the array A on the screen
    public static void print_array(float[] A) {
        System.out.print("[");
        for (int i = 0; i < A.length - 1; i++) {
            System.out.printf("%f, ", A[i]);
        }

        System.out.printf("%f]\n", A[A.length - 1]);

    }
}
