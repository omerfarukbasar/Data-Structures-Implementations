
/**
 * LinkedBag.java - a linked-list based implementation of our Bag ADT
 */
public class LinkedBag<T> implements BagInterface<T>
{
    private Node firstNode;
    private int numEntries;
    
    private class Node
    {
        private T data;
        private Node next;
        
        private Node(T dataPortion)
        {
            data = dataPortion;
            next = null;
        }
    }
    
    //initially create a Bag that is empty
    public LinkedBag()
    {
        firstNode = null;
        numEntries = 0;
    }
    
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
    
    //add() - Adds a new entry to this bag
    // @param newEntry - the object to be added to the bag
    // @returns TRUE if addition was successful, or FALSE if it fails
    public boolean add(T newEntry)
    {
        //create a new Node
        Node newNode = new Node(newEntry);
        
        //set newNode's next pointer to firstNode
        newNode.next = firstNode;
        
        //reassign firstNode to be new Node
        firstNode = newNode;
        
        //increment numEntries
        numEntries++;
        
        return true;
    }
    
    //remove() - removes one unspecified entry from the bag, if possible
    // @returns either the removed entry (if successful), or NULL if not
    public T remove()
    {
        T result = null;
        
        if (!isEmpty())
        {
            //remove the first entry in the list
            result = firstNode.data;
            firstNode = firstNode.next;
            numEntries--;
        }
        
        return result;
        
    }
    
    //remove(T anEntry) - removes one occurrence of a given entry from this bag, if possible
    // @param anEntry - the entry to be removed
    // @returns TRUE if removal was successful, FALSE otherwise
    public boolean remove(T anEntry)
    {
        //find Node p where anEntry is located
        Node p = getNodeContaining(anEntry);
        
        if (p == null)
            return false;
            
        //copy firstNode's data into Node p
        p.data = firstNode.data;
        
        //remove the first Node
        remove();
        
        return true;
    }
    
    //getNodeContaining(T anEntry) - returns a Node object containing given entry
    // @param anEntyr - the entry to be found
    // @returns a Node reference where anEntry is found or NULL if NOT found
    private Node getNodeContaining(T anEntry)
    {
        Node cur = firstNode;
        
        //traverse list until we find anEntry
        while (cur != null)
        {
            if (cur.data.equals(anEntry))
                return cur;     //we found it, so return the Node
                
            cur = cur.next; //hop to next Node
        }
        
        return null;    //anEntry not found
        
    }
    
    //clear() - removes all entries from the bag
    public void clear()
    {
        firstNode = null;
        numEntries = 0;
    }
    
    //contains() - test whether this bag contains a given entry
    // @param anEntry - the entry to find
    // @returns TRUE if the bag contains anEntry, or FALSE otherwise
    public boolean contains(T anEntry)
    {
        Node p = getNodeContaining(anEntry);
        
        if (p == null)
            return false;
        else
            return true;
    }
    
    //getFrequencyOf() - count the number of times a given entry appears in the bag
    // @param anEntry - the entry to count
    // @returns the number of time anEntry appears in the bag
    public int getFrequencyOf(T anEntry)
    {
        Node cur = firstNode;
        int count = 0;
        
        //traverse list until we find anEntry
        while (cur != null)
        {
            if (cur.data.equals(anEntry))
                count++;     //we found it, so increment the count
                
            cur = cur.next; //hop to next Node
        }
        
        return count;    
    }
    
    //toArray() - retrieve all entries that are in the bag
    // @returns a newly allocated array of all the entries in the bag
    //  NOTE:  if bag is empty, it will return an empty array
    public T[] toArray()
    {
        //allocate result array
        T[] resultArray = (T[]) new Object[numEntries];
        
        Node cur = firstNode;
        int index = 0;
        
        //traverse the list, copying the data in each node into the next spot in array
        while(cur != null)
        {
            resultArray[index] = cur.data;  //copy the Node's data into next spot in array
            index++;
            cur = cur.next;
        }
        
        return resultArray;
    }
    
}
