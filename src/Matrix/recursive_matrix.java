import java.util.Random;

public class matrix {
    public static void main(String[] args) {
        int size = 2;
        int size_2 = 16;
        int size_3 = 64;

        int [][] A = new int[size][size];
        int [][] B = new int[size][size];
        int [][] C = new int[size][size];

        int [][] A_2 = new int[size_2][size_2];
        int [][] B_2 = new int[size_2][size_2];
        int [][] C_2 = new int[size_2][size_2];

        int [][] A_3 = new int[size_3][size_3];
        int [][] B_3 = new int[size_3][size_3];
        int [][] C_3 = new int[size_3][size_3];

        long start_time, end_time, elapsed_time;

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        /* Question 1 - PART B */
        A[0][0] = 1;
        A[0][1] = 3;
        A[1][0] = 7;
        A[1][1] = 5;
        B[0][0] = 6;
        B[0][1] = 8;
        B[1][0] = 4;
        B[1][1] = 2;

        square_matrix_multiply(A,B,C);
        print_2d_array(A);
        print_2d_array(B);
        print_2d_array(C);

        /* Question 1 - PART C */

        //initialize elements of matrices with random integers
        initialize_2d_array_random(A_2);
        initialize_2d_array_random(B_2);
        initialize_2d_array_random(C_2);
        //compute the elapsed time
        start_time = System.nanoTime();
        square_matrix_multiply(A_2, B_2, C_2);
        end_time = System.nanoTime();
        elapsed_time = end_time - start_time;
        System.out.printf("Part 1(c) Elapsed Time in nanoseconds: %d\n", elapsed_time);
        /* Question 1 - PART D */

        //initialize elements of matrices with random integers
        initialize_2d_array_random(A_3);
        initialize_2d_array_random(B_3);
        initialize_2d_array_random(C_3);

        //compute the elapsed time
        start_time = System.nanoTime();
        square_matrix_multiply(A_3, B_3, C_3);
        end_time = System.nanoTime();
        elapsed_time = end_time - start_time;
        System.out.printf("Part 1(d) Elapsed Time in nanoseconds: %d\n", elapsed_time);

        /* Question 2 - Part B*/
          initialize_2d_array_random(C);
           C = square_matrix_multiply_recursive(A,B,0,0,0,0, size);
           print_2d_array(A);
           print_2d_array(B);
           print_2d_array(C);

        /* Question 2 - Part C */

        initialize_2d_array_random(A_2);
        initialize_2d_array_random(B_2);
        initialize_2d_array(C_2);

        start_time = System.nanoTime();
        C_2 = square_matrix_multiply_recursive(A_2,B_2,0,0,0,0, size_2);
        end_time = System.nanoTime();
        elapsed_time = end_time - start_time;
        System.out.printf("Part 2(c) Elapsed Time in nanoseconds: %d\n", elapsed_time);

        /* Question 2 - Part D */

        initialize_2d_array_random(A_3);
        initialize_2d_array_random(B_3);
        initialize_2d_array(C_3);

        start_time = System.nanoTime();
        C_3 = square_matrix_multiply_recursive(A_3,B_3,0,0,0,0, size_3);
        end_time = System.nanoTime();
        elapsed_time = end_time - start_time;
        System.out.printf("Part 2(d) Elapsed Time in nanoseconds: %d\n", elapsed_time);

    }

    /* Question 1 - PART A */
    static int[][] square_matrix_multiply(int[][] A, int[][] B, int[][] C)
    {
        int n = A.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                for (int k = 0; k < n; k++) {
                    C[i][j] = C[i][j] + A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }

    /* Question 2 - Part A */
    static int[][] square_matrix_multiply_recursive(int[][] A, int[][] B, int rowA, int colA, int rowB, int colB, int n)
    {
        int[][] C = new int[n][n];

        if (n == 1) {
            C[0][0] = A[rowA][colA] * B[rowB][colB];
            return C;
        }

        int size = n / 2;

        // C11
        square_matrix_sum(square_matrix_multiply_recursive(A,B, rowA, colA, rowB, colB, size),
                square_matrix_multiply_recursive(A,B, rowA, colA + size, rowB + size, colB, size),
                C, 0,0);
        // C12
        square_matrix_sum(square_matrix_multiply_recursive(A,B, rowA, colA, rowB, colB + size, size),
                square_matrix_multiply_recursive(A,B, rowA, colA + size, rowB + size, colB + size, size),
                C, 0, size);
        // C21
        square_matrix_sum(square_matrix_multiply_recursive(A,B, rowA + size, colA, rowB, colB, size),
                square_matrix_multiply_recursive(A,B, rowA + size, colA + size, rowB + size, colB, size),
                C, size, 0);

        // C22
        square_matrix_sum(square_matrix_multiply_recursive(A,B, rowA + size, colA, rowB, colB + size, size),
                square_matrix_multiply_recursive(A,B, rowA + size, colA + size, rowB + size, colB + size, size),
                C, size, size);

        return C;
    }

    static void square_matrix_sum(int[][] A, int[][] B, int[][] C, int rows, int cols)
    {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                C[i+rows][j+cols]=A[i][j]+B[i][j];
            }
        }
    }

    //prints the elements of the array A on the screen
    public static void print_2d_array(int [][] A)
    {
        //System.out.printf("[");
        for (int i = 0; i < A.length; i++)
        {
            for (int j = 0; j < A[0].length; j++)
                System.out.printf("%d ", A[i][j]);
            System.out.printf("\n");
        }

        //System.out.printf("%d]\n", A[A.length-1]);

    }

    public static void initialize_2d_array_random(int [][] A)
    {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        for (int i = 0; i < A.length; i++)
        {
            for (int j = 0; j < A[0].length; j++)
                A[i][j] = rand.nextInt(100);
        }
    }

    public static void initialize_2d_array(int [][] A)
    {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        for (int i = 0; i < A.length; i++)
        {
            for (int j = 0; j < A[0].length; j++)
                A[i][j] = 0;
        }
    }
}