import java.util.*;

public class ListRotator {
    public static <T> void rotateList(List<T> list, int positions) {
        if (list == null || list.isEmpty()) return;

        int n = list.size();
        positions = positions % n;
        if (positions < 0) positions += n;

        reverseSublist(list, 0, n - 1);
        reverseSublist(list, 0, positions - 1);
        reverseSublist(list, positions, n - 1);
    }

    private static <T> void reverseSublist(List<T> list, int start, int end) {
        while (start < end) {
            T temp = list.get(start);
            list.set(start, list.get(end));
            list.set(end, temp);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));
        System.out.println("Original list: " + list);
        rotateList(list, 2);
        System.out.println("Rotated list: " + list);
    }
}