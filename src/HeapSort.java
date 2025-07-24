import java.util.Arrays;

class HeapSort {
    private static int[] comparisons;
    private static int[] swaps;
    private static int[] reheapDowns;
    private static long[] times;

    public static void sort(Car[] cars) {
        comparisons = new int[cars.length];
        swaps = new int[cars.length];
        reheapDowns = new int[cars.length];
        times = new long[cars.length];

        for (int i = 0; i < cars.length; i++) {
            Car[] sortedCars = Arrays.copyOf(cars, cars.length);
            long startTime = System.nanoTime();
            // Implement Heap Sort algorithm
            buildMaxHeap(sortedCars);
            for (int j = sortedCars.length - 1; j > 0; j--) {
                Car temp = sortedCars[0];
                sortedCars[0] = sortedCars[j];
                sortedCars[j] = temp;
                swaps[i]++;
                heapify(sortedCars, j, 0);
            }
            long endTime = System.nanoTime();
            times[i] = endTime - startTime;
        }
    }

    private static void buildMaxHeap(Car[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    private static void heapify(Car[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        comparisons[i]++;
        if (left < n && arr[left].getName().compareTo(arr[largest].getName()) > 0) {
            largest = left;
        }
        comparisons[i]++;
        if (right < n && arr[right].getName().compareTo(arr[largest].getName()) > 0) {
            largest = right;
        }
        if (largest != i) {
            Car temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            swaps[i]++;
            heapify(arr, n, largest);
            reheapDowns[i]++;
        }
    }

    public static int[] getComparisons() {
        return comparisons;
    }

    public static int[] getSwaps() {
        return swaps;
    }

    public static int[] getReheapDowns() {
        return reheapDowns;
    }

    public static long[] getTimes() {
        return times;
    }
}
