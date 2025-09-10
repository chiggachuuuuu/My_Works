/*
    Name: Lin Tian
    PID:  A16844916
 */

import java.util.EmptyStackException;
import utilities.FullStackException;

/**
 * MyStack Implementation
 * @author Lin Tian
 * @since 10/10/2023
 */
public class MyStack {
    int initialCapacity;
    int size;
    public static final int DEFAULTCAPACITY = 10;
    String[] stringArray;
    /*
    * This is a constructor that initializes a stack:
    * Set the initial capacity;
    * Set the size of the stack (default 0);
    * Set a string array as the stack storage space.
    * @param capacity An integer that set the value for the stack's initial capacity.
    * @throws IllegalArgumentException when capacity is out of valid range (less than 1).
    * */
    public MyStack(int capacity) throws IllegalArgumentException {
        if (capacity < 1) {
            throw new IllegalArgumentException();
        }
        initialCapacity = capacity;
        size = 0;
        stringArray = new String[initialCapacity];
    }

    /*
     * This is a constructor that initializes a stack when no given capacity is passed in:
     * Set the initial capacity to 10;
     * Set the size of the stack (default 0);
     * Set a string array as the stack storage space.
     * */
    public MyStack() {
        this(DEFAULTCAPACITY);
    }

    /*
     * This method tells if the stack is empty.
     * @return a boolean as the result.
     * */
    public boolean isEmpty() {
        int i = 0;
        while (i < initialCapacity) {
            if (stringArray[i] != null) {
                return false;
            } else {
                i++;
            }
        }
        return true;
    }

    /*
     * This method clears all the elements in the stack.
     * */
    public void clear() {
        for (int i = 0; i < initialCapacity; i++){
            stringArray[i] = null;
            size = 0;
        }
    }

    /*
     * This method gives the size of the stack.
     * @return an integer that represents the number of elements currently stored in the stack.
     * */
    public int size() {
        return size;
    }

    /*
     * This method gives the capacity of the stack.
     * @return an integer that represents the maximum number of elements the stack can store.
     * */
    public int capacity() {
        return initialCapacity;
    }

    /*
     * This method gives the top element of the stack.
     * @return a string that is at the top of the stack.
     * @throws EmptyStackException if the stack is empty.
     * */
    public String peek() throws EmptyStackException {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        for (int i = 0; i < initialCapacity; i++){
            if (stringArray[i] != null){         // Search for the first element in the back
                return stringArray[i];
            }
        }
        return null;
    }

    /*
     * This method pushes the given element to the stack.
     * @param element A string that needs to be pushed into the stack.
     * @throws FullStackException if the stack reaches full capacity
     * with the message “Your stack is full.”
     * @throws IllegalArgumentException if element is null.
     * */
    public void push(String element) throws IllegalArgumentException, FullStackException {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (size == initialCapacity) {
            throw new FullStackException("Your stack is full.");
        }
        stringArray[initialCapacity - size - 1] = element;
        size += 1;
    }

    /*
     * This method returns and removes the top element of the stack.
     * @return a string that is at the top of the stack.
     * @throws EmptyStackException if the stack is empty
     * */
    public String pop() throws EmptyStackException {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        String popString = stringArray[initialCapacity - size];
        stringArray[initialCapacity - size] = null;
        size -= 1;
        return popString;
    }

    /*
     * This method pushes all strings in the given array to the stack.
     * @param elements A string array contains all the string elements that need to be pushed.
     * @throws FullStackException once stack reaches full capacity
     * with the message “Your stack is full.”
     * @throws IllegalArgumentException if elements is null.
     * */
    public void multiPush(String[] elements) throws IllegalArgumentException, FullStackException {
        if (elements == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < elements.length; i++){  // push every element in the array
            this.push(elements[i]);
        }
    }

    /*
     * This method pops the given amount of elements from the stack,
     * and pops all elements from the stack if the elements inside are not enough
     * for the given amount.
     * @param amount An integer that represent the number of elements that need to be popped.
     * @return a string array that contains all the string elements popped.
     * @throws IllegalArgumentException if amount is not a positive number.
     * */
    public String[] multiPop(int amount) throws IllegalArgumentException {
        if (amount < 1) {
            throw new IllegalArgumentException();
        }
        if (size < amount) {        // When size smaller than amount needed to pop
            int count = size;
            String[] popStrings = new String[size];
            for (int i = 0; i < count; i++) {      // Pop all elements from the array
                popStrings[i] = stringArray[initialCapacity - size];
                stringArray[initialCapacity - size] = null;
                size -= 1;
            }
            return popStrings;
        } else {
            String[] popStrings = new String[amount];
            for (int i = 0; i < amount; i++) {     // Pop given amount of elements from array
                popStrings[i] = stringArray[initialCapacity - size];
                stringArray[initialCapacity - size] = null;
                size -= 1;
            }
            return popStrings;
        }
    }
}