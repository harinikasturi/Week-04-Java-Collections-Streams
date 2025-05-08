import java.util.InputMismatchException;
import java.util.Scanner;

public class DivisionWithFinally {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter first number: ");
            int num1 = scanner.nextInt();

            System.out.print("Enter second number: ");
            int num2 = scanner.nextInt();

            int result = num1 / num2;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero");
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input");
        } finally {
            System.out.println("Operation completed");
            scanner.close();
        }
    }
}