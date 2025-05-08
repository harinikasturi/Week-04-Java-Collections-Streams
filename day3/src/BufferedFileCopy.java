import java.io.*;

public class BufferedFileCopy {
    public static void main(String[] args) {
        String source = "largefile.dat";
        String destUnbuffered = "unbuffered_copy.dat";
        String destBuffered = "buffered_copy.dat";
        int bufferSize = 4096; // 4KB

        // Unbuffered copy
        long startTime = System.nanoTime();
        copyFileUnbuffered(source, destUnbuffered, bufferSize);
        long unbufferedTime = System.nanoTime() - startTime;

        // Buffered copy
        startTime = System.nanoTime();
        copyFileBuffered(source, destBuffered, bufferSize);
        long bufferedTime = System.nanoTime() - startTime;

        System.out.println("Unbuffered copy time: " + (unbufferedTime / 1_000_000) + " ms");
        System.out.println("Buffered copy time: " + (bufferedTime / 1_000_000) + " ms");
    }

    private static void copyFileUnbuffered(String source, String dest, int bufferSize) {
        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(dest)) {

            byte[] buffer = new byte[bufferSize];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            System.err.println("Error copying file: " + e.getMessage());
        }
    }

    private static void copyFileBuffered(String source, String dest, int bufferSize) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source), bufferSize);
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest), bufferSize)) {

            byte[] buffer = new byte[bufferSize];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            System.err.println("Error copying file: " + e.getMessage());
        }
    }
}