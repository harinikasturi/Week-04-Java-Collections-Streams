import java.io.*;

public class LargeFileReader {
    public static void main(String[] args) {
        String filename = "large_log_file.txt";
        String searchTerm = "error";

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.toLowerCase().contains(searchTerm.toLowerCase())) {
                    System.out.printf("Line %d: %s%n", lineNumber, line);
                }

                // Print progress every 100,000 lines
                if (lineNumber % 100_000 == 0) {
                    System.out.println("Processed " + lineNumber + " lines...");
                }
            }
            System.out.println("Finished processing file. Total lines: " + lineNumber);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}