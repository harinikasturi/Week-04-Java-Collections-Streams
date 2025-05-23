
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    @Test
    void testBankAccountOperations() {
        BankAccount account = new BankAccount();

        account.deposit(100);
        assertEquals(100, account.getBalance());

        account.withdraw(50);
        assertEquals(50, account.getBalance());

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(100));
    }
}