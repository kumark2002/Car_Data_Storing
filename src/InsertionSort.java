import java.util.Arrays;

class InsertionSort {
    private static int[] comparisons;
    private static int[] assignments;
    private static long[] times;

    public static void sort(Car[] cars) {
        comparisons = new int[cars.length];
        assignments = new int[cars.length];
        times = new long[cars.length];

        for (int i = 0; i < cars.length; i++) {
            Car[] sortedCars = Arrays.copyOf(cars, cars.length);
            long startTime = System.nanoTime();
            // Implement Insertion Sort algorithm
            for (int j = 1; j < sortedCars.length; j++) {
                Car key = sortedCars[j];
                int k = j - 1;
                while (k >= 0 && sortedCars[k].getName().compareTo(key.getName()) > 0) {
                    comparisons[i]++;
                    sortedCars[k + 1] = sortedCars[k];
                    assignments[i]++;
                    k--;
                }
                sortedCars[k + 1] = key;
                assignments[i]++;
            }
            long endTime = System.nanoTime();
            times[i] = endTime - startTime;
        }
    }

    public static int[] getComparisons() {
        return comparisons;
    }

    public static int[] getAssignments() {
        return assignments;
    }

    public static long[] getTimes() {
        return times;
    }

    public static int[] getSwaps() {
        // Insertion Sort does not perform swaps directly, so returning null or an empty array
        return new int[0];
    }
}
