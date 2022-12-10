import java.util.Random;

public class StrassenMatrix {
    public static void main(String[] args) {
        int size = 2; /* define the matrix size you want to evaluate in Strassen's algorithm */
        int size_2 = 16;
        int size_3 = 64;

        int[][] A = new int[size][size];
        int[][] B = new int[size][size];
        int[][] C = new int[size][size];

        int[][] A_2 = new int[size_2][size_2];
        int[][] B_2 = new int[size_2][size_2];
        int[][] C_2 = new int[size_2][size_2];

        int[][] A_3 = new int[size_3][size_3];
        int[][] B_3 = new int[size_3][size_3];
        int[][] C_3 = new int[size_3][size_3];

        long start_time, end_time, elapsed_time;

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        // part 1(b)
        A[0][0] = 1;
        A[0][1] = 3;
        A[1][0] = 7;
        A[1][1] = 5;
        B[0][0] = 6;
        B[0][1] = 8;
        B[1][0] = 4;
        B[1][1] = 2;

        // multiply A and B and print the result on screen
        print_2d_array(A);
        print_2d_array(B);
        strassen_matrix_multiply(A, B, C);
        print_2d_array(C);

        // part 1(c)
        // initialize elements of matrices with random integers
        initialize_2d_array_random(A_2);
        initialize_2d_array_random(B_2);

        // compute the elapsed time
        start_time = System.nanoTime();
        strassen_matrix_multiply(A_2, B_2, C_2);
        end_time = System.nanoTime();
        elapsed_time = end_time - start_time;
        System.out.printf("Part 1c elapsed time in nanoseconds: %d ns\n", elapsed_time);

        // part 1(d)
        // initialize elements of matrices with random integers
        initialize_2d_array_random(A_3);
        initialize_2d_array_random(B_3);
        initialize_2d_array(C_3);

        // compute the elapsed time
        start_time = System.nanoTime();
        strassen_matrix_multiply(A_3, B_3, C_3);
        end_time = System.nanoTime();
        elapsed_time = end_time - start_time;
        System.out.printf("Part 1d elapsed time in nanoseconds: %d ns\n", elapsed_time);

        // part 1(e)
        start_time = System.nanoTime();
        square_matrix_multiply(A_2, B_2, C_2);
        end_time = System.nanoTime();
        elapsed_time = end_time - start_time;
        System.out.printf("Part 1e elapsed time in nanoseconds: %d ns\n", elapsed_time);

        // part 1(f)
        start_time = System.nanoTime();
        square_matrix_multiply(A_3, B_3, C_3);
        end_time = System.nanoTime();
        elapsed_time = end_time - start_time;
        System.out.printf("Part 1f elapsed time in nanoseconds: %d ns\n", elapsed_time);

    }

