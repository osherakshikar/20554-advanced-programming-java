import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final int MIN_RANDOM = 1; // Minimum random value for array elements.
    private static final int MAX_RANDOM = 100; // Maximum random value for array elements.

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of elements in the array (n):");
        int n = scanner.nextInt();
        if (n <= 0) {
            System.out.println("The number of elements must be greater than 0.");
            scanner.close();
            return;
        }

        System.out.println("Enter the number of threads (m):");
        int m = scanner.nextInt();
        if (m <= 0) {
            System.out.println("The number of threads must be greater than 0.");
            scanner.close();
            return;
        }
        scanner.close();

        ArrayList<ArrayList<Integer>> arr = initializeArray(n); // Create initial arrays.

        System.out.println("Initial arrays:");
        for (ArrayList<Integer> array : arr) {
            System.out.println(array); // Print the generated arrays.
        }

        ArrayPool arrayPool = new ArrayPool(arr); // Initialize the shared pool.
        MergeThread[] threads = initializeThreads(arrayPool, m); // Create the merge threads.

        startThreads(threads); // Start all threads.
        waitForThreads(threads); // Wait for threads to finish.

        System.out.println("Sorted array:");
        arrayPool.printFinalArray(); // Display the final sorted array.
    }

    /**
     * Initializes an array of single-element arrays with random integers.
     * @param n the number of elements to generate.
     * @return a list of single-element arrays.
     */
    private static ArrayList<ArrayList<Integer>> initializeArray(int n) {
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> singleElementArray = new ArrayList<>();
            singleElementArray.add(MIN_RANDOM + (int) (Math.random() * (MAX_RANDOM - MIN_RANDOM + 1))); // Generate random number.
            arr.add(singleElementArray);
        }
        return arr;
    }

    /**
     * Initializes the merge threads.
     * @param arrayPool the shared pool of arrays.
     * @param m the number of threads to create.
     * @return an array of merge threads.
     */
    private static MergeThread[] initializeThreads(ArrayPool arrayPool, int m) {
        MergeThread[] threads = new MergeThread[m];
        for (int i = 0; i < m; i++) {
            threads[i] = new MergeThread(arrayPool, i + 1); // Assign each thread a unique ID.
        }
        return threads;
    }

    /**
     * Starts all threads.
     * @param threads the array of threads to start.
     */
    private static void startThreads(MergeThread[] threads) {
        for (MergeThread thread : threads) {
            thread.start(); // Start each thread.
        }
    }

    /**
     * Waits for all threads to finish execution.
     * @param threads the array of threads to join.
     */
    private static void waitForThreads(MergeThread[] threads) {
        try {
            for (MergeThread thread : threads) {
                thread.join(); // Wait for the thread to finish.
            }
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted: " + e.getMessage());
        }
    }
}