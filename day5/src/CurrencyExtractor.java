import java.util.regex.*;

public class CurrencyExtractor {
    public static void main(String[] args) {
        String text = "Prices: $45.99, 10.50, €30, £20.75 and 100 dollars";

        Pattern pattern = Pattern.compile("\\$?\\d+(?:\\.\\d{1,2})?");
        Matcher matcher = pattern.matcher(text);

        System.out.println("Currency values found:");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}