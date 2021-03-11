
/**
 * ArrayStack.java - array-based implementation of Stack ADT
 */

import java.util.EmptyStackException;
import java.util.Arrays;

public class ArrayStack<T> implements StackInterface<T>
{
    private T[] stack;    // Array of stack entries
    private int topIndex; // Index of top entry
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    public ArrayStack()
    {
        this(DEFAULT_CAPACITY);
    }

    public ArrayStack(int initialCapacity)
    {
        integrityOK = false;
        checkCapacity(initialCapacity);

        T[] tempStack = (T[])new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
        integrityOK = true;
    }

    //helper method to ensure we are not trying to create an array that is too large
    private void checkCapacity(int capacity)
    {
        if (capacity >= MAX_CAPACITY)
            throw new IllegalStateException("Attempted to create bag whose capacity exceeds max allowed value.");

    }

    //checkIntegrity() - helper method to ensure bag is okay to work with
    private void checkIntegrity()
    {
        if (!integrityOK)
            throw new SecurityException("Data is corrupt.");
    }

    //push() - adds a new entry to the top of this stack
    //  @param newEntry - the object to be added to the stack
    public void push(T newEntry)
    {
        checkIntegrity();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
    }

    //helper method to double size of array if it is full
    private void ensureCapacity()
    {
        if (topIndex >= stack.length - 1) // If array is full, double its size
        {
            int newLength = 2 * stack.length;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack, newLength);
        }
    }

    //pop() - removes and returns the top entry from this stack
    //  @return - the object at the top of the stack
    //  @throws - EmptyStackException if the stack is empty before the operation
    public T pop()
    {
        checkIntegrity();
        if (isEmpty())
            throw new EmptyStackException();
        else
        {
            T top = stack[topIndex];    //save the top item
            stack[topIndex] = null;     //delete the spot in array
            topIndex--;                 //topIndex is reduced by 1
            return top;

        }
    }

    //peek() - retrieves the top entry from this stack
    //  @return - the object at the top of the stack
    //  @throws - EmptyStackException if the stack is empty before the operation
    public T peek()
    {
        checkIntegrity();
        if (isEmpty())
            throw new EmptyStackException();
        else
            return stack[topIndex];
    }

    //isEmpty() - detects whether this stack is empty
    //  @return - TRUE if this stack is empty, FALSE otherwise
    public boolean isEmpty()
    {
        return (topIndex<0);
    }

    //clear() - removes all entries from this stack
    public void clear()
    {
        //delete each slot in array
        for (int i = 0; i <=topIndex; i++)
            stack[i] = null;

        topIndex = -1;
    }

}
