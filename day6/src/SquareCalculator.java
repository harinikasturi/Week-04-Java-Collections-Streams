@FunctionalInterface
interface SquareCalculator {
    double calculateSquare(double num);

    default void printResult(double num, double result) {
        System.out.println("The square of " + num + " is " + result);
    }
}

class CustomFunctionalInterface {
    public static void main(String[] args) {
        SquareCalculator square = x -> x * x;
        double number = 4.0;
        double result = square.calculateSquare(number);
        square.printResult(number, result);
    }
}