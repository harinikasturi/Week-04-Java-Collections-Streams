public class ArrayOperations {
    public static void printValueAtIndex(Integer[] array, int index) {
        try {
            System.out.println("Value at index " + index + ": " + array[index]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid index!");
        } catch (NullPointerException e) {
            System.out.println("Array is not initialized!");
        }
    }

    public static void main(String[] args) {
        Integer[] numbers = {1, 2, 3, 4, 5};
        printValueAtIndex(numbers, 2);  // Valid
        printValueAtIndex(numbers, 10); // Invalid index
        printValueAtIndex(null, 0);     // Null array
    }
}