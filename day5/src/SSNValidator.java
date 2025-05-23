public class SSNValidator {
    public static void main(String[] args) {
        String[] ssns = {"123-45-6789", "123456789", "123-45-678", "999-99-9999"};

        for (String ssn : ssns) {
            System.out.println(ssn + " is " + (isValidSSN(ssn) ? "Valid" : "Invalid"));
        }
    }

    public static boolean isValidSSN(String ssn) {
        return ssn.matches("^\\d{3}-\\d{2}-\\d{4}$");
    }
}
