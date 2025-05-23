
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {
    @Test
    void testPasswordValidator() {
        assertTrue(PasswordValidator.isValid("Password1"));
        assertTrue(PasswordValidator.isValid("Secure123"));

        assertFalse(PasswordValidator.isValid("short"));
        assertFalse(PasswordValidator.isValid("nouppercase1"));
        assertFalse(PasswordValidator.isValid("NODIGITS"));
        assertFalse(PasswordValidator.isValid(null));
    }
}