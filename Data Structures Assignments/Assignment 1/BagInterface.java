
/**
 * BagInterface.java - ADT Bag Type
 * Describes the operations of a bag of objects
 */
public interface BagInterface<T>
{
    //getCurrentSize() - gets the current number of entries in this bag
    // @returns the integer number of entries currently in the bag
    public int getCurrentSize();
    
    //isEmpty() - sees whether the bag is empty
    // @returns TRUE if the bag is empty, FALSE if not
    public boolean isEmpty();
    
    //add() - Adds a new entry to this bag
    // @param newEntry - the object to be added to the bag
    // @returns TRUE if addition was successful, or FALSE if it fails
    public boolean add(T newEntry);
    
    //remove() - removes one unspecified entry from the bag, if possible
    // @returns either the removed entry (if successful), or NULL if not
    public T remove();
    
    //remove(T anEntry) - removes one occurrence of a given entry from this bag, if possible
    // @param anEntry - the entry to be removed
    // @returns TRUE if removal was successful, FALSE otherwise
    public boolean remove(T anEntry);
    
    //clear() - removes all entries from the bag
    public void clear();
    
    //contains() - test whether this bag contains a given entry
    // @param anEntry - the entry to find
    // @returns TRUE if the bag contains anEntry, or FALSE otherwise
    public boolean contains(T anEntry);
    
    //getFrequencyOf() - count the number of times a given entry appears in the bag
    // @param anEntry - the entry to count
    // @returns the number of time anEntry appears in the bag
    public int getFrequencyOf(T anEntry);
    
    //toArray() - retrieve all entries that are in the bag
    // @returns a newly allocated array of all the entries in the bag
    //  NOTE:  if bag is empty, it will return an empty array
    public T[] toArray();
    
}
