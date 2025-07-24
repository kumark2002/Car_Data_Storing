import java.util.Arrays;

class QuickSort {
    private static int[] comparisons;
    private static int[] swaps;
    private static long[] times;

    public static void sort(Car[] cars) {
        comparisons = new int[cars.length];
        swaps = new int[cars.length];
        times = new long[cars.length];

        for (int i = 0; i < cars.length; i++) {
            Car[] sortedCars = Arrays.copyOf(cars, cars.length);
            long startTime = System.nanoTime();
            // Implement Quick Sort algorithm
            quickSort(sortedCars, 0, sortedCars.length - 1);
            long endTime = System.nanoTime();
            times[i] = endTime - startTime;
        }
    }

    private static void quickSort(Car[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Car[] arr, int low, int high) {
        Car pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            comparisons[j]++;
            if (arr[j].getName().compareTo(pivot.getName()) < 0) {
                i++;
                Car temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                swaps[j]++;
            }
        }
        Car temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        swaps[high]++;
        return i + 1;
    }

    public static int[] getComparisons() {
        return comparisons;
    }

    public static int[] getSwaps() {
        return swaps;
    }

    public static long[] getTimes() {
        return times;
    }
}