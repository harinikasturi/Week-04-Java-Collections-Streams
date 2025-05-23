import java.util.regex.*;

public class CapitalizedWordsExtractor {
    public static void main(String[] args) {
        String text = "The Eiffel Tower is in Paris and the Statue of Liberty is in New York.";

        Pattern pattern = Pattern.compile("\\b[A-Z][a-zA-Z]*\\b");
        Matcher matcher = pattern.matcher(text);

        System.out.println("Capitalized words:");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}