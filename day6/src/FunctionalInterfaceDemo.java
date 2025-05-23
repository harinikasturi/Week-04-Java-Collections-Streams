@FunctionalInterface
interface CustomSum {
    int sum(int a, int b);
}

public class FunctionalInterfaceDemo {
    public static void main(String[] args) {
        CustomSum adder = (a, b) -> a + b;
        System.out.println("Sum: " + adder.sum(5, 3));
    }
}