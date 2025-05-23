import java.util.function.Function;

public class FunctionDemo {
    public static void main(String[] args) {
        Function<Double, Double> circleArea = radius -> Math.PI * radius * radius;
        double radius = 5.0;
        System.out.println("Area of circle with radius " + radius + ": " + circleArea.apply(radius));
    }
}
