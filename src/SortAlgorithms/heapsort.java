public class heapsort {
    public static void main(String[] args) {
        int A[] = {83, 90, 15, 5, 75, 41, 86, 47, 1, 41 };
        Heapsort(A);
        System.out.println("Sorted array:");
        print_array(A);
    }

    public static void Heapsort(int A[]) {
        int n = A.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            max_heapify(A, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int exchange = A[0];
            A[0] = A[i];
            A[i] = exchange;
            max_heapify(A, i, 0);
        }

    }

    // n: heapsize
    // i: root node
    public static void max_heapify(int A[], int n, int i) {
        int l = 2 * i;
        int r = 2 * i + 1;
        int largest = 0;

        if (l < n && A[l] > A[i]) {
            largest = l;
        } else
            largest = i;
        if (r < n && A[r] > A[largest]) {
            largest = r;
        }
        if (largest != i) {
            int exchange = A[i];
            A[i] = A[largest];
            A[largest] = exchange;
            max_heapify(A, n, largest);
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
