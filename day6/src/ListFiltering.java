import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListFiltering {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Apple", "Banana", "Apricot", "Orange", "Avocado");
        List<String> filteredNames = names.stream()
                .filter(name -> !name.startsWith("A"))
                .collect(Collectors.toList());
        System.out.println("Filtered names: " + filteredNames);
    }
}