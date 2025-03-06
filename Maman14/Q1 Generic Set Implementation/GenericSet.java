import java.util.ArrayList;
import java.util.Iterator;

public class GenericSet<T> {
    ArrayList<T> list = new ArrayList<T>();

    public GenericSet() {
        this.list = new ArrayList<>();
    }

    public GenericSet(T[] items) {
        this();
        for (T item : items) {
            insert(item);
        }
    }

    public void union(GenericSet<T> other) {
        for (T item : other.list) {
            insert(item);
        }
    }

    public void intersect(GenericSet<T> other) {
        ArrayList<T> toRemove = new ArrayList<>();
        for (T item : list) {
            if (!other.isMember(item)) {
                toRemove.add(item);
            }
        }
        list.removeAll(toRemove);
    }

    public boolean isSubset(GenericSet<T> other) {
        for (T item : other.list) {
            if (!isMember(item)) {
                return false;
            }
        }
        return true;
    }


    public boolean isMember(T item) {
        return list.contains(item);
    }

    public void insert(T item) {
        if (!isMember(item)) {
            list.add(item);
        }
    }

    public void delete(T item) {
        if (isMember(item))
            list.remove(item);
    }

    public Iterator<T> iterator() {
        return list.iterator();
    }

    public String toString() {
        return list.toString();
    }
}
