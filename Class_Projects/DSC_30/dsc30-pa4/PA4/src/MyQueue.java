/*
    Name: Lin Tian
    PID:  A16844916
 */

/**
 * Implementation of MyQueue
 *
 * @author Lin Tian
 * @since 10/31/2023
 */

public class MyQueue<T> implements MyQueueInterface<T> {

    DoublyLinkedList<T> myQueue;

    /* ===separation=== */

    /**
     * Creates a new, empty MyQueue using Doubly-Linked List.
     */
    public MyQueue() {
        myQueue =  new DoublyLinkedList<>();
    }

    /**
     * Determine if the queue empty
     *
     * @return whether or not the list is empty
     */
    public boolean isEmpty() {
        return myQueue.isEmpty();
    }

    /**
     * Retrieves the amount of elements that are currently in the queue.
     *
     * @return size of the queue
     */
    public int size() {
        return myQueue.size();
    }

    /**
     * Clear the queue
     */
    public void clear() {
        myQueue.clear();
    }

    /**
     * Adds an element at the end of the queue
     *
     * @param data data to be added
     * @throws IllegalArgumentException if passed in data is null
     *
     */
    public void enqueue(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        myQueue.add(data);
    }

    /**
     * Removes the element at the head of the queue
     *
     * @return T type element at the head of the queue (null if queue is empty)
     */
    public T dequeue() {
        if (myQueue.isEmpty()) {
            return null;
        }
        return myQueue.remove(0);
    }

    /**
     * Retrieves the element at the head of the queue
     *
     * @return T type element at the head of the queue (null if queue is empty)
     */
    public T peek() {
        if (myQueue.isEmpty()) {
            return null;
        }
        return myQueue.get(0);
    }

}
