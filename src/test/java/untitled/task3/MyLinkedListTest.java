package untitled.task3;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

class MyLinkedListTest {
    private MyLinkedList<String> list;

    @BeforeEach
    void setUp() {
        list = new MyLinkedList<>();
    }

    @Test
    void testAddFirst() {
        list.addFirst("first");
        list.addFirst("second");

        assertEquals(2, list.size());
        assertEquals("second", list.get(0));
        assertEquals("first", list.get(1));
    }

    @Test
    void testAddLast() {
        list.addLast("first");
        list.addLast("second");

        assertEquals(2, list.size());
        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
    }

    @Test
    void testAdd() {
        list.add("first");
        list.add("second");

        assertEquals(2, list.size());
        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
    }

    @Test
    void testAddByIndex() {
        list.add("first");
        list.add("third");
        list.add(1, "second");

        assertEquals(3, list.size());
        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
        assertEquals("third", list.get(2));
    }

    @Test
    void testAddByIndexInvalid() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "test"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, "test"));
    }

    @Test
    void testSet() {
        list.add("first");
        list.add("second");
        list.set(1, "modified");

        assertEquals("modified", list.get(1));
    }

    @Test
    void testSetInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, "test"));
    }

    @Test
    void testRemoveFirst() {
        list.add("first");
        list.add("second");

        String removed = list.removeFirst();

        assertEquals("first", removed);
        assertEquals(1, list.size());
        assertEquals("second", list.get(0));
    }

    @Test
    void testRemoveFirstEmpty() {
        assertThrows(NoSuchElementException.class, () -> list.removeFirst());
    }

    @Test
    void testRemoveLast() {
        list.add("first");
        list.add("second");

        String removed = list.removeLast();

        assertEquals("second", removed);
        assertEquals(1, list.size());
        assertEquals("first", list.get(0));
    }

    @Test
    void testRemoveLastEmpty() {
        assertThrows(NoSuchElementException.class, () -> list.removeLast());
    }

    @Test
    void testRemoveByIndex() {
        list.add("first");
        list.add("second");
        list.add("third");

        String removed = list.remove(1);

        assertEquals("second", removed);
        assertEquals(2, list.size());
        assertEquals("first", list.get(0));
        assertEquals("third", list.get(1));
    }

    @Test
    void testRemoveInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    @Test
    void testGet() {
        list.add("first");
        list.add("second");

        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
    }

    @Test
    void testGetInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    void testSize() {
        assertEquals(0, list.size());
        list.add("first");
        assertEquals(1, list.size());
        list.removeFirst();
        assertEquals(0, list.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add("first");
        assertFalse(list.isEmpty());
    }

    @Test
    void testIterator() {
        list.add("first");
        list.add("second");
        list.add("third");

        Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("first", iterator.next());
        assertEquals("second", iterator.next());
        assertEquals("third", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorRemove() {
        list.add("first");
        list.add("second");
        list.add("third");

        Iterator<String> iterator = list.iterator();
        iterator.next(); // first
        iterator.remove(); // remove first

        assertEquals(2, list.size());
        assertEquals("second", list.get(0));
        assertEquals("third", list.get(1));
    }

    @Test
    void testIteratorRemoveWithoutNext() {
        list.add("first");
        Iterator<String> iterator = list.iterator();
        assertThrows(IllegalStateException.class, () -> iterator.remove());
    }

    @Test
    void testIteratorNextOnEmpty() {
        Iterator<String> iterator = list.iterator();
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    void testToString() {
        assertEquals("[]", list.toString());
        list.add("first");
        list.add("second");
        assertEquals("[first, second]", list.toString());
    }

    @Test
    void testComplexOperations() {
        list.addFirst("second");
        list.addFirst("first");
        list.addLast("third");
        list.add(3, "fourth");

        assertEquals("[first, second, third, fourth]", list.toString());

        list.removeFirst();
        assertEquals("[second, third, fourth]", list.toString());

        list.removeLast();
        assertEquals("[second, third]", list.toString());

        list.remove(0);
        assertEquals("[third]", list.toString());

        list.removeLast();
        assertEquals("[]", list.toString());
        assertTrue(list.isEmpty());
    }
}