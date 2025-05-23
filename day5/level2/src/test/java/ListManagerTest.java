

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ListManagerTest {
    @Test
    void testListOperations() {
        List<Integer> list = new ArrayList<>();

        assertEquals(0, ListManager.getSize(list));

        ListManager.addElement(list, 5);
        assertEquals(1, ListManager.getSize(list));
        assertTrue(list.contains(5));

        ListManager.addElement(list, 10);
        assertEquals(2, ListManager.getSize(list));

        assertTrue(ListManager.removeElement(list, 5));
        assertEquals(1, ListManager.getSize(list));
        assertFalse(list.contains(5));

        assertFalse(ListManager.removeElement(list, 99));
    }
}