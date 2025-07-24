import java.util.Arrays;

class BubbleSort {
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
            // Implement Bubble Sort algorithm
            for (int j = 0; j < sortedCars.length - 1; j++) {
                for (int k = 0; k < sortedCars.length - j - 1; k++) {
                    comparisons[i]++;
                    if (sortedCars[k].getName().compareTo(sortedCars[k + 1].getName()) > 0) {
                        Car temp = sortedCars[k];
                        sortedCars[k] = sortedCars[k + 1];
                        sortedCars[k + 1] = temp;
                        swaps[i]++;
                    }
                }
            }
            long endTime = System.nanoTime();
            times[i] = endTime - startTime;
        }
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