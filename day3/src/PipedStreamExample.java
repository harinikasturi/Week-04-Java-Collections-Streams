import java.io.*;

public class PipedStreamExample {
    public static void main(String[] args) {
        try {
            PipedOutputStream pos = new PipedOutputStream();
            PipedInputStream pis = new PipedInputStream(pos);

            Thread writerThread = new Thread(() -> {
                try {
                    String[] messages = {"Hello", "World", "From", "Piped", "Streams"};
                    for (String msg : messages) {
                        pos.write((msg + "\n").getBytes());
                        Thread.sleep(500);
                    }
                    pos.close();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });

            Thread readerThread = new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(pis))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("Received: " + line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            writerThread.start();
            readerThread.start();

            writerThread.join();
            readerThread.join();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}