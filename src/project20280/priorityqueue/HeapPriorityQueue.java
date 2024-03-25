package project20280.priorityqueue;

/*
 */

import project20280.interfaces.Entry;

import java.util.ArrayList;
import java.util.Comparator;


/**
 * An implementation of a priority queue using an array-based heap.
 */

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

    /**
     * Creates an empty priority queue based on the natural ordering of its keys.
     */
    public HeapPriorityQueue() {
        super();
    }

    /**
     * Creates an empty priority queue using the given comparator to order keys.
     *
     * @param comp comparator defining the order of keys in the priority queue
     */
    public HeapPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Creates a priority queue initialized with the respective key-value pairs. The
     * two arrays given will be paired element-by-element. They are presumed to have
     * the same length. (If not, entries will be created only up to the length of
     * the shorter of the arrays)
     *
     * @param keys   an array of the initial keys for the priority queue
     * @param values an array of the initial values for the priority queue
     */
    public HeapPriorityQueue(K[] keys, V[] values) {
        // TODO
        super();

        if(keys.length != values.length){
            throw new IllegalStateException("keys and values array must be same length");
        }else{
            for(int i = 0;i<keys.length;i++){
                Entry<K,V> entry = new PQEntry<>(keys[i],values[i]);
                heap.add(i,entry);
            }
        }
        this.heapify();

    }

    // protected utilities
    protected int parent(int j) {
        // TODO
        return (j-1)/2;
    }

    protected int left(int j) {
        // TODO
        return 2 * j +1;
    }

    protected int right(int j) {
        // TODO
        return 2 * j + 2;
    }

    protected boolean hasLeft(int j) {
        // TODO
        return left(j) < heap.size();
    }

    protected boolean hasRight(int j) {
        // TODO
        return right(j) < heap.size();
    }

    /**
     * Exchanges the entries at indices i and j of the array list.
     */
    protected void swap(int i, int j) {
        // TODO
        Entry<K,V> temp = heap.get(i);
        heap.set(i,heap.get(j));
        heap.set(j,temp);

    }

    /**
     * Moves the entry at index j higher, if necessary, to restore the heap
     * property.
     */
    private void upHeap(int childIndex) {
        //TODO

        while(childIndex>0){//not root
            int parentInd = parent(childIndex);
            if(compare(heap.get(childIndex), heap.get(parentInd))>=0){
                break;
            }
            swap(childIndex,parentInd);
            childIndex = parentInd;
        }
    }

    /**
     * Moves the entry at index j lower, if necessary, to restore the heap property.
     */
    protected void downHeap(int j) {//bubble down
        //TODO
        int smallChildInd;
        int leftInd;
        while(hasLeft(j)){
            leftInd = left(j);
            smallChildInd = leftInd;
            if(hasRight(j)){
                if(compare(heap.get(left(j)),heap.get(right(j))) > 0){
                    smallChildInd = right(j);
                }
            }
            if(compare(heap.get(smallChildInd),heap.get(j)) < 0){
                swap(smallChildInd,j);
                j = smallChildInd;
            }else{
                break;
            }
        }
    }

    /**
     * Performs a bottom-up construction of the heap in linear time.
     */
    protected void heapify( ) {
        int startIndex = parent(size( ) - 1); // start at PARENT of last entry
        for (int j=startIndex; j >= 0; j--) // loop until processing the root
        downHeap(j);
    }

    // public methods

    /**
     * Returns the number of items in the priority queue.
     *
     * @return number of items
     */
    @Override
    public int size() {
        return heap.size();
    }

    /**
     * Returns (but does not remove) an entry with minimal key.
     *
     * @return entry having a minimal key (or null if empty)
     */
    @Override
    public Entry<K, V> min() {
        Entry<K,V> min = heap.get(0);
        for(Entry<K,V> entry:heap){
            if(compare(entry,min) <= 0){
                min = entry;
            }
        }
        return min;
    }

    /**
     * Inserts a key-value pair and return the entry created.
     *
     * @param key   the key of the new entry
     * @param value the associated value of the new entry
     * @return the entry storing the new key-value pair
     * @throws IllegalArgumentException if the key is unacceptable for this queue
     */
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        // TODO
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null");
        Entry<K,V> newest = new PQEntry<>(key,value);

        heap.add(newest);
        //System.out.println(heap.indexOf(newest) + " == " + (heap.size()-1));

        upHeap(heap.size()-1);

        return newest;
    }

    /**
     * Removes and returns an entry with minimal key.
     *
     * @return the removed entry (or null if empty)
     */
    @Override
    public Entry<K, V> removeMin() {
        // TODO
        return heap.remove(heap.indexOf(min()));
    }

    public String toString() {
        return heap.toString();
    }

    /**
     * Used for debugging purposes only
     */
    private void sanityCheck() {
        for (int j = 0; j < heap.size(); j++) {
            int left = left(j);
            int right = right(j);
            //System.out.println("-> " +left + ", " + j + ", " + right);
            Entry<K, V> e_left, e_right;
            e_left = left < heap.size() ? heap.get(left) : null;
            e_right = right < heap.size() ? heap.get(right) : null;
            if (left < heap.size() && compare(heap.get(left), heap.get(j)) < 0) {
                System.out.println("Invalid left child relationship");
                System.out.println("=> " + e_left + ", " + heap.get(j) + ", " + e_right);
            }
            if (right < heap.size() && compare(heap.get(right), heap.get(j)) < 0) {
                System.out.println("Invalid right child relationship");
                System.out.println("=> " + e_left + ", " + heap.get(j) + ", " + e_right);
            }
        }
    }

    public static <E> void pqSort(E[] array) {
        // Create a priority queue (min-heap) to hold the elements of the array
        HeapPriorityQueue<Integer, E> pq = new HeapPriorityQueue<>();

        // Phase 1: Insert all array elements into the heap
        for (E element : array) {
            pq.insert((Integer) element, element);
        }

        // Phase 2: Extract elements from the heap and store them back into the array
        for (int i = 0; i < array.length; i++) {
            Entry<Integer, E> entry = pq.removeMin();
            array[i] = entry.getValue();
        }
    }


    public static void main(String[] args) {
        Integer[] rands = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};
        HeapPriorityQueue<Integer, Integer> pq = new HeapPriorityQueue<>(rands, rands);

        System.out.println("elements: " + rands);
        System.out.println("after adding elements: " + pq);

        System.out.println("min element: " + pq.min());

        pq.removeMin();
        System.out.println("after removeMin: " + pq);
        // [             1,
        //        2,            4,
        //   23,     21,      5, 12,
        // 24, 26, 35, 33, 15]
    }
}
