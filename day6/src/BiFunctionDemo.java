import java.util.function.BiFunction;

public class BiFunctionDemo {
    public static void main(String[] args) {
        BiFunction<String, String, String> concatWithSpace = (s1, s2) -> s1 + " " + s2;
        String result = concatWithSpace.apply("Hello", "World");
        System.out.println(result);
    }
}