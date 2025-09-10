/*
 * Name: Lin Tian
 * PID: A16844916
 */

/**
 * Implementation of MyBloomFilter with MyHashTable
 *
 * @author Lin Tian
 * @since 12/08/2023
 */

public class MyBloomFilter implements KeyedSet {

    private static final int DEFAULT_M = (int) 1e7;
    private static final int HASH_FUNC_A_LEFT_SHIFT_NUM = 5;
    private static final int HASH_FUNC_A_RIGHT_SHIFT_NUM = 27;
    private static final int HASH_FUNC_B_MULTIPLIER = 27;
    private static final int HASH_FUNC_C_LEFT_SHIFT_NUM = 8;

    boolean[] bits;

    /**
     * Initialize MyBloomFilter with the default number of bits
     */
    public MyBloomFilter() {
        bits = new boolean[DEFAULT_M];
    }

    /**
     * Insert the string key into the bloom filter.
     *
     * @param key key to insert
     * @throws NullPointerException if value is null
     * @return true if the key was inserted, false if the key was already
     *         present
     */
    public boolean insert(String key) throws NullPointerException {
        if (key == null) {
            throw new NullPointerException();
        }
        if (this.lookup(key)) {
            return false;
        } else {
            int indexA = hashFuncA(key);
            int indexB = hashFuncB(key);
            int indexC = hashFuncC(key);
            this.bits[indexA] = true;
            this.bits[indexB] = true;
            this.bits[indexC] = true;
            return true;
        }
    }

    /**
     * Check if the given key is present in the bloom filter.
     * @param key key to look up
     * @throws NullPointerException if value is null
     * @return true if the key was found, false if the key was not found
     */
    public boolean lookup(String key) throws NullPointerException {
        if (key == null) {
            throw new NullPointerException();
        }
        int indexA = hashFuncA(key);
        int indexB = hashFuncB(key);
        int indexC = hashFuncC(key);
        return (this.bits[indexA] && this.bits[indexB] && this.bits[indexC]);
    }

    /**
     * First hash function to be used by MyBloomFilter
     * @param value The input string
     * @return A hashcode for the string
     */
    private int hashFuncA(String value) {
        int hashValue = 0;
        for (int i = 0; i < value.length(); i++) {
            int leftShiftedValue = hashValue << HASH_FUNC_A_LEFT_SHIFT_NUM;  // left shift
            int rightShiftedValue = hashValue >>> HASH_FUNC_A_RIGHT_SHIFT_NUM;  // right shift
            // | is bitwise OR, ^ is bitwise XOR
            hashValue = (leftShiftedValue | rightShiftedValue) ^ value.charAt(i);
        }
        return Math.abs(hashValue % DEFAULT_M);
    }

    /**
     * Second hash function to be used by MyBloomFilter
     * @param value The input string
     * @return A hashcode for the string
     */
    private int hashFuncB(String value) {
        int hashVal = 0;
        for (int i = 0; i < value.length(); i++) {  // left to right
            int letter = value.charAt(i);  // get char code
            hashVal = (hashVal * HASH_FUNC_B_MULTIPLIER + letter) % DEFAULT_M;  // mod
        }
        return hashVal;
    }

    /**
     * Third hash function to be used by MyBloomFilter
     * @param value The input string
     * @return A hashcode for the string
     */
    private int hashFuncC(String value) {
        int hashVal = 0;
        for (int j = 0; j < value.length(); j++) {
            int letter = value.charAt(j);
            hashVal = ((hashVal << HASH_FUNC_C_LEFT_SHIFT_NUM) + letter) % bits.length;
        }
        return Math.abs(hashVal % bits.length);
    }
}
