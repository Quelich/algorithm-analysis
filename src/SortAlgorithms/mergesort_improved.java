import java.util.Arrays;
import java.util.Random;

public class merge {
    public static void main(String[] args) {
        int array_size = 65536;
        //int array_size = 16;
        int[] array = new int[ array_size ];
        int[] array_2 = new int[ array_size ];
        int[] array_3 = new int[ array_size ];

        long start_time, end_time, elapsed_time = 0, min_elapsed_time;

        int best_max_level = 1;

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        for (int i = 0; i < array_size; i++) {
            array[ i ] = rand.nextInt(100);
            array_2[ i ] = array[ i ];
            array_3[ i ] = array[ i ];
        }


        // part (a)
        //print_array(array);
        start_time = System.nanoTime();
        merge_sort(array, 0, array.length - 1);
        end_time = System.nanoTime();
        elapsed_time = end_time - start_time;
        //print_array(array);
        //Running time of merge sort
        System.out.printf("Elapsed time in nanoseconds for original merge sort: %d ns\n", elapsed_time);

        //part (d)
        //Running time of improved merge sort
        start_time = System.nanoTime();
        merge_sort_improved(array_2, 0, array_2.length - 1, 1, 13);
        end_time = System.nanoTime();
        elapsed_time = end_time - start_time;
        System.out.printf("Elapsed time in nanoseconds for improved merge sort with max_level=13: %d ns\n", elapsed_time);

        //part (e)
        min_elapsed_time = 0;
        for (int max_level = 1; max_level <= 17; max_level++) {
            //copy the original array back to input array
            for (int i = 0; i < array_size; i++)
                array[ i ] = array_3[ i ];

            //compute running time of merge sort improved here
            start_time = System.nanoTime();
            merge_sort_improved(array, 0, array.length - 1, 1, max_level);
            end_time = System.nanoTime();
            elapsed_time = end_time - start_time;

            if ((max_level == 1) || (elapsed_time < min_elapsed_time)) {
                min_elapsed_time = elapsed_time;
                best_max_level = max_level;
            }

            System.out.printf("Max level: %d, Elapsed time in nanoseconds: %d\n", max_level, elapsed_time);

        }

        System.out.printf("Best max level: %d, Min elapsed time in nanoseconds: %d\n", best_max_level, min_elapsed_time);
    }

    // parts (b)-(d) implement improved merge sort here
    public static void merge_sort_improved(int[] A, int p, int r, int level, int max_level) {
        int q;

        if (p < r) {
            if (level >= max_level) {
                //call insertion_sort_2 here
                insertion_sort_2(A, p, r);
            } else {
                //implement the divide and conquer code here
                q = (int) Math.floor((p + r) / 2);
                level++;
                merge_sort_improved(A, p, q, level, max_level);
                merge_sort_improved(A, q + 1, r, level, max_level);
                merge(A, p, q, r);
            }
        }
    }

    //indices p and r can start from 0
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

        int[] L = new int[ n1 ];
        int[] R = new int[ n2 ];

        for (i = 0; i < n1; i++)
            L[ i ] = A[ p + i ];

        for (i = 0; i < n2; i++)
            R[ i ] = A[ q + i + 1 ];

        i = 0;
        j = 0;

        for (int k = p; k <= r; k++) {
            if (i >= n1) //the left array finished, copy from right array
            {
                A[ k ] = R[ j ];
                j++;
                continue;
            }

            if (j >= n2) //the right array finished, copy from left array
            {
                A[ k ] = L[ i ];
                i++;
                continue;
            }

            if (L[ i ] <= R[ j ]) {
                A[ k ] = L[ i ];
                i++;
            } else {
                A[ k ] = R[ j ];
                j++;
            }
        }
    }


    //insertion sort algorithm
    public static void insertion_sort_2(int[] A, int start_index, int end_index) {
        int key;
        int i;

        for (int j = start_index + 1; j < end_index; j++) {
            key = A[ j ];

            //insert A[j] into the sorted sequence A[starting_index..j-1]
            i = j - 1;

            while ((i >= 0) && (A[ i ] > key)) {
                A[ i + 1 ] = A[ i ];
                i = i - 1;
            }

            A[ i + 1 ] = key;
        }
    }

    //insertion sort algorithm
    public static void insertion_sort(int[] A) {
        int key;
        int i;

        for (int j = 1; j < A.length; j++) {
            key = A[ j ];

            //insert A[j] into the sorted sequence A[1..j-1]
            i = j - 1;

            while ((i >= 0) && (A[ i ] > key)) {
                A[ i + 1 ] = A[ i ];
                i = i - 1;
            }

            A[ i + 1 ] = key;
        }
    }

    //prints the elements of the array A on the screen
    public static void print_array(int[] A) {
        System.out.printf("[");
        for (int i = 0; i < A.length - 1; i++) {
            System.out.printf("%d, ", A[ i ]);
        }

        System.out.printf("%d]\n", A[ A.length - 1 ]);

    }
}

