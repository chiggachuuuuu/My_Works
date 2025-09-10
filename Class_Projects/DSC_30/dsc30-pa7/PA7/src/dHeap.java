/*
 * Name: Lin Tian
 * PID:  A16844916
 */

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Title: dHeap Description: This program creates a Heap with d branching factor
 *
 * @author Lin Tian
 * @since  11/27/2023
 *
 * @param <T> the type of elements held in this collection
 */

public class dHeap<T extends Comparable<? super T>> implements HeapInterface<T> {

    private T[] heap;   // backing array
    private int d;      // branching factor
    private int nelems; // number of elements
    private boolean isMaxHeap; // indicates whether heap is max or min
    private static final int DEFAULT_SIZE = 10;

    /**
     * Initializes a binary max heap with capacity = 10
     */
    @SuppressWarnings("unchecked")
    public dHeap() {
        this(2, DEFAULT_SIZE, true);
    }

    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int heapSize) {
        this(2, heapSize, true);
    }

    /**
     * Initializes a d-ary heap (with a given value for d), with a given initial
     * capacity.
     *
     * @param d         The number of child nodes each node in the heap should have.
     * @param heapSize  The initial capacity of the heap.
     * @param isMaxHeap indicates whether the heap should be max or min
     * @throws IllegalArgumentException if d is less than one.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int d, int heapSize, boolean isMaxHeap) throws IllegalArgumentException {
        if (d < 1) {
            throw new IllegalArgumentException();
        }
        this.d = d;
        this.nelems = 0;
        this.isMaxHeap = isMaxHeap;
        this.heap = (T[]) new Comparable[heapSize];
    }

    /**
     * Getter method for the number of elements stored in the heap.
     *
     * @return the number of elements stored in the heap.
     */
    @Override
    public int size() {
        return this.nelems;
    }

    /**
     * Returns and removes the root element from the heap.
     *
     * @return the root element from the heap.
     * @throws NoSuchElementException if the heap is empty.
     */
    @Override
    public T remove() throws NoSuchElementException {
        if (this.size() == 0) {
            throw new NoSuchElementException();
        }
        T root = this.heap[0];       // Store the root value
        this.heap[0] = this.heap[this.nelems - 1];   // Remove the root
        this.heap[this.nelems - 1] = null;           // update the heap
        this.nelems--;                               // Update the number of elements
        this.trickleDown(0);                   // Trickle down to make the heap right
        return root;                                 // Return the root
    }

    /**
     * Adds the given data to the heap.
     *
     * @param item the given data to be added.
     * @throws NullPointerException if data is null.
     */
    @Override
    public void add(T item) throws NullPointerException {
        if (item == null) {
            throw new NullPointerException();
        }
        if (this.heap.length <= nelems) {           // No space to add
            this.resize();                          // Resize the heap array
        }
        this.heap[this.nelems] = item;              // Add the item
        this.bubbleUp(nelems);                      // Bubble up the item to make the heap right
        this.nelems++;                              // Update the number of the elements
    }

    /**
     * Clears all elements in the heap.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        int i = 0;
        while (i < this.size()) {
            this.heap[i] = null;        // Assign every element in the heap to null
            i++;
        }
        this.nelems = 0;                // Update the number of the elements in the heap
    }

    /**
     * Getter method for the root element from the heap.
     *
     * @return the root element from the heap.
     * @throws NoSuchElementException if the heap is empty.
     */
    @Override
    public T element() throws NoSuchElementException {
        if (this.size() == 0) {
            throw new NoSuchElementException();
        }
        return this.heap[0];
    }

    /**
     * Helper method for getting the parent index of a given index in the heap.
     *
     * @param index the given index.
     * @return the parent index of a given index in the heap.
     */
    private int parent(int index) {
        return (index - 1) / this.d;
    }

    /**
     * Helper method for adding elements to the heap.
     *
     * @param index the given index of the element in the heap to be bubbled up.
     */
    private void bubbleUp(int index) {
        if (index != 0) {               // the root doesn't have parent to bubble up
            if (this.heap[index].compareTo(this.heap[this.parent(index)]) >= 0) {
                // Child greater or equal to parent
                if (this.isMaxHeap) {         // Need to swap if this is a max heap
                    T temp = this.heap[this.parent(index)];
                    this.heap[this.parent(index)] = this.heap[index];
                    this.heap[index] = temp;
                }
            } else if (this.heap[index].compareTo(this.heap[this.parent(index)]) <= 0) {
                // Parent greater or equal to child
                if (! this.isMaxHeap) {        // Need to swap if this is a min heap
                    T temp = this.heap[this.parent(index)];
                    this.heap[this.parent(index)] = this.heap[index];
                    this.heap[index] = temp;
                }
            }
            bubbleUp(this.parent(index));  // Continue to bubble up
        }
    }

    /**
     * Helper method for removing elements from the heap.
     *
     * @param index the given index of the element in the heap to be trickled down.
     */
    private void trickleDown(int index) {
        // Assign the starting index for the children of the given index
        int childStart = this.d * index + 1;
        // Only trickle down if there is any child for the given index in the heap
        if (childStart <= this.nelems - 1) {
            // Instantiates the ending index for the children of the given index
            int childEnd;
            // Decide which index should be the ending index for the children of the given index
            if (this.d * index + this.d > this.nelems - 1) {
                // When the spots of the children are not fully occupied
                childEnd = this.nelems - 1;
            } else {
                childEnd = this.d * index + this.d;
            }
            if (this.isMaxHeap) {
                // Find the maximum among the children when this is a max heap
                int maxIndex = childStart;
                for (int i = childStart; i <= childEnd; i++) {
                    if (this.heap[i].compareTo(this.heap[maxIndex]) >= 0) {
                        maxIndex = i;
                    }
                }
                // When Max child is greater than its parent
                if (this.heap[index].compareTo(this.heap[maxIndex]) <= 0) {
                    // Swap the Max child with its parent
                    T temp = this.heap[maxIndex];
                    this.heap[maxIndex] = this.heap[index];
                    this.heap[index] = temp;
                    this.trickleDown(maxIndex);
                }
            } else {
                // Find the minimum among the children when this is a min heap
                int minIndex = childStart;
                for (int i = childStart; i <= childEnd; i++) {
                    if (this.heap[i].compareTo(this.heap[minIndex]) <= 0) {
                        minIndex = i;
                    }
                }
                // When Min child is less than its parent
                if (this.heap[index].compareTo(this.heap[minIndex]) >= 0) {
                    // Swap the Min child with its parent
                    T temp = this.heap[minIndex];
                    this.heap[minIndex] = this.heap[index];
                    this.heap[index] = temp;
                    this.trickleDown(minIndex);
                }
            }
        }
    }

    /**
     * Helper method to resize the heap array for adding elements to the heap
     * when there is no enough space.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        int doubled = this.heap.length * 2; // Double the size
        T[] temp = (T[]) new Comparable[doubled]; // Create a new array with more space
        int i = 0;
        while (i < this.size()) {
            temp[i] = this.heap[i]; // Add all elements from old array to the new one
            i++;
        }
        this.heap = temp; // Assign the new array
    }

}
