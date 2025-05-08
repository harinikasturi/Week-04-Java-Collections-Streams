import java.io.*;

public class StudentDataStorage {
    public static void main(String[] args) {
        String filename = "students.dat";

        // Write student data
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename))) {
            dos.writeInt(101); // roll number
            dos.writeUTF("Alice Johnson"); // name
            dos.writeDouble(3.8); // GPA

            dos.writeInt(102);
            dos.writeUTF("Bob Smith");
            dos.writeDouble(3.5);

            System.out.println("Student data written to file");
        } catch (IOException e) {
            System.err.println("Error writing student data: " + e.getMessage());
        }

        // Read student data
        try (DataInputStream dis = new DataInputStream(new FileInputStream(filename))) {
            System.out.println("\nStudent Data:");
            while (dis.available() > 0) {
                int rollNo = dis.readInt();
                String name = dis.readUTF();
                double gpa = dis.readDouble();

                System.out.printf("Roll No: %d, Name: %s, GPA: %.2f%n", rollNo, name, gpa);
            }
        } catch (IOException e) {
            System.err.println("Error reading student data: " + e.getMessage());
        }
    }
}