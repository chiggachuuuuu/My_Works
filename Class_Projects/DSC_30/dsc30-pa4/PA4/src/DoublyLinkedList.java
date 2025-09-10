/*
 * NAME: Lin Tian
 * PID: A16844916
 */

import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Implementing DoublyLinkedList
 * @author Lin Tian
 * @since 10/29/2002
 */
public class DoublyLinkedList<T> extends AbstractList<T> {

    /* DLL instance variables */
    private int elems;
    private Node head;
    private Node tail;
    private int size;

    /**
     * Node for chaining together to create a linked list
     */
    protected class Node {

        /* Node instance variables */
        T data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         */
        private Node(T element) {
            this(element, null, null);
        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param nextNode successor Node, can be null
         * @param prevNode predecessor Node, can be null
         */
        private Node(T element, Node nextNode, Node prevNode) {
            this.data = element;
            this.next = nextNode;
            this.prev = prevNode;
        }

        /**
         * Set the element
         *
         * @param element new element
         */
        public void setElement(T element) {
            this.data = element;
        }

        /**
         * Accessor to get the Nodes Element
         */
        public T getElement() {
            return this.data;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            this.next = n;
        }

        /**
         * Get the next node in the list
         *
         * @return the successor node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            this.prev = p;
        }


        /**
         * Accessor to get the prev Node in the list
         *
         * @return predecessor node
         */
        public Node getPrev() {
            return this.prev;
        }

        /**
         * Remove this node from the list.
         * Update previous and next nodes
         */
        public void remove() {
            this.prev.setNext(this.next);
            this.next.setPrev(this.prev);
        }
    }

    /**
     * Creates a new, empty doubly-linked list.
     */
    public DoublyLinkedList() {
        this.head = new Node(null);
        this.tail = new Node(null);
        this.head.setNext(this.tail);
        this.tail.setPrev(this.head);
        this.size = 0;

    }

    /**
     * Add an element to the end of the list
     *
     * @param element data to be added
     * @return whether or not the element was added
     * @throws NullPointerException if data received is null
     */
    @Override
    public boolean add(T element) throws NullPointerException {
        if (element == null) {
            throw new NullPointerException();
        }
        Node newNode = new Node(element, this.tail, this.tail.getPrev());
        this.tail.getPrev().setNext(newNode);
        this.tail.setPrev(newNode);
        this.size++;
        return true;
    }


    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room. Does not accept null values.
     *
     *  @param index the position index of the node in the list to be accessed
     *  @param element data to be added
     *  @throws IndexOutOfBoundsException if index received is outside the range [0, size]
     *  @throws NullPointerException if data received is null
     */
    @Override
    public void add(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        if (index < 0 || index > this.size) {     // index needs to be in range [0,size]
            throw new IndexOutOfBoundsException();
        }
        if (element == null) {               // Passes in element can't be null
            throw new NullPointerException();
        }
        Node curNode;
        Node sucNode;
        if (index == this.size) {           // In this case, add at the tail
            curNode = this.tail.getPrev();
            sucNode = this.tail;
        } else {
            curNode = this.getNth(index);
            sucNode = curNode.getNext();
        }
        Node newNode = new Node(element, sucNode, curNode);
        curNode.setNext(newNode);
        sucNode.setPrev(newNode);
        this.size++;
    }

    /**
     * Clear the linked list
     */
    @Override
    public void clear() {
        this.head.setNext(this.tail);
        this.tail.setPrev(this.head);
        this.size = 0;
    }

    /**
     * Determine if the list contains the data element anywhere in the list.
     *
     * @param element data to be searched for in the list
     * @return whether or not the element existed
     */
    @Override
    public boolean contains(Object element) {
        T data = (T) element;
        Node curNode = this.head;
        for (int i = 0; i < size; i++) {       // search for the element one by one
            curNode = curNode.getNext();
            if (curNode.getElement() == data) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the element stored with a given index on the list.
     *
     * @param index the position index of the node in the list to be accessed
     * @return T type element stored in the node at the given index
     * @throws IndexOutOfBoundsException if index received is outside the range [0, size-1]
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size - 1) {  // Index needs to be inside range
            throw new IndexOutOfBoundsException();
        }
        Node getNode = this.getNth(index);
        return getNode.getElement();
    }

