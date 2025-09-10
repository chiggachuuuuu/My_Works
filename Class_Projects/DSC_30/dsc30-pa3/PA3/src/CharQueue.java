/*
    Name: Lin Tian
    PID:  A16844916
 */

import java.util.NoSuchElementException;

/**
 * CharQueue Implementation
 *
 * @author Lin Tian
 * @since 10/18/2023
 */

public class CharQueue {
    /* instance variables, feel free to add more if you need */
    private char[] circularArray;
    private int length;
    private int front;
    private int rear;
    private int capacity;
    private static final int INITIAL_CAPACITY = 5;
    private static final int DOUBLE = 2;

    /*
     * This is a constructor that initialize a queue that stores char type elements
     * when no parameter is passed in:
     * The default capacity is 5,
     * Calls CharQueue(5) to initialize the char queue.
     **/
    public CharQueue() {
        this(INITIAL_CAPACITY);
    }

    /*
     * This is a constructor that initialize a queue that stores char type elements:
     * Create a char Array with capacity of the number passed in,
     * Set the size of the array to be 0,
     * set the front pointer index to be -1,
     * set the rear pointer index to be -1.
     * @param capacity An integer that determines the capacity of the array.
     * @throws IllegalArgumentException when capacity is out of valid range (less than 1).
     **/
    public CharQueue(int capacity) throws IllegalArgumentException {
        if (capacity < 1) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        circularArray = new char[this.capacity];
        this.length = 0;
        this.front = -1;
        this.rear = -1;
    }

    /*
     * This method checks if the char queue is empty.
     * @return A boolean that indicates whether the char array is empty.
     **/
    public boolean isEmpty() {
        return length == 0;
    }

    /*
     * This method tells the current size of the char queue.
     * @return An int number that represents the length of the char queue.
     **/
    public int size() {
        return length;
    }

    /*
     * This method clears all elements in the char queue.
     **/
    public void clear() {
        this.front = -1;       // Reset the front pointer
        this.rear = -1;        // Reset the rear pointer
        this.length = 0;       // Clear the array
    }

    /*
    * This is a private helper method that resize the queue:
    * Expand the capacity of the array to be doubled.
    **/
    private void resize() {
        this.capacity = this.capacity * DOUBLE; // Double the capacity
        char[] temp = new char[this.capacity]; // Create a new array with more space
        int i = this.front;
        int j = 0;
        while (i <= this.rear) {
            temp[j] = circularArray[i]; // Add all elements from old queue to new queue
            i++;
            j++;
        }
        circularArray = temp; // Assign the new queue
        front = 0;            // Reset the front pointer
        rear = length - 1;    // Reset the rear pointer
    }

    /*
     * This method adds a char element to the char queue:
     * The element should be added at the rear,
     * The rear pointer should move by 1 position,
     * The length(size) should increase by 1.
     * @param elem A char that represents the element needed to add to the char queue.
     **/
    public void enqueue(char elem) {
        if (length == this.capacity) {                      // When the queue is full
            this.resize();                                  // Resize the queue
        }
        if (this.isEmpty()) {               // When the queue is empty
            front = rear = 0;    // Queue has only 1 element (front and rear at the same place)
        } else {
            rear = (rear + 1) % this.capacity;
        }
        circularArray[rear] = elem;                 // Add the new element in the rear
        length++;
    }

    /*
     * This method peeks the element at the front of the queue:
     * @return A char that is the element at the front of the queue.
     * @throws NoSuchElementException when the queue is empty.
     **/
    public char peek() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return circularArray[front];
    }

    /*
     * This method removes a char element from the char queue:
     * The element should be removed from the front,
     * The front pointer should move by 1 position,
     * The length(size) should decrease by 1.
     * @return A char that represents the elements removed from the queue.
     * @throws NoSuchElementException when the queue is empty.
     **/
    public char dequeue() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        char elem = circularArray[front];    // Save the element at the front for return
        if (front == rear) {                 // The queue only has one element
        front = rear = -1;                   // Reset both pointers since the queue is empty now
        } else {
            front = (front + 1) % this.capacity;
        }
        length--;
        return elem;
    }
}
