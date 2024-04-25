package project20280.hashtable;

import project20280.interfaces.Entry;

import java.util.ArrayList;
import java.util.List;

public class ProbeHashMap<K, V> extends AbstractHashMap<K, V> {
    private MapEntry<K, V>[] table;
    private final MapEntry<K, V> DEFUNCT = new MapEntry<>(null, null);

    public ProbeHashMap() {
        super();
    }

    /**
     * Creates a hash table with given capacity and prime factor 109345121.
     */
    public ProbeHashMap(int cap) {
        super(cap);
    }

    /**
     * Creates a hash table with the given capacity and prime factor.
     */
    public ProbeHashMap(int cap, int p) {
        super(cap, p);
    }

    @Override
    protected void createTable() {
        table = new MapEntry[capacity];
    }


    private int findSlot(int h, K k) {
         int avail = -1; // no slot available (thus far)
         int j = h; // index while scanning table
         do {
             if (isAvailable(j)) { // may be either empty or defunct
                 if (avail == -1) avail = j; // this is the first available slot!
                 if (table[j] == null) break; // if empty, search fails immediately
                 } else if (table[j].getKey( ).equals(k))
                 return j; // successful match
             j = (j+1) % capacity; // keep looking (cyclically)
             } while (j != h); // stop if we return to the start
          return -(avail + 1); // search has failed
         }
    @Override
    protected V bucketGet(int h, K k) {
        //todo
        int j = findSlot(h, k);
        if (j < 0) return null; // No matching key found
        return table[j].getValue();
    }

    @Override
    protected V bucketPut(int h, K k, V v) {
        int j = findSlot(h, k);
        if (j >= 0) { // This means key exists
            return table[j].setValue(v);
        }
        table[-(j + 1)] = new MapEntry<>(k, v); // Convert negative value back to positive and place new entry

        n++;
        return null;
    }


    @Override
    protected V bucketRemove(int h, K k) {
        int j = findSlot(h, k);
        if (j < 0) return null; // Key not found
        V answer = table[j].getValue();
        table[j] = DEFUNCT; // Mark the slot as defunct
        n--;
        return answer;
    }


    @Override
    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K, V>> buffer = new ArrayList<>();
        for (int h = 0; h < table.length; h++) {
            if (table[h] != null && table[h] != DEFUNCT)
                buffer.add(table[h]);
        }
        return buffer;
    }

    private boolean isAvailable(int j) {
        return (table[j] == null || table[j] == DEFUNCT);
    }
}
