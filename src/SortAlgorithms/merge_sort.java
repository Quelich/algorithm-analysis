import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class merge_sort
{
    public static void main(String [] args)
    {
        /***** QUESTION 2 - PART B *****/
        int array_size = 16;
        int [] array = new int[array_size];
        long start_time, end_time, elapsed_time, elapsed_time_insertion, elapsed_time_merge;

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        for (int i = 0; i < array_size; i++)
            array[i] = rand.nextInt(100);

        print_array(array);
        merge_sort(array, 0, array.length - 1);
        print_array(array);

        /***** QUESTION 3 - PART A *****/
       array_size = 1048576;
       array = new int[array_size];

       for (int i = 0; i < array_size; i++)
           array[i] = rand.nextInt(100);

       start_time = System.currentTimeMillis();
       merge_sort(array, 0, array.length-1);
       end_time = System.currentTimeMillis();
       elapsed_time_merge = end_time - start_time;
       System.out.printf("Merge Sort Running Time:%d\n",  elapsed_time_merge);

       array_size = 1048576;
       array = new int[array_size];

       for (int i = 0; i < array_size; i++)
           array[i] = rand.nextInt(100);

       start_time = System.currentTimeMillis();
       insertion_sort(array);
       end_time = System.currentTimeMillis();
       elapsed_time_insertion = end_time - start_time;
       System.out.printf("Insertion Sort Running Time:%d\n",  elapsed_time_insertion);

        /***** QUESTION 3 - PART B *****/
        inputSize_insertion_merge();
    }


    public static void merge_sort(int[] A, int l, int r)
    {
        if (l < r)
        {
            int m = l + (r - l) / 2;
            merge_sort(A, l, m);
            merge_sort(A, m + 1, r);
            merge(A, l, m, r);
        }
    }

    /***** QUESTION 2 - PART A *****/
    public static void merge(int[] A, int l, int m, int r)
    {
        int i, j, k;
        int lSize = m - l + 1;
        int rSize = r - m;
        int[] L = new int[lSize];
        int[] R = new int[rSize];

        for (i = 0; i < lSize; ++i)
            L[i] = A[l + i];

        for (j = 0; j < rSize; ++j)
            R[j] = A[m + 1 + j];

        i = 0;
        j = 0;
        k = l;

        // Copied back to A
        while (i < lSize && j < rSize)
        {
            if (L[i] <= R[j])
            {
                A[k] = L[i];
                i++;
            }
            else
            {
                A[k] = R[j];
                j++;
            }
            k++;
        }

        // Copying the remainder of the other array back to A
        while (i < lSize)
        {
            A[k] = L[i];
            i++;
            k++;
        }

        while (j < rSize)
        {
            A[k] = R[j];
            j++;
            k++;
        }
    }

    public static void insertion_sort(int [] A)
    {
        for (int j = 1; j < A.length; j++) {
            int key = A[j];
            int i = j - 1;
            while (i >= 0 && A[i] > key)
            {
                A[i+1] = A[i];
                i--;
            }
            A[i+1] = key;
        }
    }

    public static void inputSize_insertion_merge()
    {
        int array_size = 1;

        long start_time, end_time, elapsed_time, elapsed_time_insertion, elapsed_time_merge;

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());


            while(true)
            {
                int [] array = new int[array_size];
                for (int i = 0; i < array_size; i++)
                    array[i] = rand.nextInt(100);

                int[] array2 = array;

                start_time = System.currentTimeMillis();
                insertion_sort(array);
                end_time = System.currentTimeMillis();
                elapsed_time_insertion = end_time - start_time;

                start_time = System.currentTimeMillis();
                merge_sort(array2, 0, array2.length-1);
                end_time = System.currentTimeMillis();
                elapsed_time_merge = end_time - start_time;

                if(elapsed_time_insertion > elapsed_time_merge)
                {
                    System.out.printf("The input size of n that insertion sort runs faster than merge sort: %d", array_size);
                    break;
                }
                array_size++;
            }

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
