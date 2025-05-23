import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateComposition {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Apple", "Banana", "Apricot", "Avocado", "Berry", "Cherry");

        Predicate<String> lengthGreaterThan5 = s -> s.length() > 5;
        Predicate<String> containsSubstring = s -> s.contains("rr");

        List<String> filteredWords = words.stream()
                .filter(lengthGreaterThan5.and(containsSubstring))
                .collect(Collectors.toList());
        System.out.println("Filtered words: " + filteredWords);
    }
}