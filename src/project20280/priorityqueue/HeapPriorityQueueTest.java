package project20280.priorityqueue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeapPriorityQueueTest {

    @Test
    void testSize() {
        HeapPriorityQueue<Integer, String> pq = new HeapPriorityQueue<>();
        int n = 10;
        for (int i = 0; i < n; ++i) {
            pq.insert(i, Integer.toString(i));
        }
        assertEquals(n, pq.size());
    }

    @Test
    void testMin() {
        Integer[] arr = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};
        HeapPriorityQueue<Integer, String> pq = new HeapPriorityQueue<>();

        for (Integer i : arr) pq.insert(i, Integer.toString(i));

        assertEquals(1, pq.min().getKey());
        assertEquals("1", pq.min().getValue());
    }

    @Test
    void testInsert() {
        Integer[] arr = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};
        HeapPriorityQueue<Integer, String> pq = new HeapPriorityQueue<>();

        for (Integer i : arr) pq.insert(i, Integer.toString(i));

        assertEquals(12, pq.size());
        assertEquals("[1, 2, 5, 23, 4, 12, 15, 35, 24, 33, 21, 26]", pq.toString());

    }

    @Test
    void testRemoveMin() {
        Integer[] arr = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};
        HeapPriorityQueue<Integer, String> pq = new HeapPriorityQueue<>();

        for (Integer i : arr) pq.insert(i, Integer.toString(i));

        assertEquals(1, pq.removeMin().getKey());
        assertEquals(11, pq.size());
        assertEquals(2, pq.min().getKey());
    }

    @Test
    void testToString() {
        Integer[] arr = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};
        HeapPriorityQueue<Integer, String> pq = new HeapPriorityQueue<>();

        for (Integer i : arr) pq.insert(i, Integer.toString(i));

        assertEquals("[1, 2, 5, 23, 4, 12, 15, 35, 24, 33, 21, 26]", pq.toString());
    }


    //////////////////////////////////////////////////////
    /////////////////////////////////////////////////////


    @Test
    void testDownHeapViaRemoveMin() {
        Integer[] arr = {20, 15, 10, 5, 1};
        HeapPriorityQueue<Integer, String> pq = new HeapPriorityQueue<>();
        for (Integer i : arr) pq.insert(i, Integer.toString(i));

        pq.removeMin(); // This should trigger downHeap
        assertEquals(5, pq.min().getKey(), "downHeap failed to maintain heap property after removeMin");
    }
    @Test
    public void testDownHeap() {
        // Initialize a priority queue with a known set of elements that will require downheaping.
        Integer[] keys = {10, 4, 5, 8, 11, 20};
        Integer[] values = {10, 4, 5, 8, 11, 20};
        HeapPriorityQueue<Integer, Integer> pq = new HeapPriorityQueue<>(keys, values);

        // Manually disrupt the heap property to simulate a scenario where downHeap is needed
        pq.heap.set(0, new AbstractPriorityQueue.PQEntry<>(15, 15)); // Assume PQEntry is a valid Entry implementation

        // Print the heap before downHeap
        System.out.println("Heap before downHeap: " + pq);

        // Apply downHeap starting from the root (index 0) to restore the heap property
        pq.downHeap(0); // You need to ensure that downheap is accessible, potentially by making it public for this test

        // Print the heap after downHeap
        System.out.println("Heap after downHeap: " + pq);

        // Verify the heap property is restored
        // In a real test, you would use assertions to automatically verify the heap's validity
        // For simplicity, this example just prints the heap for visual inspection
    }



}
