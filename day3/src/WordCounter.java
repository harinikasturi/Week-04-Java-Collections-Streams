import java.io.*;
import java.util.*;
import java.util.stream.*;

public class WordCounter {
    public static void main(String[] args) {
        String filename = "sample_text.txt";
        Map<String, Integer> wordCounts = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split line into words, ignoring punctuation and case
                String[] words = line.toLowerCase().split("[^a-zA-Z']+");

                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                    }
                }
            }

            // Get top 5 most frequent words
            List<Map.Entry<String, Integer>> topWords = wordCounts.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .limit(5)
                    .collect(Collectors.toList());

            System.out.println("Top 5 most frequent words:");
            topWords.forEach(entry ->
                    System.out.printf("%s: %d occurrences%n", entry.getKey(), entry.getValue()));

            System.out.println("\nTotal unique words: " + wordCounts.size());

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}