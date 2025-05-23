public class UsernameValidator {
    public static void main(String[] args) {
        String[] usernames = {"user_123", "123user", "us", "valid_user123"};

        for (String username : usernames) {
            System.out.println(username + " is " + (isValidUsername(username) ? "Valid" : "Invalid"));
        }
    }

    public static boolean isValidUsername(String username) {
        return username.matches("^[a-zA-Z][a-zA-Z0-9_]{4,14}$");
    }
}