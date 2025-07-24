import java.util.Arrays;

class MergeSort {
    private static int[] comparisons;
    private static int[] mergeCalls;
    private static long[] times;

    public static void sort(Car[] cars) {
        comparisons = new int[cars.length];
        mergeCalls = new int[cars.length];
        times = new long[cars.length];

        for (int i = 0; i < cars.length; i++) {
            Car[] sortedCars = Arrays.copyOf(cars, cars.length);
            long startTime = System.nanoTime();
            // Implement Merge Sort algorithm
            mergeSort(sortedCars, 0, sortedCars.length - 1);
            long endTime = System.nanoTime();
            times[i] = endTime - startTime;
        }
    }

    private static void mergeSort(Car[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(Car[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        Car[] L = new Car[n1];
        Car[] R = new Car[n2];

        for (int i = 0; i < n1; ++i) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[m + 1 + j];
        }

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            comparisons[k]++;
            if (L[i].getName().compareTo(R[j].getName()) <= 0) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
        mergeCalls[k-1]++;
    }

    public static int[] getComparisons() {
        return comparisons;
    }

    public static int[] getMergeCalls() {
        return mergeCalls;
    }

    public static long[] getTimes() {
        return times;
    }
}