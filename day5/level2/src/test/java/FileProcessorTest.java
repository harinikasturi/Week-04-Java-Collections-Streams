import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileProcessorTest {
    private static final String TEST_FILE = "testfile.txt";

    @BeforeEach
    @AfterEach
    void cleanup() throws IOException {
        Path path = Paths.get(TEST_FILE);
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }

    @Test
    void testFileOperations() throws IOException {
        String content = "Hello, JUnit!";

        FileProcessor.writeToFile(TEST_FILE, content);
        assertTrue(Files.exists(Paths.get(TEST_FILE)));

        String readContent = FileProcessor.readFromFile(TEST_FILE);
        assertEquals(content, readContent);
    }

    @Test
    void testReadNonExistentFile() {
        assertThrows(IOException.class, () -> FileProcessor.readFromFile("nonexistent.txt"));
    }
}