import java.util.regex.*;

public class EmailExtractor {
    public static void main(String[] args) {
        String text = "Contact us at support@example.com and info@company.org or sales@test.co.uk";

        Pattern pattern = Pattern.compile("[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}");
        Matcher matcher = pattern.matcher(text);

        System.out.println("Found emails:");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}