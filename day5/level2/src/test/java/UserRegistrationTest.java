import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserRegistrationTest {
    @Test
    void testValidRegistration() {
        assertDoesNotThrow(() -> UserRegistration.registerUser("user1", "user@example.com", "password123"));
    }

    @Test
    void testInvalidRegistration() {
        assertThrows(IllegalArgumentException.class,
                () -> UserRegistration.registerUser("", "user@example.com", "password123"));

        assertThrows(IllegalArgumentException.class,
                () -> UserRegistration.registerUser("user1", "invalid-email", "password123"));

        assertThrows(IllegalArgumentException.class,
                () -> UserRegistration.registerUser("user1", "user@example.com", "short"));
    }
}