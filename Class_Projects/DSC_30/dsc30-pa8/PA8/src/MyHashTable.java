/*
 * Name: Lin Tian
 * PID: A16844916
 */

import java.util.LinkedList;

/**
 * Implementation of MyHashTable class
 * 
 * @author Lin Tian
 * @since 12/04/2023
 */

public class MyHashTable implements KeyedSet {

    /* instance variables */
    private int size; // number of elements stored
    private LinkedList<String>[] table; // data table
    private int capacity; // table capacity
    private int rehashCount; // num of rehash
    private int numCollision; // number of collisions
    private String statsLog; // stats log of rehashes
    private static final int DEFAULT_CAPACITY = 20; // default capacity
    private static final int MIN_CAPACITY = 5; // default capacity
    private static final int HASH_FUNC_LEFT_SHIFT_NUM = 5;
    private static final int HASH_FUNC_RIGHT_SHIFT_NUM = 27;

    /**
     * Instantiates MyHashTable with default capacity
     */

    public MyHashTable() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Instantiates MyHashTable with given capacity
     *
     * @param capacity the given capacity
     * @throws IllegalArgumentException when the given capacity is less than 5
     */

    @SuppressWarnings("unchecked")
    public MyHashTable(int capacity) throws IllegalArgumentException {
        if (capacity < MIN_CAPACITY) {
            throw new IllegalArgumentException();
        }
        this.size = 0;
        this.capacity = capacity;
        this.table = new LinkedList[capacity];
        this.rehashCount = 0;
        this.numCollision = 0;
        this.statsLog = "";
        for (int i = 0; i < capacity; i++) {
            this.table[i] = new LinkedList<String>();
        } // populate the table with empty linked list
    }

    /**
     * Insert element in the hash table
     *
     * @param value element to insert
     * @return true if the item is inserted, false if it already exists
     * @throws NullPointerException if value is null
     */
    public boolean insert(String value) throws NullPointerException {
        if (value == null) {
            throw new NullPointerException();
        }
        if (this.lookup(value)) {
            return false;
        } else {
            float loadFactor = (float) this.size() / this.capacity();
            if (loadFactor > 1.00) {
                rehash(loadFactor);
            } // rehash the table if load factor is too big
            int locateIndex = hashString(value);
            if (! table[locateIndex].isEmpty()) {
                this.numCollision++; // update the number of collision
            }
            table[locateIndex].add(value);
            this.size++;
            return true;
        }
    }

    /**
     * Delete element from the hash table
     *
     * @param value element to delete
     * @return true if the item is deleted, false if it doesn't exists
     * @throws NullPointerException if value is null
     */
    public boolean delete(String value) throws NullPointerException {
        if (value == null) {
            throw new NullPointerException();
        }
        if (this.lookup(value)) {
            int locateIndex = hashString(value);
            table[locateIndex].remove(value);
            this.size--;
            return true;
        } else {
            return false;
        }

    }

    /**
     * Look up for the element in the hash table
     *
     * @param value element to look up for
     * @return true if the item exists, false if it does not
     * @throws NullPointerException if value is null
     */
    public boolean lookup(String value) throws NullPointerException {
        if (value == null) {
            throw new NullPointerException();
        }
        int locateIndex = hashString(value);
        return table[locateIndex].contains(value);
    }

    /**
     * Returns an array of all elements stored in the hashtable
     *
     * @return A string array with all the elements in the hash table
     */
    public String[] returnAll() {
        String[] outputArray = new String[this.size()];
        int count = 0;
        for (int i = 0; i < this.capacity(); i++) {
            if (!table[i].isEmpty()) {
                for (int j = 0; j < table[i].size(); j++) {
                    outputArray[count] = table[i].get(j);
                    count++;
                }
            }
        } // loop through the whole table
        return outputArray;
    }

    /**
     * Getter method for the size of the table
     *
     * @return the number of elements currently stored in the HashTable
     */
    public int size() {
        return this.size;
    }

    /**
     * Getter method for the capacity of the table
     *
     * @return the total capacity of the table in the HashTable
     */
    public int capacity() {
        return this.capacity;
    }

    /**
     * Getter method for the stats log of the table
     *
     * @return the stats log of the HashTable in a String
     */
    public String getStatsLog() {
        return this.statsLog;
    }

    /**
     * Utility function provided to help with debugging
     */
    public void printTable() {
        String s = "";
        for (int i = 0; i < table.length; i++) {
            s = s + i + ":";
            if (!table[i].isEmpty()) {
                for (String t : table[i])
                    s = s + " " + t + ",";
                // remove trailing comma
                s = s.substring(0, s.length() - 1);
            }
            s = s + "\n";
        }
        // remove trailing newline
        s = s.substring(0, s.length() - 1);
        System.out.println(s);
    }

    /**
     * Private rehash method to double the size of the HashTable
     *
     * @param loadFactor the load factor before each rehash(passed in as a record)
     */
    @SuppressWarnings("unchecked")
    private void rehash(float loadFactor) {
        this.rehashCount++;     // update the number of rehash(es)
        // Store the stats before each rehash (load factor and the number of collision(s))
        String lf = String.format("%.2f", loadFactor);
        statsLog += "Before rehash # " + this.rehashCount + ": load factor " +
                lf + ", " + this.numCollision + " collision(s).\n";
        this.numCollision = 0; // set the count of collision back to 0
        // Double the size of the HashTable
        int newCapacity = this.capacity * 2;
        LinkedList<String>[] newTable = new LinkedList[newCapacity];
        // Populate the HashTable and instantiate
        for (int i = 0; i < newCapacity; i++) {
            newTable[i] = new LinkedList<String>();
        }
        String[] reinsert = this.returnAll();
        this.table = newTable;
        this.size = 0;
        this.capacity = newCapacity;
        // Reinsert all the elements into the new HashTable
        for (int j = 0; j < reinsert.length; j++) {
            this.insert(reinsert[j]);
        }
    }

    /**
     * Private hashString method to convert a given string to an index in the HashTable
     *
     * @param value given string
     * @return the index in the HashTable
     */
    private int hashString(String value) {
        int hashValue = 0;
        for (int i = 0; i < value.length(); i++) {
            int leftShiftedValue = hashValue << HASH_FUNC_LEFT_SHIFT_NUM;  // left shift
            int rightShiftedValue = hashValue >>> HASH_FUNC_RIGHT_SHIFT_NUM;  // right shift
            // | is bitwise OR, ^ is bitwise XOR
            hashValue = (leftShiftedValue | rightShiftedValue) ^ value.charAt(i);
        }
        return Math.abs(hashValue % this.capacity());
    }
}