    public static void strassen_matrix_multiply(int[][] A, int[][] B, int[][] C) {
        // assumes square matrices
        int n = A.length;
        int n_half = n / 2;

        // base case
        if (n == 1) {
            C[0][0] = A[0][0] * B[0][0];
            return;
        }

        int[][] S1 = new int[n_half][n_half];
        int[][] S2 = new int[n_half][n_half];
        int[][] S3 = new int[n_half][n_half];
        int[][] S4 = new int[n_half][n_half];
        int[][] S5 = new int[n_half][n_half];
        int[][] S6 = new int[n_half][n_half];
        int[][] S7 = new int[n_half][n_half];
        int[][] S8 = new int[n_half][n_half];
        int[][] S9 = new int[n_half][n_half];
        int[][] S10 = new int[n_half][n_half];

        int[][] P1 = new int[n_half][n_half];
        int[][] P2 = new int[n_half][n_half];
        int[][] P3 = new int[n_half][n_half];
        int[][] P4 = new int[n_half][n_half];
        int[][] P5 = new int[n_half][n_half];
        int[][] P6 = new int[n_half][n_half];
        int[][] P7 = new int[n_half][n_half];

        int[][] temp = new int[n_half][n_half];

        int A11_row_index_start = 0;
        int A11_col_index_start = 0;

        int A12_row_index_start = 0;
        int A12_col_index_start = n_half;

        int A21_row_index_start = n_half;
        int A21_col_index_start = 0;

        int A22_row_index_start = n_half;
        int A22_col_index_start = n_half;

        int B11_row_index_start = 0;
        int B11_col_index_start = 0;

        int B12_row_index_start = 0;
        int B12_col_index_start = n_half;

        int B21_row_index_start = n_half;
        int B21_col_index_start = 0;

        int B22_row_index_start = n_half;
        int B22_col_index_start = n_half;

        int C11_row_index_start = 0;
        int C11_col_index_start = 0;

        int C12_row_index_start = 0;
        int C12_col_index_start = n_half;

        int C21_row_index_start = n_half;
        int C21_col_index_start = 0;

        int C22_row_index_start = n_half;
        int C22_col_index_start = n_half;

        for (int i = 0; i < n_half; i++) {
            for (int j = 0; j < n_half; j++) {
                // Computation of S1
                S1[i][j] = B[B12_row_index_start + i][B12_col_index_start + j]
                        - B[B22_row_index_start + i][B22_col_index_start + j];

                // *****************************************
                // Implement computation of S2,...,S10 here
                S2[i][j] = A[A11_row_index_start + i][A11_col_index_start + j]
                        + A[A12_row_index_start + i][A12_col_index_start + j];
                S3[i][j] = A[A21_row_index_start + i][A21_col_index_start + j]
                        + A[A22_row_index_start + i][A22_col_index_start + j];
                S4[i][j] = B[B21_row_index_start + i][B21_col_index_start + j]
                        - B[B11_row_index_start + i][B11_col_index_start + j];
                S5[i][j] = A[A11_row_index_start + i][A11_col_index_start + j]
                        + A[A22_row_index_start + i][A22_col_index_start + j];
                S6[i][j] = B[B11_row_index_start + i][B11_col_index_start + j]
                        + B[B22_row_index_start + i][B22_col_index_start + j];
                S7[i][j] = A[A12_row_index_start + i][A12_col_index_start + j]
                        - A[A22_row_index_start + i][A22_col_index_start + j];
                S8[i][j] = B[B21_row_index_start + i][B21_col_index_start + j]
                        + B[B22_row_index_start + i][B22_col_index_start + j];
                S9[i][j] = A[A11_row_index_start + i][A11_col_index_start + j]
                        - A[A21_row_index_start + i][A21_col_index_start + j];
                S10[i][j] = B[B11_row_index_start + i][B11_col_index_start + j]
                        + B[B12_row_index_start + i][B12_col_index_start + j];
                // *****************************************

            }
        }

        // recursively compute P matrices

        copy_submatrix(A, A11_row_index_start, A11_col_index_start, temp);
        strassen_matrix_multiply(temp, S1, P1);

        // *****************************************
        // Implement computation of P2,...,P7 here
        copy_submatrix(B, B22_row_index_start, B22_col_index_start, temp);
        strassen_matrix_multiply(S2, temp, P2);
        copy_submatrix(B, B11_row_index_start, B11_col_index_start, temp);
        strassen_matrix_multiply(S3, temp, P3);
        copy_submatrix(A, A22_row_index_start, A22_col_index_start, temp);
        strassen_matrix_multiply(temp, S4, P4);
        strassen_matrix_multiply(S5, S6, P5);
        strassen_matrix_multiply(S7, S8, P6);
        strassen_matrix_multiply(S9, S10, P7);

        // *****************************************

        for (int i = 0; i < n_half; i++) {
            for (int j = 0; j < n_half; j++) {
                // computation of C11
                C[C11_row_index_start + i][C11_col_index_start + j] = P5[i][j] + P4[i][j] - P2[i][j] + P6[i][j];

                // *****************************************
                // Implement computation of C12, C21, and C22 here
                C[C12_row_index_start + i][C12_col_index_start + j] = P1[i][j] + P2[i][j];
                C[C21_row_index_start + i][C21_col_index_start + j] = P3[i][j] + P4[i][j];
                C[C22_row_index_start + i][C22_col_index_start + j] = P5[i][j] + P1[i][j] - P3[i][j] - P7[i][j];
                // *****************************************

            }
        }

        System.gc();
    }

    public static void copy_submatrix(int[][] A, int row_index_start, int col_index_start, int[][] temp) {
        int size = temp.length;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                temp[i][j] = A[row_index_start + i][col_index_start + j];
        }
    }

    public static void square_matrix_multiply(int[][] A, int[][] B, int[][] C) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                C[i][j] = 0;
                for (int k = 0; k < A[0].length; k++)
                    C[i][j] += A[i][k] * B[k][j];
            }
        }
    }

    // prints the elements of the array A on the screen
    public static void print_2d_array(int[][] A) {
        // System.out.printf("[");
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++)
                System.out.printf("%d ", A[i][j]);
            System.out.printf("\n");
        }

        // System.out.printf("%d]\n", A[A.length-1]);

    }

    public static void initialize_2d_array_random(int[][] A) {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++)
                A[i][j] = rand.nextInt(100);
        }
    }

    public static void initialize_2d_array(int[][] A) {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++)
                A[i][j] = 0;
        }
    }
}
