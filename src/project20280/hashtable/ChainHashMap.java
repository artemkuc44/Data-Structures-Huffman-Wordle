package project20280.hashtable;

import project20280.interfaces.Entry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * Map implementation using hash table with separate chaining.
 */

public class ChainHashMap<K, V> extends AbstractHashMap<K, V> {
    // a fixed capacity array of UnsortedTableMap that serve as buckets
    private UnsortedTableMap<K, V>[] table; // initialized within createTable

    /**
     * Creates a hash table with capacity 11 and prime factor 109345121.
     */
    public ChainHashMap() {
        super();
    }

    /**
     * Creates a hash table with given capacity and prime factor 109345121.
     */
    public ChainHashMap(int cap) {
        super(cap);
    }

    /**
     * Creates a hash table with the given capacity and prime factor.
     */
    public ChainHashMap(int cap, int p) {
        super(cap, p);
    }

    /**
     * Creates an empty table having length equal to current capacity.
     */
    @Override
    @SuppressWarnings({"unchecked"})
    protected void createTable() {
        table = new UnsortedTableMap[capacity];
    }

    /**
     * Returns value associated with key k in bucket with hash value h. If no such
     * entry exists, returns null.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @return associate value (or null, if no such entry)
     */
    @Override
    protected V bucketGet(int h, K k) {
        // TODO
        UnsortedTableMap<K,V> bucket = table[h];
        if(bucket == null){
            return null;
        }
        return  bucket.get(k);
    }

    /**
     * Associates key k with value v in bucket with hash value h, returning the
     * previously associated value, if any.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @param v the value to be associated
     * @return previous value associated with k (or null, if no such entry)
     */
    @Override
    protected V bucketPut(int h, K k, V v) {
        // TODO
        UnsortedTableMap<K,V> bucket = table[h];
        if(bucket == null) {
            bucket = table[h] = new UnsortedTableMap<>();
        }
        int oldSize = bucket.size();
        V ans = bucket.put(k,v);
        n+= (bucket.size() - oldSize);
        return ans;
    }


    /**
     * Removes entry having key k from bucket with hash value h, returning the
     * previously associated value, if found.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @return previous value associated with k (or null, if no such entry)
     */
    @Override
    protected V bucketRemove(int h, K k) {
        // TODO
        UnsortedTableMap<K,V> bucket = table[h];
        if(bucket == null) return null;
        int oldSize = bucket.size();
        V ans = bucket.remove(k);
        n -= oldSize - bucket.size();
        return ans;
    }

    /**
     * Returns an iterable collection of all key-value entries of the map.
     *
     * @return iterable collection of the map's entries
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        /*
        for each element in (UnsortedTableMap []) table
            for each element in bucket:
                print element
        */
        ArrayList<Entry<K, V>> entries = new ArrayList<>();
        for (UnsortedTableMap<K, V> tm : table) {
            if (tm != null) {
                for (Entry<K, V> e : tm.entrySet()) {
                    entries.add(e);
                }
            }
        }
        return entries;
    }
    public V getOrDefault(K key, V defaultValue) {//needed for huffman + example
        int h = hashValue(key); //compute the hash value for the key to find the correct bucket
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null) {
            return defaultValue; //return the default value if the bucket is empty
        }
        V value = bucket.get(key);
        return (value != null) ? value : defaultValue; //return the found value or the default value if null
    }

    public double getLoadFactor() {
        return (double) super.size() / capacity;
    }

    public void printHashMap() {
        System.out.println("ChainHashMap contents:");
        for (int i = 0; i < table.length; i++) {
            UnsortedTableMap<K, V> bucket = table[i];
            // Check if the bucket is not empty
            if (bucket != null && !bucket.isEmpty()) {
                System.out.print("Bucket " + i + ": ");
                for (Entry<K, V> entry : bucket.entrySet()) {
                    System.out.print("(" + entry.getKey() + ", " + entry.getValue() + ") ");
                }
                System.out.print("\n"); // Move to the next line after printing all entries in a bucket
            }
        }
    }



    public String toString() {
        return entrySet().toString();
    }

    public static project20280.hashtable.ChainHashMap<String, Integer> countWordFrequency(String filePath) throws FileNotFoundException {
        project20280.hashtable.ChainHashMap<String, Integer> counter = new project20280.hashtable.ChainHashMap<>();

        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase();

            counter.put(word, counter.getOrDefault(word, 0) + 1);
        }

        return counter;
    }

    public static void printTopFrequentWords(project20280.hashtable.ChainHashMap<String, Integer> counter, int topN) {
        Comparator<Entry<String, Integer>> entryComparator = Comparator.comparing(Entry::getValue);//custom

        PriorityQueue<Entry<String, Integer>> pq = new PriorityQueue<>(entryComparator.reversed());//min ->max.heap

        for (Entry<String, Integer> entry : counter.entrySet()) {
            pq.offer(entry);
        }

        System.out.println("Top " + topN + " most frequently used words:");
        int count = 0;
        while (count < topN && !pq.isEmpty()) {
            Entry<String, Integer> entry = pq.poll();
            System.out.println(entry.getKey() + ": " + entry.getValue());
            count++;
        }
    }

    public int countCollisions() {
        int collisionCount = 0;
        // Iterate through each bucket
        for (UnsortedTableMap<K, V> bucket : table) {
            if (bucket != null && bucket.size() > 1) {  //check if the bucket exists and has more than one entry
                collisionCount += (bucket.size() - 1);  //add the number of collisions in this bucket
            }
        }
        return collisionCount;
    }


    public static void main(String[] args) {
        try {
            String filePath = "src/wordle/resources/sample.txt";
            System.out.println(filePath);
            project20280.hashtable.ChainHashMap<String, Integer> counter = countWordFrequency(filePath);
            printTopFrequentWords(counter, 10);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

}
