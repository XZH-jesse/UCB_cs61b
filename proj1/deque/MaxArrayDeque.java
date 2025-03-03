package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> cmp;

    public MaxArrayDeque(Comparator<T> c) {
        cmp = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }

        T maxValue = get(0);
        for (T item : this) {
            if (cmp.compare(maxValue, item) < 0) {
                maxValue = item;
            }
        }
        return maxValue;
    }

    public T max(Comparator<T> c) {
        cmp = c;
        return max();
    }
}
