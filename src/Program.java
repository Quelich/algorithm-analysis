import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Program {
    static long quickSortElapsedTime;

    public static void printInsertionSort(int[] data) {
        long startTime = System.currentTimeMillis();
        for (int i = 1; i < data.length; i++) {
            int newElement = data[i];
            int j;
            for (j = i; j > 0 && data[j - 1] > newElement; j--) {
                data[j] = data[j - 1];
            }

            data[j] = newElement;
        }
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
        //print result
        System.out.println("Insertion Sort: Array Size: " + data.length + " Time Elapsed: " + elapsed);
    }

    public static void printSelectionSort(int[] data) {
        long startTime = System.currentTimeMillis();
        for (int i = data.length - 1; i > 0; i--) {
            int largest = 0;
            for (int j = 1; j <= i; j++) {
                if (data[j] > data[largest]) {
                    largest = j;
                }
            }
            swap(data, largest, i);
        }

        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
        //print result
        System.out.println("Selection Sort: Array Size: " + data.length + " Time Elapsed: " + elapsed);
    }

    public static void printQuickSort(int[] data) {
        long startTime = System.currentTimeMillis();
        quickSort(data, 0, data.length);
        //print result
        long endTime = System.currentTimeMillis();
        quickSortElapsedTime = endTime - startTime;
        System.out.println("Quick Sort: Array Size: " + data.length + " Time Elapsed: " + quickSortElapsedTime);
    }


    public static void printMergeSort(int[] data) {

        long startTime = System.currentTimeMillis();
        mergeSort(data, 0, data.length);
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
        System.out.println("Merge Sort: Array Size: " + data.length + " Time Elapsed: " + elapsed);
    }

    public static void printShellSort(int[] data) {
        long startTime = System.currentTimeMillis();
        for (int gap = data.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < data.length; i++) {
                int newElement = data[i];

                int j = i;

                while (j >= gap && data[j - gap] > newElement) {
                    data[j] = data[j - gap];
                    j -= gap;
                }

                data[j] = newElement;
            }
        }
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
        //print result
        System.out.println("Shell Sort: Array Size: " + data.length + " Time Elapsed: " + elapsed);
    }

    public static void swap(int[] data, int i, int j) {
        if (i == j) return;
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /* Helper methods */
    public static int partition(int[] data, int start, int end) {
        // Starting element is the pivot
        int pivot = data[start];
        int i = start;
        int j = end;

        while (i < j) {
            while (i < j && data[--j] >= pivot) ;
            if (i < j) {
                data[i] = data[j];
            }
            while (i < j && data[++i] <= pivot) ;
            if (i < j) {
                data[j] = data[i];
            }
        }

        data[j] = pivot;
        return j;

    }

    public static void mergeSort(int[] data, int start, int end) {

        if (end - start < 2) return;
        int mid = (start + end) / 2;
        mergeSort(data, start, mid);
        mergeSort(data, mid, end);
        merge(data, start, mid, end);
    }

    public static void merge(int[] data, int start, int mid, int end) {

        if (data[mid - 1] <= data[mid])  return;
        int i = start;
        int j = mid;
        int tempIndex = 0;
        int[] temp = new int[end - start];
        while (i < mid && j < end) {
            temp[tempIndex++] = data[i] <= data[j] ? data[i++] : data[j++];
        }
        System.arraycopy(data, i, data, start + tempIndex, mid - i);
        System.arraycopy(temp, 0, data, start, tempIndex);
    }

    private static void quickSort(int[] data, int start, int end) {

        if (end - start < 2) return;
        int pivotIndex = partition(data, start, end);
        quickSort(data, start, pivotIndex);
        quickSort(data, pivotIndex + 1, end);
    }

    public static void getArray() throws FileNotFoundException {
      File file = new File("Query.txt");
      Scanner sc = new Scanner(file);
      while(sc.hasNextLine())
      {
        System.out.println(sc.nextLine());
      }
    }

    private static int convertNumber(String sqc)
    {

        return -1;
    }

    public static void main(String[] args) throws FileNotFoundException {
       
        getArray();
    }
}


