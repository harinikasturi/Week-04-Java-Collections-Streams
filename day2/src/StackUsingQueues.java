import java.util.*;

class StackUsingQueues<T> {
    private Queue<T> q1 = new LinkedList<>();
    private Queue<T> q2 = new LinkedList<>();

    public void push(T item) {
        q2.add(item);
        while (!q1.isEmpty()) {
            q2.add(q1.remove());
        }
        Queue<T> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    public T pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");
        return q1.remove();
    }

    public T top() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");
        return q1.peek();
    }

    public boolean isEmpty() {
        return q1.isEmpty();
    }
}
class StackWithQueues {
    public static void main(String[] args) {
        StackUsingQueues<Integer> stack = new StackUsingQueues<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("Pop: " + stack.pop()); // 3
        System.out.println("Top: " + stack.top()); // 2
    }
}