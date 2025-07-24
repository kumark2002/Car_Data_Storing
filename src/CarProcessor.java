import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class CarProcessor {

    public static void main(String[] args) {
        String filePath = "src/cars.csv";
        String outputFilePath = "formatted_car.txt";
        Car[] cars = readDataFromFile(filePath);
        if (cars != null) {
            writeFormattedDataToFile(cars, outputFilePath);
            testSortingAlgorithms(cars);
            performQualitativeAnalysis();
        } else {
            System.out.println("Failed to read data from file.");
        }
    }

    private static Car[] readDataFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            Car[] cars = new Car[1000];
            int index = 0;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String VIN = data[0];
                String name = data[1];
                String DOB = data[2];
                cars[index] = new Car(VIN, name, DOB);
                index++;
            }
            return Arrays.copyOf(cars, index); // Trim array to actual size
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void writeFormattedDataToFile(Car[] cars, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("No.\tName\tDOB\tVIN\n");
            for (int i = 0; i < cars.length; i++) {
                String formattedVIN = cars[i].getVIN() + "ID";
                String formattedName = cars[i].getName();
                String formattedDOB = cars[i].getDOB();
                writer.write((i + 1) + "+ID\t" + formattedName + "\t" + formattedDOB + "\t" + formattedVIN + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testSortingAlgorithms(Car[] cars) {
        Car[] carsCopy = Arrays.copyOf(cars, cars.length);

        // Bubble Sort
        BubbleSort.sort(carsCopy);
        writeSortedDataToFile(carsCopy, "bsorted.txt");

        // Insertion Sort
        carsCopy = Arrays.copyOf(cars, cars.length);
        InsertionSort.sort(carsCopy);
        writeSortedDataToFile(carsCopy, "isorted.txt");

        // Quick Sort
        carsCopy = Arrays.copyOf(cars, cars.length);
        QuickSort.sort(carsCopy);
        writeSortedDataToFile(carsCopy, "qsorted.txt");

        // Heap Sort
        carsCopy = Arrays.copyOf(cars, cars.length);
        HeapSort.sort(carsCopy);
        writeSortedDataToFile(carsCopy, "hsorted.txt");

        // Merge Sort
        carsCopy = Arrays.copyOf(cars, cars.length);
        MergeSort.sort(carsCopy);
        writeSortedDataToFile(carsCopy, "msorted.txt");
    }

    private static void writeSortedDataToFile(Car[] cars, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("No.\t\tName\t\tDOB\t\tVIN\n");
            for (int i = 0; i < cars.length; i++) {
                String formattedVIN = cars[i].getVIN();
                String formattedName = cars[i].getName();
                String formattedDOB = cars[i].getDOB();
                writer.write((i + 1) + "\t" + formattedName + "\t" + formattedDOB + "\t" + formattedVIN + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void performQualitativeAnalysis() {
        // Print header for the analysis table
        System.out.println("Algorithm\t\t\tComparisons\t\t\tSwaps\t\t\tTime (milliseconds)");

        // Analyze and print results for Bubble Sort
        analyzeAndPrint("Bubble Sort", BubbleSort.getComparisons(), BubbleSort.getSwaps(), BubbleSort.getTimes());

        // Analyze and print results for Insertion Sort
        analyzeAndPrint("Insertion Sort", InsertionSort.getComparisons(), InsertionSort.getAssignments(),
                InsertionSort.getTimes());

        // Analyze and print results for Quick Sort
        analyzeAndPrint("Quick Sort", QuickSort.getComparisons(), QuickSort.getSwaps(), QuickSort.getTimes());

        // Analyze and print results for Heap Sort
        analyzeAndPrint("Heap Sort", HeapSort.getComparisons(), HeapSort.getSwaps(), HeapSort.getTimes());

        // Analyze and print results for Merge Sort
        analyzeAndPrint("Merge Sort", MergeSort.getComparisons(), MergeSort.getMergeCalls(), MergeSort.getTimes());
    }

    private static void analyzeAndPrint(String algorithm, int[] comparisons, int[] swaps, long[] times) {
        System.out.print(algorithm + "\t\t");
        double avgComparisons = getAverage(comparisons);
        System.out.print(avgComparisons + "\t\t");
        if (swaps != null) {
            double avgSwaps = getAverage(swaps);
            System.out.print(avgSwaps + "\t\t");
        } else {
            System.out.print("\t\t");
        }
        double avgTime = getAverage(times);
        System.out.println(avgTime);
    }

    private static double getAverage(long[] arr) {
        long sum = 0;
        for (long num : arr) {
            sum += num;
        }
        return (double) sum / arr.length;
    }

    private static double getAverage(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return (double) sum / arr.length;
    }
}