    /**
     * Helper method to get the Nth node in our list
     *
     * @param index the position index of the node in the list to be accessed
     * @return Node at the given index
     */
    private Node getNth(int index) {
        Node getNode = this.head;
        for (int i = 0; i <= index; i++) {   // Moving to the next node until index is met
            getNode = getNode.getNext();
        }
        return getNode;
    }

    /**
     * Determine if the list empty
     *
     * @return whether or not the list is empty
     */
    @Override
    public boolean isEmpty() {
        while (this.size != 0) {
            return false;
        }
        return true;
    }

    /**
     * Remove the element from position index in the list
     *
     * @param index the position index of the node in the list to be accessed
     * @return the data stored in the removed node
     * @throws IndexOutOfBoundsException if index received is outside the range [0, size-1]
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size - 1) {    // index needs to be inside range
            throw new IndexOutOfBoundsException();
        }
        Node curNode = this.getNth(index);
        T curData = curNode.getElement();
        curNode.getPrev().setNext(curNode.getNext());
        curNode.getNext().setPrev(curNode.getPrev());
        curNode.setPrev(null);
        curNode.setNext(null);
        size--;
        return curData;
    }

    /**
     * Set the value of an element at a certain index in the list.
     *
     * @param index the position index of the node in the list to be accessed
     * @param element data to be updated in the given node
     * @return T type data originally stored in given node
     * @throws IndexOutOfBoundsException if index received is outside the range [0, size]
     * @throws NullPointerException if data received is null
     */
    @Override
    public T set(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        if (index < 0 || index > this.size - 1) {  // Index need to be inside range
            throw new IndexOutOfBoundsException();
        }
        if (element == null) {
            throw new NullPointerException();
        }
        Node curNode = this.getNth(index);
        T curData = curNode.getElement();
        curNode.setElement(element);
        return curData;
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     *
     * @return size of the list
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * String representation of this list in the form of:
     * "[(head) -> elem1 -> elem2 -> ... -> elemN -> (tail)]"
     *
     * @return a String representation of the list
     */
    @Override
    public String toString() {
        String output = "[(head) -> ";
        Node curNode = this.head;
        for (int i = 0; i < size; i++) { // Gather the elements one by one and turn them to string
            curNode = curNode.getNext();
            String curData = String.valueOf(curNode.getElement());
            output += curData + " -> ";
        }
        output += "(tail)]";
        return output;
    }

    /* ==================== EXTRA CREDIT ==================== */

    /**
     * Remove nodes whose index is a multiple of base
     *
     * @param base a given base to decide on which indexes to remove elements from the list
     * @throws IllegalArgumentException when passed in base is less than 1
     */
    public void removeMultipleOf(int base) throws IllegalArgumentException {
        if (base < 1) {
            throw new IllegalArgumentException();
        }
        int temp = 0;
        int limit = this.size;
        while (temp < limit) {
            this.remove(temp);
            temp += base - 1;
            limit--;
        }
    }

    /**
     * Swap the nodes between index [0, splitIndex] of two lists
     *
     * @param other the other doubly-linked list that needs to be swapped
     * @param splitIndex the index where the swapping ends
     */
    public void swapSegment(DoublyLinkedList<T> other, int splitIndex) {
        ArrayList<T> tempThis = new ArrayList<T>();
        ArrayList<T> tempOther = new ArrayList<T>();
        for (int i = 0; i <= splitIndex; i++) {
            tempThis.add(this.get(i));
            tempOther.add(other.get(i));
        }
        for (int j = 0; j <= splitIndex; j++) {
            this.set(j,tempOther.get(j));
            other.set(j,tempThis.get(j));
        }

    }

}
