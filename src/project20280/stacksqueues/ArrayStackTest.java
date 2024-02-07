package project20280.stacksqueues;


import org.junit.jupiter.api.Test;
import project20280.interfaces.Stack;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ArrayStackTest {
    @Test
    void testSize() {
        Stack<Integer> s = new ArrayStack<>();

        assertEquals(0, s.size());

        int N = 16;
        for (int i = 0; i < N; ++i) s.push(i);
        assertEquals(N, s.size());
    }

    @Test
    void testIsEmpty() {
        Stack<Integer> s = new ArrayStack<>();
        for (int i = 0; i < 10; ++i)
            s.push(i);
        for (int i = 0; i < 10; ++i) {
            s.pop();
        }
        assertTrue(s.isEmpty());
    }

    @Test
    void testPush() {
        Stack<Integer> s = new ArrayStack<>();
        for (int i = 0; i < 10; ++i)
            s.push(i);
        assertEquals(10, s.size());
        assertEquals("[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]", s.toString());
    }

    @Test
    void testTop() {
        Stack<Integer> s = new ArrayStack<>();
        for (int i = 0; i < 10; ++i)
            s.push(i);
        assertEquals(9, s.top());
    }

    @Test
    void testPop() {
        Stack<Integer> s = new ArrayStack<>();
        for (int i = 0; i < 10; ++i)
            s.push(i);
        assertEquals(9, s.pop());
        assertEquals(9, s.size());
    }

    @Test
    void testToString() {
        Stack<Integer> s = new ArrayStack<>();
        for (int i = 0; i < 10; ++i)
            s.push(i);
        assertEquals("[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]", s.toString());
    }

    ////////extra

    @Test
    public void testPushOntoFullStackIncreasesSize() {
        // Initial capacity is set to 3 for this example.
        ArrayStack<Integer> stack = new ArrayStack<>(3);

        // Fill the stack to its initial capacity.
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // This operation should trigger resizing of the stack to accommodate more elements.
        stack.push(4);

        // Verify the stack now contains 4 elements, indicating it has resized.
        assertEquals( 4, stack.size());

        // Optionally, verify that the elements are correctly positioned in the stack.
        assertEquals(Integer.valueOf(4), stack.top());
    }
}
