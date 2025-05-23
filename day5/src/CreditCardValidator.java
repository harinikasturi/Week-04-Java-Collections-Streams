public class CreditCardValidator {
    public static void main(String[] args) {
        String[] cards = {"4123456789012345", "5123456789012345", "3123456789012345", "412345678901234"};

        for (String card : cards) {
            System.out.println(card + " is " + validateCard(card));
        }
    }

    public static String validateCard(String card) {
        if (card.matches("^4\\d{15}$")) return "Valid Visa";
        if (card.matches("^5\\d{15}$")) return "Valid MasterCard";
        return "Invalid";
    }
}