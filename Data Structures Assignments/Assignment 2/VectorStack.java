
/**
 * VectorStack.java - Vector-based implementation of Stack ADT
 */
import java.util.Vector;
import java.util.EmptyStackException;

public class VectorStack<T> implements StackInterface<T>
{
    private Vector<T> stack;
    private boolean integrityOK;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    public VectorStack()
    {
        this(DEFAULT_CAPACITY);
    }

    public VectorStack(int initialCapacity)
    {
        integrityOK = false;
        checkCapacity(initialCapacity);
        stack = new Vector<T>(initialCapacity);
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
        stack.add(newEntry);
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
            return stack.remove(stack.size() - 1);    //remove entry in last spot of Vector and return it

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
            return stack.lastElement();
    }

    //isEmpty() - detects whether this stack is empty
    //  @return - TRUE if this stack is empty, FALSE otherwise
    public boolean isEmpty()
    {
        checkIntegrity();
        return stack.isEmpty();
    }

    //clear() - removes all entries from this stack
    public void clear()
    {
        checkIntegrity();
        stack.clear();
    }
}
