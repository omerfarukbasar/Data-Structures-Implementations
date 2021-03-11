/**
 * Author: Omer Basar
 * Filename: Driver.java - basic driver program to test core methods of ShoppingCart
 * Version: 10/6/20
 * Assignment: 1
 */
/**
 * Driver.java - basic driver program to test core method of
 *   ArrayBag implementation
 *
 */
public class Driver
{
    public static void main(String[] args)
    {
        //Create a shopping cart, budget, and items
        ShoppingCart Crate = new ShoppingCart();
        int budget = 2000;
        Item Pear = new Item("pear",399);
        Item Apple = new Item("apple", 299);
        Item Cheese = new Item("cheese", 599);

        //Add items to the cart
        Crate.add(Cheese);
        Crate.add(Cheese);
        Crate.add(Cheese);
        Crate.add(Apple);
        Crate.add(Pear);
        Crate.add(Pear);
        Crate.displayCart();

        //Remove random item from cart
        Crate.remove();
        Crate.displayCart();

        //Remove specific item from cart
        Crate.remove(Pear);
        Crate.displayCart();

        //Checkout function to display total
        Crate.checkout();

        //Check budget function to remove stuff when above budget.
        Crate.checkBudget(budget);

        //Checking out after cart adjustments;
        Crate.checkout();
        
    }
    

}
