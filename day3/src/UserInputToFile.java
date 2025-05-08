import java.io.*;

public class UserInputToFile {
    public static void main(String[] args) {
        String filename = "userdata.txt";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             FileWriter writer = new FileWriter(filename)) {

            System.out.print("Enter your name: ");
            String name = reader.readLine();

            System.out.print("Enter your age: ");
            String age = reader.readLine();

            System.out.print("Enter your favorite programming language: ");
            String language = reader.readLine();

            writer.write("Name: " + name + "\n");
            writer.write("Age: " + age + "\n");
            writer.write("Favorite Language: " + language + "\n");

            System.out.println("Data saved to " + filename);

        } catch (IOException e) {
            System.err.println("Error handling user input: " + e.getMessage());
        }
    }
}