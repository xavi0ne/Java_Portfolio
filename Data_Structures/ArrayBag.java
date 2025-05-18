/* 
 * ArrayBag.java
 * 
 * Computer Science E-22
 * 
 * Modified by: Xavier Torres, xtorres1@live.com
 */

import java.util.*;

/*
 * An simple implementation of a bag data structure that uses 
 * an array to store the items.
 */
public class ArrayBag {
    /* 
     * The array used to store the items in the bag.
     */
    private Object[] items;
    
    /* 
     * The number of items in the bag.
     */
    private int numItems;
    
    public static final int DEFAULT_MAX_SIZE = 50;
    
    /*
     * Constructor with no parameters - creates a new, empty ArrayBag with 
     * the default maximum size.
     */
    public ArrayBag() {
        this.items = new Object[DEFAULT_MAX_SIZE];
        this.numItems = 0;
    }
    
    /* 
     * A constructor that creates a new, empty ArrayBag with the specified
     * maximum size.
     */
    public ArrayBag(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize must be > 0");
        }
        this.items = new Object[maxSize];
        this.numItems = 0;
    }

    /*
     * capacity() - method returns the maximum items arrayBag is capable
     * of holding. 
     */
    public int capacity() {

        return this.items.length;
    }

    /*
     * isFull() - method returns true to confirm if ArrayBag is full
     * upon being called, and false if not. 
     */
    public boolean isFull() {

        if (this.numItems == this.capacity()) {

            return true;
        }
        return false;
    }
