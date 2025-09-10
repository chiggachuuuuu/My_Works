/*
 * Name: Lin Tian
 * PID:  A16844916
 */

import java.util.*;

/**
 * Binary search tree implementation.
 * 
 * @author Lin Tian
 * @since  11/11/2023
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node

    /* * * * * BST Node Inner Class * * * * */

    /**
     * Binary search tree node implementation.
     *
     * @author Lin Tian
     * @since  11/11/2023
     */
    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            this.left = left;
            this.right = right;
            this.key = key;
            if (dataList == null) {
                this.dataList = new LinkedList<T>();
            } else {
                this.dataList = dataList;
            }
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            this(left, right, null, key);
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            return this.key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            return this.left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            return this.right;
        }

        /**
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {
            return this.dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setLeft(BSTNode newleft) {
            this.left = newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setRight(BSTNode newright) {
            this.right = newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {
            this.dataList = newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {
            this.dataList.add(data);
        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {
            return this.dataList.remove(data);
        }
    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        this.root = null;
        this.nelems = 0;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        return root;
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        return nelems;
    }

    /**
     * Insert a key into BST
     * 
     * @param key To be inserted
     * @return true if insertion is successful and false otherwise
     * @throws NullPointerException if key is null
     */
    public boolean insert(T key) throws NullPointerException{
        if (key == null) {
            throw new NullPointerException();
        }
        if (this.getRoot() == null) {
            this.root = new BSTNode(null, null, key);
        } else {
            BSTNode newNode = new BSTNode(null, null, key);
            BSTNode insertNode = locateHelper(this.getRoot(), key);
            if (key.compareTo(insertNode.getKey()) < 0) {
                insertNode.setLeft(newNode);
            } else if (key.compareTo(insertNode.getKey()) > 0){
                insertNode.setRight(newNode);
            } else {
                return false;
            }
        }
        this.nelems ++;
        return true;
    }

    /**
     * Find the position to insert a key into BST
     *
     * @param currNode Node to be visited and compared (start with the root)
     * @param key To be inserted
     * @return a BSTNode as the desirable position for the insertion
     */
    private BSTNode locateHelper(BSTNode currNode, T key) {
        T thisKey = currNode.getKey();
        if (thisKey.compareTo(key) < 0) {
            if (currNode.getRight() == null) {
                return currNode;
            } else {
                return locateHelper(currNode.getRight(), key);
            }
        } else if (thisKey.compareTo(key) > 0){
            if (currNode.getLeft() == null) {
                return currNode;
            } else {
                return locateHelper(currNode.getLeft(), key);
            }
        }
        return currNode;
    }

    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) throws NullPointerException {
        if (key == null) {
            throw new NullPointerException();
        }
        if (this.getRoot() == null) {
            return false;
        }
        BSTNode currNode = locateHelper(this.getRoot(), key);
        while (currNode.getKey().compareTo(key) == 0) {
            return true;
        }
        return false;
    }

    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If either key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) throws NullPointerException, IllegalArgumentException {
        if (key == null || data == null) {
            throw new NullPointerException();
        }
        if (!findKey(key)) {
            throw new IllegalArgumentException();
        }
        BSTNode currNode = locateHelper(this.getRoot(), key);
        currNode.addNewInfo(data);
    }

    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) throws NullPointerException, IllegalArgumentException{
        if (key == null) {
            throw new NullPointerException();
        }
        if (!findKey(key)) {
            throw new IllegalArgumentException();
        }
        BSTNode currNode = locateHelper(this.getRoot(), key);
        return currNode.getDataList();
    }

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        return findHeightHelper(this.getRoot());
    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {
        if (root == null) {
            return -1;
        }
        BSTNode leftNode = root.getLeft();
        int leftHeight = findHeightHelper(root.getLeft());
        int rightHeight = findHeightHelper(root.getRight());
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /* * * * * BST Iterator * * * * */


    /**
     * Binary search tree iterator implementation.
     *
     * @author Lin Tian
     * @since  11/11/2023
     */
    public class BSTree_Iterator implements Iterator<T> {
        private Stack<BSTNode> nodeStack;

        /**
         * A constructor that initializes the BSTree_Iterator instance variables.
         */
        public BSTree_Iterator() {
            this.nodeStack = new Stack();
            BSTNode currNode = root;
            while (currNode != null) {
                this.nodeStack.push(currNode);
                currNode = currNode.getLeft();
            }
        }

        /**
         * Return whether the iterator has a next element.
         *
         * @return True if the iterator has a next element, false otherwise.
         */
        public boolean hasNext() {
            return ! this.nodeStack.empty();
        }

        /**
         * Return the next element of the Binary Search Tree Iterator.
         *
         * @return The next element of the Binary Search Tree Iterator.
         * @throws NoSuchElementException If the Iterator doesn't have a next element.
         */
        public T next() throws NoSuchElementException {
            if (! this.hasNext()) {
                throw new NoSuchElementException();
            }
            BSTNode nextNode = this.nodeStack.pop();
            BSTNode nextRight = nextNode.getRight();
            if (nextRight != null) {
                this.nodeStack.push(nextRight);
                BSTNode nextLeft = nextRight.getLeft();
                while (nextLeft != null) {
                    this.nodeStack.push(nextLeft);
                    nextLeft = nextLeft.getLeft();
                }
            }
            return nextNode.getKey();
        }
    }

    /**
     * Method that create a new iterator for the Binary Search Tree
     *
     * @return An Iterator instance
     */
    public Iterator<T> iterator() {
        return new BSTree_Iterator();
    }

    /* * * * * Extra Credit Methods * * * * */

    /**
     * Method that returns the intersection of two Binary Search Trees
     *
     * @param iter1 One tree
     * @param iter2 The other tree
     * @return An ArrayList containing the intersection data of the two trees
     */
    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        ArrayList<T> list1 = new ArrayList<T>();
        ArrayList<T> list2 = new ArrayList<T>();
        while (iter1.hasNext()) {
            list1.add(iter1.next());
        }
        while (iter2.hasNext()) {
            list2.add(iter2.next());
        }
        list1.retainAll(list2);
        return list1;
    }

    /**
     * Method that finds the largest key at a given level
     *
     * @param level Given level of the tree
     * @return The largest key at the given level
     */
    public T levelMax(int level) {
        if (this.getRoot() == null) {
            return null;
        }
        if (this.findHeight() < level) {
            return null;
        }
        return null;
    }
}
