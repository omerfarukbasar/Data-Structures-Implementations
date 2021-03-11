/**
 * Author: Omer Basar
 * Filename: ShoppingCart.java - utilizes methods from BagInterface to simulate a shopping cart
 * Version: 10/6/20
 * Assignment: 1
 */
public class ShoppingCart
{
    //Initialize attributes
    private BagInterface<Item> cartBin;

    /**
     * Constructor for the ShoppingCart class
     */
    public ShoppingCart()
    {
        cartBin = new LinkedBag<Item>();
    }

    /**
     * add - adds the specified item to the cart
     *
     * @param T - an Item type
     *
     * @return boolean based upon success or failure
     *
     * Precondition: a shopping cart and the item exists
     */
    public boolean add(Item T)
    {
        return cartBin.add(T);
    }

    /**
     * remove - removes a specified item from the cart
     *
     * @param T - an Item type
     *
     * @return boolean based upon success or failure
     *
     * Precondition: a shopping cart and the specified item exists
     */
    public boolean remove(Item T)
    {
        return cartBin.remove(T);
    }

    /**
     * remove - removes an unspecified item from the cart
     *
     * @return the unspecified item that was removed
     *
     * Precondition: a shopping cart exists and is not empty
     */
    public Item remove()
    {
        return (Item) cartBin.remove();
    }


    /**
     * bagToItem - Converts the bag implementation into an Item array by
     * making a new Item array and copying everything over
     *
     * @return cart - the given array transformed into an Item based array
     *
     * Precondition: a shopping cart exists
     *
     * Additional notes - I received help from Christopher Benson on making this function
     * as my code would not work otherwise
     */
    public Item[] bagToItem()
    {
        //initialize new Object array - contains data
        Object[] bagArray = cartBin.toArray();
        //initialize new Item array - empty
        Item cart[] = new Item[bagArray.length];

        //loop for copying each entry into the Item array from the Object one.
        for(int i = 0; i < bagArray.length; i++)
        {
            cart[i] = (Item) bagArray[i];
        }

        return cart;

    }

    /**
     * checkBudget - checks for the total amount and removes some items if
     * the budget is being exceeded so that the purchase can be afforded
     *
     * @param budget - an integer containing the money we are allowed to spend
     *
     * @return remainder - an integer with the amount of money we are allowed to
     * spend after removing some budget exceeding items
     *
     * Precondition: a shopping cart exists
     */
    public int checkBudget(int budget)
    {
        //loop that checks cart total and removes items until under budget threshold
        while (checkout() > budget)
        {
            System.out.println("Removing item due to exceeding budget: " + remove());
        }

        //calculate the remaining budget after expenses
        int remainder = budget - checkout();

        System.out.println("Your budget remainder after item removal(s) is: " + remainder + "₵");

        return remainder;
    }

    /**
     * checkout - removes and scans each item from the carts and calculates the
     * total amount due after each scanned item
     *
     * @return totalPrice - an integer containing the balance due after the checkout
     *
     * Precondition: a shopping cart exists
     */
    public int checkout()
    {
        //initialize local variables
        int totalPrice = 0;

        //converts the shopping cart's bag into an Item array
        Item[] temp = bagToItem();

        //Retrieves the price of each item and adds it to the total balance
        for (int i = 0; i < temp.length; i++)
        {
            System.out.println("Item to be scanned: " + temp[i].toString());
            totalPrice += temp[i].getPrice();

        }

        System.out.println("Your total is: " + totalPrice + "₵" );

        return totalPrice;

    }

    /**
     * displayCart - prints all items in the shopping cart
     *
     * Precondition: a shopping cart exists
     */
    public void displayCart()
    {
        System.out.println("Display contents of Shopping cart...");

        //use toArray() to get the entries in theBag
        Object[] data = cartBin.toArray();

        //use a loop to print each entry from the array
        for (int i = 0; i < data.length; i++)
            System.out.println(data[i]);
    }

}
