import java.util.regex.*;

public class LinkExtractor {
    public static void main(String[] args) {
        String text = "Visit https://www.google.com and http://example.org or https://sub.domain.co.uk/page";

        Pattern pattern = Pattern.compile("https?://[\\w.-]+\\.[a-zA-Z]{2,6}[\\w./?=%-]*");
        Matcher matcher = pattern.matcher(text);

        System.out.println("Found links:");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}