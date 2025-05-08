public class InterestCalculator {
    public static double calculateInterest(double amount, double rate, int years)
            throws IllegalArgumentException {
        if (amount < 0 || rate < 0) {
            throw new IllegalArgumentException("Amount and rate must be positive");
        }
        return amount * rate * years / 100;
    }

    public static void main(String[] args) {
        try {
            double interest = calculateInterest(1000, 5, 3);
            System.out.println("Interest: " + interest);

            // Test with invalid input
            calculateInterest(-1000, 5, 3);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }
}