import java.util.ArrayList;

/**
 * Represents a shared pool of arrays for merge sorting.
 * Uses built-in Java synchronization without explicit locks.
 */
class ArrayPool {
    private final ArrayList<ArrayList<Integer>> arrays;

    /**
     * Initializes the pool with the given arrays.
     * @param arrays the list of arrays to manage.
     */
    public ArrayPool(ArrayList<ArrayList<Integer>> arrays) {
        this.arrays = new ArrayList<>(arrays);
    }

    /**
     * Retrieves the first available array from the pool.
     * Synchronized to ensure thread-safe access to the shared resource.
     * @return the first array or null if the pool is empty.
     */
    public synchronized ArrayList<Integer> getArray() {
        if (arrays.isEmpty()) {
            return null; // Return null if no arrays are available.
        }
        return arrays.remove(0); // Remove and return the first array.
    }

    /**
     * Inserts a merged array into the pool and logs the operation.
     * Synchronized to ensure thread-safe access to the shared resource.
     * @param mergedArray the merged array to insert.
     * @param threadID the ID of the thread performing the operation.
     */
    public synchronized void insertArray(ArrayList<Integer> mergedArray, int threadID) {
        arrays.add(mergedArray); // Add the merged array to the pool.
        System.out.println("Thread " + threadID + " inserted merged array: " + mergedArray);
    }

    /**
     * Returns the current size of the pool.
     * Synchronized to ensure consistent size reporting.
     * @return the number of arrays in the pool.
     */
    public synchronized int getSize() {
        return arrays.size();
    }

    /**
     * Prints the final sorted array if only one remains in the pool.
     * Otherwise, it logs an error indicating incomplete merging.
     */
    public synchronized void printFinalArray() {
        if (arrays.size() == 1) {
            System.out.println("Final array: " + arrays.get(0));
        } else {
            System.out.println("Error: Pool does not contain exactly one array.");
        }
    }
}