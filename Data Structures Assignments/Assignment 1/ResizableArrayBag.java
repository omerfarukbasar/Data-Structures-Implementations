
/**
 * ResizableArrayBag.java - an array-based implementation of our Bag ADT that can be resized
 */
import java.util.Arrays;

public class ResizableArrayBag<T> implements BagInterface<T>
{
    //declare our data properties
    private T[] bag;          //the array holding our entries
    private int numEntries;  //how many entries currently in the Bag
    private final static int DEFAULT_CAPACITY = 25;  //default starts with array size 25
    private final static int MAX_CAPACITY = 1000;    // for a fixed bag, maximum allowed size
    private boolean integrityOK;    //flag value to signal data is not corrupted

    //default constructor - create empty bag with DEFAULT CAPACITY
    public ResizableArrayBag()
    {
        this(DEFAULT_CAPACITY);
    }

    //parameterized constructor - create an empty bag with given capacity, as long as
    // it does not exceed MAX_CAPACITY
    public ResizableArrayBag(int capacity)
    {
        integrityOK = false; 

        checkCapacity(capacity);

        numEntries = 0; //initially bag has 0 entries
        bag = (T[]) new Object[capacity];

        integrityOK = true;  // all good, set flag to true
    }

    //define CORE methods

    //add() - Adds a new entry to this bag
    // @param newEntry - the object to be added to the bag
    // @returns TRUE if addition was successful, or FALSE if it fails
    public boolean add(T newEntry)
    {
        checkIntegrity();

        //if array is full, double the size of the array
        if (isArrayFull())
            doubleCapacity();

        //add new entry into next available spot
        bag[numEntries] = newEntry;
        //increment numEntries
        numEntries++;

        return true;
    }

    //helper method to double size of the array, if possible
    private void doubleCapacity()
    {
        int newCapacity = bag.length * 2;
        //check capacity
        checkCapacity(newCapacity);       

        //use copyOf method to copy into larger array
        bag = Arrays.copyOf(bag, newCapacity);
    }

    //helper method to ensure we are not trying to create an array that is too large
    private void checkCapacity(int capacity)
    {
        if (capacity >= MAX_CAPACITY)
            throw new IllegalStateException("Attempted to create bag whose capacity exceeds max allowed value.");

    }

    //isArrayFull() - helper method for add
    private boolean isArrayFull()
    {
        return (numEntries >= bag.length);
    }

    //checkIntegrity() - helper method to ensure bag is okay to work with
    private void checkIntegrity()
    {
        if (!integrityOK)
            throw new SecurityException("Data is corrupt.");
    }

    //toArray() - retrieve all entries that are in the bag
    // @returns a newly allocated array of all the entries in the bag
    //  NOTE:  if bag is empty, it will return an empty array
    public T[] toArray()
    {
        checkIntegrity();       

        //create a new array of size numEntries
        T[] resultArray = (T[]) new Object[numEntries];
        //Copy entries into the new array
        for (int i = 0; i < numEntries; i++)
        {
            resultArray[i] = bag[i];
        }
        //return the new array
        return resultArray;
    }

    //define ADDITIONAL methods
    //getCurrentSize() - gets the current number of entries in this bag
    // @returns the integer number of entries currently in the bag
    public int getCurrentSize()
    {
        return numEntries;
    }

    //isEmpty() - sees whether the bag is empty
    // @returns TRUE if the bag is empty, FALSE if not
    public boolean isEmpty()
    {
        return (numEntries == 0);
    }

    //**TODO**

    //remove() - removes one unspecified entry from the bag, if possible
    // @returns either the removed entry (if successful), or NULL if not
    public T remove()
    {
        return removeEntry(numEntries - 1);
    }

    //remove(T anEntry) - removes one occurrence of a given entry from this bag, if possible
    // @param anEntry - the entry to be removed
    // @returns TRUE if removal was successful, FALSE otherwise
    public boolean remove(T anEntry)
    {
        //**Pre-condition:  we know the index of anEntry**
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);

        if (result == null)
            return false;
        else
            return true;

    }

    //removeEntry(int i) - helper method for removal
    // @param index - the index of element to be removed
    // @returns the element removed from index
    private T removeEntry(int index)
    {   
        if (index < 0)
            return null;

        //save element in index
        T result = bag[index];
        //copy last element into index spot
        bag[index] = bag[numEntries - 1];
        //delete the last element
        bag[numEntries - 1] = null;
        //decrease numEntries
        numEntries--;

        return result;

    }

    //getIndexOf() - return the index of the first instance of given entry
    // @param anEntry - the entry to look for
    // @returns the index of the entry if found, -1 if not found
    private int getIndexOf(T anEntry)
    {
        boolean found = false;
        int i = 0;
        int result = -1;    // index will be overwritten if found
        while (!found && i < numEntries)
        {
            if (bag[i].equals(anEntry))
            {
                result = i;
                found = true;
            }
            i++;
        }

        return result;
    }

    //clear() - removes all entries from the bag
    public void clear()
    {
        while (!isEmpty())
            remove();
        //as long as bag is NOT empty, remove()
    }

    //contains() - test whether this bag contains a given entry
    // @param anEntry - the entry to find
    // @returns TRUE if the bag contains anEntry, or FALSE otherwise
    public boolean contains(T anEntry)
    {
        //int result = getIndexOf(anEntry);
        //if (result < 0)
        //    return false;
        //else
        //    return true;

        return (getFrequencyOf(anEntry) > 0);
    }

    //getFrequencyOf() - count the number of times a given entry appears in the bag
    // @param anEntry - the entry to count
    // @returns the number of time anEntry appears in the bag
    public int getFrequencyOf(T anEntry)
    {
        int i = 0;
        int result = 0;    // running total of times entry was found
        while (i < numEntries)
        {
            if (bag[i].equals(anEntry))
            {
                result++;       // increment the counter
            }
            i++;
        }

        return result;
    }

}
