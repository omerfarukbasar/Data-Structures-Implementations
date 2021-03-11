
/**
 * LinkedStack.java - Linked List based implementation of Stack ADT
 *
 */
import java.util.EmptyStackException;

public class LinkedStack<T> implements StackInterface<T>
{
    private Node topNode;   //reference to the first Node in the chain

    public LinkedStack()
    {
        topNode = null; //initially stack is empty
    }

    //push() - adds a new entry to the top of this stack
    //  @param newEntry - the object to be added to the stack
    public void push(T newEntry)
    {
        Node newNode = new Node(newEntry);

        newNode.next = topNode;
        topNode = newNode;
    }

    //pop() - removes and returns the top entry from this stack
    //  @return - the object at the top of the stack
    //  @throws - EmptyStackException if the stack is empty before the operation
    public T pop()
    {
        T top = peek();     //might throw EmptyStackException

        //Assertion:  topNode != null
        topNode = topNode.next;

        return top;
    }

    //peek() - retrieves the top entry from this stack
    //  @return - the object at the top of the stack
    //  @throws - EmptyStackException if the stack is empty before the operation
    public T peek()
    {
        if (isEmpty())
            throw new EmptyStackException();
        else
            return topNode.data;
    }

    //isEmpty() - detects whether this stack is empty
    //  @return - TRUE if this stack is empty, FALSE otherwise
    public boolean isEmpty()
    {
        return (topNode==null);
    }

    //clear() - removes all entries from this stack
    public void clear()
    {
        topNode = null;
    }

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
}
