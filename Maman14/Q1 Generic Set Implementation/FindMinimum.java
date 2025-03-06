public class FindMinimum {
    public static <T extends Comparable<T>> T findMinimum(GenericSet<T> set) {
        if (set.list.isEmpty()) {
            throw new IllegalArgumentException("Set is empty. Cannot find minimum.");
        }

        T min = set.list.getFirst();
        for (T item : set.list) {
            if (item.compareTo(min) < 0) {
                min = item;
            }
        }
        return min;
    }
}
