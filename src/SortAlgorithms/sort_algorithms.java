import java.io.*;

public class SortAlgorithms {
    static long quickSortElapsedTime;

    public static void printInsertionSort(double[] data) {
        long startTime = System.currentTimeMillis();
        for (int i = 1; i < data.length; i++) {
            double newElement = data[i];
            int j;
            for (j = i; j > 0 && data[j - 1] > newElement; j--) {
                data[j] = data[j - 1];
            }

            data[j] = newElement;
        }
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
        //print result
        System.out.println("Insertion sort took " + elapsed+ " ms");
    }

    public static void printSelectionSort(double[] data) {
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
        System.out.println("Selection sort took " + elapsed+ " ms");
    }

    public static void printQuickSort(double[] data) {
        long startTime = System.currentTimeMillis();
        quickSort(data, 0, data.length);
        //print result
        long endTime = System.currentTimeMillis();
        quickSortElapsedTime = endTime - startTime;
        System.out.println("Quick sort took " + quickSortElapsedTime+ " ms");
    }


    public static void printMergeSort(double[] data) {

        long startTime = System.currentTimeMillis();
        mergeSort(data, 0, data.length);
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
        System.out.println("Merge sort took " + elapsed+ " ms");
    }

    public static void printShellSort(double[] data) {
        long startTime = System.currentTimeMillis();
        for (int gap = data.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < data.length; i++) {
                double newElement = data[i];

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
        System.out.println("Shell sort took "  + elapsed + " ms");
    }

    public static void swap(double[] data, int i, int j) {
        if (i == j) return;
        double temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /* Helper methods */
    public static int partition(double[] data, int start, int end) {
        // Starting element is the pivot
        double pivot = data[start];
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

    public static void mergeSort(double[] data, int start, int end) {

        if (end - start < 2) return;
        int mid = (start + end) / 2;
        mergeSort(data, start, mid);
        mergeSort(data, mid, end);
        merge(data, start, mid, end);
    }

    public static void merge(double[] data, int start, int mid, int end) {

        if (data[mid - 1] <= data[mid]) return;
        int i = start;
        int j = mid;
        int tempIndex = 0;
        double[] temp = new double[end - start];
        while (i < mid && j < end) {
            temp[tempIndex++] = data[i] <= data[j] ? data[i++] : data[j++];
        }
        System.arraycopy(data, i, data, start + tempIndex, mid - i);
        System.arraycopy(temp, 0, data, start, tempIndex);
    }

    private static void quickSort(double[] data, int start, int end) {

        if (start < end) return;
        int pivotIndex = partition(data, start, end);
        quickSort(data, start, pivotIndex);
        quickSort(data, pivotIndex + 1, end);
    }

    public static double[] getArray() throws IOException {
        double[] data = new double[300000];
        File file = new File("Query.txt");
        BufferedReader br
                = new BufferedReader(new FileReader(file));

        // Declaring a string variable
        String line;
        // Condition holds true till
        // there is character in a string
        int i = 0;
        while ((line = br.readLine()) != null) {
            double number = convertNumber(line);
            data[i] = number;
            i++;
        }
        return data;
    }

    private static double convertNumber(String line) {
        StringBuilder seq1 = new StringBuilder();
        // get the sequence before the ten power
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == 'e')
                break;
            seq1.append(line.charAt(i));
        }
        char powerVal = line.charAt(line.length() - 1);
        double tenPower = Integer.parseInt(String.valueOf(powerVal));
        double num = Double.parseDouble(seq1.toString());
        return num / Math.pow(10, tenPower);
    }

    public static int[] convertIntArray(double[] data) {
        final int[] intArray = new int[data.length];
        for (int i = 0; i < intArray.length; ++i)
            intArray[i] = (int) data[i];
        return intArray;
    }

    public static void main(String[] args) throws IOException {

        double[] data_1 = getArray();
        double[] data_2 = getArray();
        double[] data_3 = getArray();
        double[] data_4 = getArray();
        double[] data_5 = getArray();

        printInsertionSort(data_1);
        printSelectionSort(data_2);
        printMergeSort(data_3);
        printShellSort(data_4);
        printQuickSort(data_5);
    }
}


