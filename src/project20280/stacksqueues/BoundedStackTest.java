package project20280.stacksqueues;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class BoundedStackTest {

    private BoundedStack<Integer> stack;

    @Before
    public void setUp() {
        stack = new BoundedStack<>(3); // Set up a stack with capacity 3 for testing
    }

    @Test
    public void testStackPush() {
        stack.push(1);
        assertFalse("Stack should not be empty after push", stack.isEmpty());
        assertEquals("Stack size should be 1 after one push", 1, stack.size());
    }

    @Test
    public void testStackPop() {
        stack.push(2);
        Integer element = stack.pop();
        assertEquals("Popped element should be the same as pushed", Integer.valueOf(2), element);
        assertTrue("Stack should be empty after pop", stack.isEmpty());
    }

    @Test
    public void testStackTop() {
        stack.push(3);
        Integer topElement = stack.top();
        assertEquals("Top element should be the same as pushed", Integer.valueOf(3), topElement);
        assertEquals("Stack size should not change after top", 1, stack.size());
    }

    @Test(expected = IllegalStateException.class)
    public void testPushOntoFullStack() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        // This push should throw IllegalStateException because the stack is full
        stack.push(4);
    }

    @Test(expected = NoSuchElementException.class)
    public void testPopFromEmptyStack() {
        // This pop should throw NoSuchElementException because the stack is empty
        stack.pop();
    }

    @Test(expected = NoSuchElementException.class)
    public void testTopFromEmptyStack() {
        // This top should throw NoSuchElementException because the stack is empty
        stack.top();
    }

    @Test
    public void testIsEmpty() {
        assertTrue("New stack should be empty", stack.isEmpty());
        stack.push(4);
        assertFalse("Stack should not be empty after push", stack.isEmpty());
    }

    @Test
    public void testIsFull() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertTrue("Stack should be full", stack.size() == stack.maxSize);
    }
}
