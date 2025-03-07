import java.util.ArrayList;
/**
 * Thread implementation for parallel merge sort.
 * Each thread takes arrays from a shared pool, merges them, and returns the merged result.
 * The process continues until only one sorted array remains in the pool.
 */
class MergeThread extends Thread {
    /** The shared array pool for concurrent access */
    private final ArrayPool arrayPool;
    /** Unique identifier for the thread */
    private final int threadID;
    /** Flag to control thread execution */
    private boolean running = true;

    /**
     * Constructs a MergeThread with a reference to the shared array pool.
     *
     * @param arrayPool the shared pool of arrays to merge
     * @param threadID  unique identifier for this thread
     */
    public MergeThread(ArrayPool arrayPool, int threadID) {
        this.arrayPool = arrayPool;
        this.threadID = threadID;
    }

    /**
     * Executes the merge sort algorithm.
     * The thread continuously:
     * 1. Gets two arrays from the pool
     * 2. Merges them
     * 3. Returns the merged array to the pool
     * Until only one array remains in the pool or the thread is interrupted.
     */
    @Override
    public void run() {
        while (running) {
            // Break if only one array remains (sort completed)
            if (arrayPool.getSize() <= 1) {
                break;
            }

            // Get first array
            ArrayList<Integer> a = arrayPool.getArray();
            if (a == null) {
                // No arrays available, wait briefly and try again
                try {
                    Thread.sleep(10);
                    continue;
                } catch (InterruptedException e) {
                    break;
                }
            }

            // Get second array
            ArrayList<Integer> b = arrayPool.getArray();
            if (b == null) {
                // Only one array available, put it back and try again
                arrayPool.insertArray(a, threadID);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    break;
                }
                continue;
            }

            // Merge the two arrays
            ArrayList<Integer> mergedArray = merge(a, b);
            arrayPool.insertArray(mergedArray, threadID);
        }
        System.out.println("Thread " + threadID + " finished");
    }

    /**
     * Merges two sorted arrays into a single sorted array.
     * Uses the standard merge algorithm from merge sort.
     *
     * @param a the first sorted array
     * @param b the second sorted array
     * @return a new array containing all elements from both input arrays in sorted order
     */
    private ArrayList<Integer> merge(ArrayList<Integer> a, ArrayList<Integer> b) {
        ArrayList<Integer> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < a.size() && j < b.size()) {
            if (a.get(i) <= b.get(j)) {
                result.add(a.get(i++));
            } else {
                result.add(b.get(j++));
            }
        }

        while (i < a.size()) {
            result.add(a.get(i++));
        }

        while (j < b.size()) {
            result.add(b.get(j++));
        }

        return result;
    }
}