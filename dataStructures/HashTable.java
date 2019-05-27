package datastructures;

/**
 * Hash table implemented with separate chaining of LinkedLists
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// Entry represents a key-value pair
class Entry<K, V> {
  int hash;
  K key;
  V value;

  public Entry(K key, V value) {
    this.key = key;
    this.value = value;
    this.hash = key.hashCode();
  }

  public boolean equals(Entry<K, V> other) {
    if (other == null) throw new IllegalArgumentException("Entry is null");
    if (hash != other.hash) return false; // first check
    return key.equals(other.key);
  }

  @Override public String toString() { return key + " : " + value; }
}

@SuppressWarnings("unchecked")
// Main class for Hash table
public class HashTable<K, V> {
  private int capacity;
  private int threshold;
  private int size = 0;
  private double loadFactor;
  private java.util.LinkedList<Entry<K, V>> [] table;

  public HashTable(int capacity, double loadFactor) {
    if (capacity <= 0) throw new IllegalArgumentException("0 or negative capacity");
    if (loadFactor > 1 || loadFactor <= 0 || Double.isInfinite(loadFactor) ||
            Double.isNaN(loadFactor)) {
      throw new IllegalArgumentException(
            "Illegal loadFactor");
    }
    this.capacity = capacity;
    this.loadFactor = loadFactor;
    threshold = (int) (capacity * loadFactor);
    table = new java.util.LinkedList[this.capacity];
  }

  public int size() { return size; }

  public boolean isEmpty() { return size == 0; }

  public void clear() {
    Arrays.fill(table, null);
    size = 0;
  }

  /**
   * Convert a hashCode into an index that represents the bucket
   * @param keyHash hashCode of a key
   * @return index in the list
   */
  public int normaliseIndex(int keyHash) {
    return (keyHash & 0x7FFFFFFF) % capacity;
  }

  /**
   * Checks if table contains a key
   */
  public boolean containsKey(K key) {
    int bucketIndex = normaliseIndex(key.hashCode());
    return bucketSeekEntry(bucketIndex, key) != null;
  }

  /**
   * Get the value of a key
   */
  public V get(K key) {
    if (key == null) return null;
    int bucketIndex = normaliseIndex(key.hashCode());
    Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);

    if (entry != null) return entry.value;
    return null;
  }

  /**
   * Insert a key-value pair into hash table
   */
  public void put(K key, V value) {
    insert(key, value);
  }

  /**
   * Remove a key-value pair from hash table
   */
  public K remove(K key) {
    if (key == null) return null;
    int bucketIndex = normaliseIndex(key.hashCode());
    return bucketRemoveEntry(bucketIndex, key);
  }

  // Create new Entry with key and value and insert into bucket
  private void insert(K key, V value) {
    if (key == null) throw new IllegalArgumentException("Null key");

    Entry<K, V> newEntry = new Entry<>(key, value);
    int bucketIndex = normaliseIndex(key.hashCode());
    bucketInsertEntry(bucketIndex, newEntry);
  }

  // Insert new entry into given bucket
  private void bucketInsertEntry(int bucketIndex, Entry<K, V> newEntry) {
    LinkedList<Entry<K, V>> bucket = table[bucketIndex];
    if (bucket == null) {
      table[bucketIndex] = bucket = new LinkedList<>();
    }

    // Make sure no previous entries, if there are, update the value
    Entry<K, V> existingEntry = bucketSeekEntry(bucketIndex, newEntry.key);
    if (existingEntry == null) {
      bucket.add(newEntry);
      size++;

      if (size > threshold) {
        resizeTable();
      }
    } else {
      existingEntry.value = newEntry.value;
    }
  }

  // Remove entry from given bucket
  private K bucketRemoveEntry(int bucketIndex, K key) {
    LinkedList<Entry<K, V>> bucket = table[bucketIndex];
    if (bucket == null) return null;

    Entry<K, V> existingEntry = bucketSeekEntry(bucketIndex, key);
    if (existingEntry == null) return null;

    bucket.remove(existingEntry);
    size--;
    return existingEntry.key;
  }

  // Check and return entry with the given key in a certain bucket with given index
  private Entry<K, V> bucketSeekEntry(int bucketIndex, K key) {
    if (key == null) return null;

    LinkedList<Entry<K, V>> bucket = table[bucketIndex];
    if (bucket == null) return null;

    for (Entry<K, V> entry : bucket) {
      if (entry.key.equals(key)) return entry;
    }
    return null;
  }

  // Resize the hash table if size > threshold
  private void resizeTable() {
    capacity *= 2;
    threshold = (int) (capacity * loadFactor);

    LinkedList<Entry<K, V>> [] newTable = new LinkedList[capacity];

    for (int i = 0; i < size; i++) {
      if (table[i] != null) {

        for (Entry<K, V> entry : table[i]) {
          int bucketIndex = normaliseIndex(entry.key.hashCode());
          LinkedList<Entry<K, V>> bucket = newTable[bucketIndex];

          if (bucket == null) {
            newTable[bucketIndex] = bucket = new LinkedList<>();
          }
          bucket.add(entry);
        }
      table[i].clear(); // help garbage collector
      }
    }

    table = newTable;
  }


  // Returns the list of keys found within the hash table (for testing)
  private void keys() {

    List <K> keys = new ArrayList<>();
    for(LinkedList<Entry<K,V>> bucket : table)
      if (bucket != null)
        for (Entry <K,V> entry : bucket)
          keys.add(entry.key);
    for (K key: keys) {
      System.out.print(key + " ");
    }
  }

  public static void main(String[] args) {
    Entry<String, String> entry1 = new Entry<>("dog","Tom");
    Entry<String, String> entry2 = new Entry<>("dog","John");
    System.out.println(entry1.toString());
    System.out.println(entry2.toString());
    System.out.println(entry1.equals(entry2));

    HashTable table = new HashTable<String, String>(10, 0.7);
    table.put("dog", "Tom");
    table.put("dog", "John");
    table.put("dog", "Lucy");
    table.put("cat", "Jason");
    table.put("one", "1");
    table.put("two", "2");

    System.out.println(table.get("dog"));
    System.out.println(table.get("cat"));
    System.out.println(table.remove("dog"));
    table.keys();
  }
}
