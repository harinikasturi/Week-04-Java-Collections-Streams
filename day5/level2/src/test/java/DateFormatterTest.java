
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DateFormatterTest {
    @Test
    void testFormatDate() {
        assertEquals("31-12-2022", DateFormatter.formatDate("2022-12-31"));
        assertEquals("01-01-2023", DateFormatter.formatDate("2023-01-01"));
    }

    @Test
    void testInvalidDate() {
        assertThrows(IllegalArgumentException.class, () -> DateFormatter.formatDate("invalid-date"));
    }
}