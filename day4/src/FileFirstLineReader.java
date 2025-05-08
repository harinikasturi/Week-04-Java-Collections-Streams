import java.io.*;

public class FileFirstLineReader {
    public static void main(String[] args) {
        String filename = "info.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String firstLine = reader.readLine();
            System.out.println("First line: " + firstLine);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}