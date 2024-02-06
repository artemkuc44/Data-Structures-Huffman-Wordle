package project20280.list;

import org.junit.jupiter.api.Test;
import project20280.interfaces.List;

import static org.junit.jupiter.api.Assertions.*;


class SinglyLinkedListTest {

    @Test
    void testIsEmpty() {
        List<Integer> ll = new SinglyLinkedList<Integer>();
        assertTrue(ll.isEmpty());
        assertEquals("[]", ll.toString());

        ll.addLast(1);
        assertFalse(ll.isEmpty());

        ll.removeLast();
        assertTrue(ll.isEmpty());
    }

    @Test
    void testGet() {
        List<Integer> ll = new SinglyLinkedList<Integer>();
        ll.addLast(1);
        ll.addLast(2);
        ll.addLast(3);

        Integer r = ll.get(2);
        assertEquals(3, r, "did not get the right element: " + r);
    }


    @Test
    void testAdd() {
        List<Integer> ll = new SinglyLinkedList<Integer>();
        ll.addLast(1);
        ll.addLast(2);
        ll.addLast(3);

        ll.add(1, 100);

        assertEquals("[1, 100, 2, 3]", ll.toString(), "item not added correctly");
    }

    @Test
    void testRemove() {
        List<Integer> ll = new SinglyLinkedList<Integer>();
        ll.addLast(1);
        ll.addLast(2);
        ll.addLast(3);

        assertEquals(3, ll.remove(2), "the removed value should be 3");
        assertEquals(2, ll.size(), "the size should be 2");
    }

    @Test
    void testSize() {
        List<Integer> ll = new SinglyLinkedList<Integer>();
        assertEquals(0, ll.size());

        ll.addFirst(1);
        assertEquals(1, ll.size());

        ll.removeFirst();
        assertEquals(0, ll.size());
    }

    @Test
    void testRemoveFirst() {
        List<Integer> ll = new SinglyLinkedList<Integer>();
		assertNull(ll.removeFirst());

        ll.addLast(1);
        ll.addLast(2);
        ll.addLast(3);
        Integer r = ll.removeFirst();
        assertEquals(1, r);
        assertEquals(2, ll.size());
    }

    @Test
    void testRemoveLast() {
        List<Integer> ll = new SinglyLinkedList<Integer>();
        ll.addLast(1);
        ll.addLast(2);
        ll.addLast(3);
        assertEquals(3, ll.removeLast());
        assertEquals(2, ll.size());
    }

    @Test
    void testAddFirst() {
        List<Integer> ll = new SinglyLinkedList<Integer>();
        ll.addLast(-1);
        ll.addFirst(1);

        assertEquals(2, ll.size());
        assertEquals("[1, -1]", ll.toString());
    }

    @Test
    void testAddLast() {
        List<Integer> ll = new SinglyLinkedList<Integer>();
        ll.addFirst(1);
        ll.addLast(-1);

        assertEquals(2, ll.size());
        assertEquals("[1, -1]", ll.toString());
    }

    @Test
    void testToString() {
        List<Integer> ll = new SinglyLinkedList<Integer>();
        ll.addLast(1);
        ll.addLast(2);
        ll.addLast(3);
        assertEquals("[1, 2, 3]", ll.toString());
    }

    @Test
    void testReverseEmptyList() {
        //note: SinglyLinkedList as reverse not part of the List interface
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        ll.reverse();
        assertTrue(ll.isEmpty(), "Empty list should remain empty after reverse");
    }

    @Test
    void testReverseSingleElementList() {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        ll.addLast(1);
        ll.reverse();
        assertEquals("[1]", ll.toString(), "Single element list should remain unchanged after reverse");
    }

    @Test
    void testReverseMultipleElementsList() {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        ll.addLast(1);
        ll.addLast(2);
        ll.addLast(3);
        ll.reverse();
        assertEquals("[3, 2, 1]", ll.toString(), "List elements should be reversed");
    }

    @Test
    void testReverseMoreElementsList() {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        ll.addLast(1);
        ll.addLast(2);
        ll.addLast(3);
        ll.addLast(4);
        ll.addLast(5);
        ll.reverse();
        assertEquals("[5, 4, 3, 2, 1]", ll.toString(), "List elements should be reversed with more elements");
    }

    ///////////////////////////////////////////////////////
    @Test
    void testCopyEmptyList() {
        SinglyLinkedList<Integer> originalList = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> copiedList = originalList.copy();

        assertNotNull(copiedList, "Copied list should not be null.");
        assertTrue(copiedList.isEmpty(), "Copied list should be empty.");
        assertNotSame(originalList, copiedList, "Copied list should be a different instance.");
    }

    @Test
    void testCopyNonEmptyList() {
        SinglyLinkedList<Integer> originalList = new SinglyLinkedList<>();
        originalList.addLast(1);
        originalList.addLast(2);
        originalList.addLast(3);

        SinglyLinkedList<Integer> copiedList = originalList.copy();

        assertNotNull(copiedList, "Copied list should not be null.");
        assertFalse(copiedList.isEmpty(), "Copied list should not be empty.");
        assertEquals(originalList.size(), copiedList.size(), "Copied list should have the same size as the original.");
        assertEquals("[1, 2, 3]", copiedList.toString(), "Copied list elements should match the original list.");
        assertNotSame(originalList, copiedList, "Copied list should be a different instance.");

        // Verify that the elements are the same but not the same instances (deep copy)
        for (int i = 0; i < originalList.size(); i++) {
            assertEquals(originalList.get(i), copiedList.get(i), "Elements at index " + i + " should be equal.");
        }
    }

    @Test
    void testCopyPreservesOrder() {
        SinglyLinkedList<Integer> originalList = new SinglyLinkedList<>();
        originalList.addLast(3);
        originalList.addLast(2);
        originalList.addLast(1);

        SinglyLinkedList<Integer> copiedList = originalList.copy();
        assertEquals("[3, 2, 1]", copiedList.toString(), "Copied list should preserve the order of elements.");
    }

}
