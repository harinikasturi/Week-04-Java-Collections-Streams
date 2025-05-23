import java.util.regex.*;

public class LanguageExtractor {
    public static void main(String[] args) {
        String text = "I love Java, Python, and JavaScript, but I haven't tried Go or C++ yet.";

        Pattern pattern = Pattern.compile("\\b(Java|Python|JavaScript|Go|C\\+\\+|C#|Ruby)\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        System.out.println("Programming languages found:");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}