/*
 * increaseCapacity() - method creates new array with fixed length plus amount 
 * and performs looping to iterate through current array and copy the 
 * elements to the new array. It then stores the reference in the called
 * object.  
 */
    public void increaseCapacity(int amount) {

        if (amount == 0) {      //special case where methods returns without
                                //making changes when param is 0.
            return;
        }

        if (amount < 0) {       //special case for error handling when param is negative.
            
            throw new IllegalArgumentException("amount cannot be negative value.");
        }
        
        Object[] b = new Object[this.items.length + amount];

        for (int i = 0; i < this.items.length; i++) {

            b[i] = this.items[i];
        }
        this.items = b;

    }

    /*
     * removeItems() - method performs checks to ensure params
     * are not null, returns true if param is empty, and performs
     * item removal using while within nested loop to remove items
     * from the called ArrayBag via the remove() method. 
     */
    public boolean removeItems(ArrayBag other) {

        if (other == null) {

            throw new IllegalArgumentException("other cannot have null values.");
        }
        
        boolean removedItem = false;

        for (int i = 0; i < other.numItems; i++) {

            while (this.remove(other.items[i])) {
                
                removedItem = true; 
            }
        }
        return removedItem;
    }

    /*
     * unionWith(Bag other) - method creates and returns unitedBag1 of 
     * data type ArrayBag with the max size of both the sum of original arraybag and
     * new bag created. For loops are used to ensure only values not contained
     * currently in each bag are added to either bag during its assigned union.
     * 
     */
    public ArrayBag unionWith(ArrayBag other) {
        
        if (other == null) {

            throw new IllegalArgumentException("other cannot have null values.");
        }
        
        int bagSum = this.numItems + other.numItems;

        ArrayBag unitedBag1 = new ArrayBag(bagSum);

        for (int i = 0; i < this.numItems; i++) {       //only adds non-duplicate values from original arraybag.

            if (!unitedBag1.contains(this.items[i])) {

                unitedBag1.items[unitedBag1.numItems++] = this.items[i];
            }
        }

        for (int i = 0; i < other.numItems; i++) {      //only adds non-duplicate values from the new arraybag. 

            if (!unitedBag1.contains(other.items[i])) {

                unitedBag1.items[unitedBag1.numItems++] = other.items[i];
            }
        }
        return unitedBag1;
    }

    /*
     * numItems - accessor method that returns the number of items 
     * in this ArrayBag.
     */
    public int numItems() {
        return this.numItems;
    }
    
    /* 
     * add - adds the specified item to this ArrayBag. Returns true if there 
     * is room to add it, and false otherwise.
     * Throws an IllegalArgumentException if the item is null.
     */
    public boolean add(Object item) {
        if (item == null) {
            throw new IllegalArgumentException("item must be non-null");
        } else if (this.numItems == this.items.length) {
            return false;    // no more room!
        } else {
            this.items[this.numItems] = item;
            this.numItems++;
            return true;
        }
    }
    
    /* 
     * remove - removes one occurrence of the specified item (if any)
     * from this ArrayBag.  Returns true on success and false if the
     * specified item (i.e., an object equal to item) is not in this ArrayBag.
     */
    public boolean remove(Object item) {
        for (int i = 0; i < this.numItems; i++) {
            if (this.items[i].equals(item)) {
                // Shift the remaining items left by one.
                for (int j = i; j < this.numItems - 1; j++) {
                    this.items[j] = this.items[j + 1];
                }
                this.items[this.numItems - 1] = null;
                
                this.numItems--;
                return true;
            }
        }
        
        return false;  // item not found
    }
    
    /*
     * contains - returns true if the specified item is in the Bag, and
     * false otherwise.
     */
    public boolean contains(Object item) {
        for (int i = 0; i < this.numItems; i++) {
            if (this.items[i].equals(item)) {
                return true;
            }
        }
        
        return false;
    }
    
    /*
     * containsAll - does this ArrayBag contain all of the items in
     * otherBag?  Returns false if otherBag is null or empty. 
     */
    public boolean containsAll(ArrayBag otherBag) {
        if (otherBag == null || otherBag.numItems == 0) {
            return false;
        }
        
        for (int i = 0; i < otherBag.numItems; i++) {
            if (! this.contains(otherBag.items[i])) {
                return false;
            }
        }
        
        return true;
    }
    
    /*
     * grab - returns a reference to a randomly chosen item in this ArrayBag.
     */
    public Object grab() {
        if (this.numItems == 0) {
            throw new IllegalStateException("the bag is empty");
        }
        
        int whichOne = (int)(Math.random() * this.numItems);
        return this.items[whichOne];
    }
    
    /*
     * toArray - return an array containing the current contents of the bag
     */
    public Object[] toArray() {
        Object[] copy = new Object[this.numItems];
        
        for (int i = 0; i < this.numItems; i++) {
            copy[i] = this.items[i];
        }
        
        return copy;
    }
    
    /*
     * toString - converts this ArrayBag into a string that can be printed.
     * Overrides the version of this method inherited from the Object class.
     */
    public String toString() {
        String str = "{";
        
        for (int i = 0; i < this.numItems; i++) {
            str = str + this.items[i];
            if (i != this.numItems - 1) {
                str += ", ";
            }
        }
        
        str = str + "}";
        return str;
    }
    
    /* Test the ArrayBag implementation. */
    public static void main(String[] args) {
        // Create a Scanner object for user input.
        Scanner scan = new Scanner(System.in);
        
        // Create an ArrayBag named bag1.
        System.out.print("size of bag 1: ");
        int size = scan.nextInt();
        ArrayBag bag1 = new ArrayBag(size);
        scan.nextLine();    // consume the rest of the line
        
        // Read in strings, add them to bag1, and print out bag1.
        String itemStr;        
        for (int i = 0; i < size; i++) {
            System.out.print("item " + i + ": ");
            itemStr = scan.nextLine();
            bag1.add(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();
        
        // Select a random item and print it.
        Object item = bag1.grab();
        System.out.println("grabbed " + item);
        System.out.println();
        
        // Iterate over the objects in bag1, printing them one per
        // line.
        Object[] items = bag1.toArray();
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }
        System.out.println();
        
        // Get an item to remove from bag1, remove it, and reprint the bag.
        System.out.print("item to remove: ");
        itemStr = scan.nextLine();
        if (bag1.contains(itemStr)) {
            bag1.remove(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();

        scan.close();
    }
}
