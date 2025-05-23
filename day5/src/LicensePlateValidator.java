public class LicensePlateValidator {
    public static void main(String[] args) {
        String[] plates = {"AB1234", "A12345", "XYZ1234", "AB123"};

        for (String plate : plates) {
            System.out.println(plate + " is " + (isValidLicensePlate(plate) ? "Valid" : "Invalid"));
        }
    }

    public static boolean isValidLicensePlate(String plate) {
        return plate.matches("^[A-Z]{2}\\d{4}$");
    }
}