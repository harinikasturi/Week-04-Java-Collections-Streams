import java.util.*;
import java.util.regex.*;

public class WordFrequencyCounter {
    public static Map<String, Integer> countWordFrequencies(String text) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(text.toLowerCase());

        while (matcher.find()) {
            String word = matcher.group();
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        return frequencyMap;
    }

    public static void main(String[] args) {
        String text = "Hello world, hello Java!";
        System.out.println("Word frequencies: " + countWordFrequencies(text));
    }
}