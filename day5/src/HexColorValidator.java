public class HexColorValidator {
    public static void main(String[] args) {
        String[] colors = {"#FFA500", "#ff4500", "#123", "#GHIJKL"};

        for (String color : colors) {
            System.out.println(color + " is " + (isValidHexColor(color) ? "Valid" : "Invalid"));
        }
    }

    public static boolean isValidHexColor(String color) {
        return color.matches("^#[0-9A-Fa-f]{6}$");
    }
}