import java.util.*;

public class NthElementFinder {
    public static <T> T findNthFromEnd(LinkedList<T> list, int n) {
        if (list == null || list.isEmpty() || n <= 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        Iterator<T> iterator = list.iterator();
        Iterator<T> delayedIterator = list.iterator();
        T result = null;

        // Advance the first iterator by n positions
        for (int i = 0; i < n; i++) {
            if (!iterator.hasNext()) {
                throw new IllegalArgumentException("List is shorter than " + n + " elements");
            }
            iterator.next();
        }

        // Move both iterators until the first one reaches the end
        while (iterator.hasNext()) {
            iterator.next();
            result = delayedIterator.next();
        }

        return delayedIterator.next();
    }

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>(Arrays.asList("A", "B", "C", "D", "E"));
        System.out.println("List: " + list);
        System.out.println("2nd from end: " + findNthFromEnd(list, 2));
    }
}