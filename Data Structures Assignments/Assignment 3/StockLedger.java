/**
 * Author: Omer Basar
 * Filename: StockLedger.java - a blueprint class for StockLedger objects that
 * utilizes a menu to perform various tasks involving stock lots such as buying,
 * selling, viewing financial reports, and quitting.
 * Version: 11/12/20
 * Assignment: 3
 */

//Import utilities
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StockLedger
{
    //Declare private attributes of the class
    private DequeInterface ledger;
    private int totalStocks;
    private double capitalGain;
    private boolean ContinueProgram;

    /**
     * Constructor for StockLedger class
     *
     * @Parameter - sampleDeque: a DequeInterface implementation
     */
    public StockLedger(DequeInterface sampleDeque)
    {
        //Initialize all attributes
        ContinueProgram = true;
        ledger = sampleDeque;
        totalStocks = 0;
        capitalGain = 0;
    }

    /**
     * Buy: asks and validates the user for how many shares to buy and for how much
     *
     * @Precondition: The program has started.
     *
     * @Postcondition: The shares are put into a stock lot and enqueued
     * or one of two exception types are thrown depending on the logical error that occurs.
     */
    public void Buy()
    {
        //Initialize scanner tool
        Scanner Scan = new Scanner(System.in);

        //Get the amount of stocks to purchase
        System.out.println("How many shares do you wish to buy?");
        int sharesPurchased = Scan.nextInt();

        //Get the share's price
        System.out.println("At what price will you be buying per share?");
        double sharePrice = Scan.nextDouble();

        //Perform the rest of the operations if the shares & price are above zero
        if (sharesPurchased > 0 && sharePrice > 0)
        {
            //Create new StockLot to be enqueued to the deque
            StockLot newStock = new StockLot(sharesPurchased, sharePrice);

            //Enqueue the StockLot to the back of the deque
            ledger.addToBack(newStock);

            //Update total share count
            totalStocks += sharesPurchased;

            //Confirmation prompt of stock lot purchase
            System.out.println("You have purchased " + sharesPurchased + " shares for $" + sharePrice + " per share.");
        }

        // Perform these operations when inputted amounts are logically invalid for this scenario
        else
            { throw new IllegalArgumentException(); }
    }

    /**
     * Sell: Asks and validates the user for how many shares to sell and for how much.
     *
     * @Precondition: The program has started and there is at least one stock lot enqueued in the deque.
     *
     * @Postcondition: The stock lots are dequeued to sell shares. Any shares sold that dip into other
     * stock lots but not fully drain said stock lot will cause the stock lot to be enqueued to the front
     * of the deque with the remaining shares left in it. The total capital gain is also calculated after
     * selling the shares. The other outcome is one of four possible exception types are thrown depending
     * on the logical error that occurs.
     */
    public void Sell()
    {
        //Initialize scanner tool
        Scanner Scan = new Scanner(System.in);

        //Checks if there are no shares to sell
        if (ledger.isEmpty())
        { throw new NoSuchElementException(); }

        //Initialize variables
        StockLot tempOne;

        //Get the amount of stocks to sell
        System.out.println("How many shares do you wish to sell?");
        int sharesSold = Scan.nextInt();

        //Get the share's price
        System.out.println("At what price will you be selling per share?");
        double sharePrice = Scan.nextDouble();

        //Store variable for later use
        int sharesToBeRemoved = sharesSold;

        // Perform these operations when inputted share amount is more than in possession
        if (sharesSold > totalStocks)
        { throw new IllegalStateException(); }

        //Perform the rest of the operations if the shares & price are above zero
        else if (sharesSold > 0 && sharePrice > 0)
        {
            //Until all specified shares are sold
            while (sharesSold > 0)
            {
                //Peek at the stock lot at front
                tempOne = (StockLot) ledger.getFront();

                //When the stock lot contains less than or equal to the amount of shares left to be sold
                if (tempOne.getShares() <= sharesSold)
                {
                    //Dequeue from the front, calculate the capital gains, and update how many
                    //shares are needed to be sold
                    tempOne = (StockLot) ledger.removeFront();
                    capitalGain += (sharePrice - tempOne.getBuyPrice()) * tempOne.getShares();
                    sharesSold -= tempOne.getShares();
                }

                //When the stock lot contains more than the amount of shares left to sell
                else
                    {
                        //Remove from the front, take out the shares and enqueue the remaining
                        //shares from the stock lot to the front.
                        tempOne = (StockLot) ledger.removeFront();
                        int remainingShares = tempOne.getShares() - sharesSold;
                        tempOne.setShares(remainingShares);
                        ledger.addToFront(tempOne);

                        //Calculate capital gain and update how many stocks are needed to be sold
                        capitalGain += (sharePrice - tempOne.getBuyPrice()) * sharesSold;
                        sharesSold -= sharesSold;
                    }
            }

            //Update total stocks we have globally
            totalStocks -= sharesToBeRemoved;

            //Confirmation prompt of stock lot sell
            System.out.println("You have sold " + sharesToBeRemoved + " shares for $" + sharePrice + " per share.");
        }

        // Perform these operations when inputted amounts are logically invalid for this scenario
        else
        { throw new IllegalArgumentException(); }
    }

    /**
     * TotalCapitalGain: Shows total gain/loss from selling shares and total shares in possession
     *
     * @Precondition: The program has started and some shares have been bought/sold
     *
     * @Postcondition: Displays the amount of money gained/lost from the sales as well as total shares
     */
    public void TotalCapitalGain()
    {
        System.out.println("Total capital gain/loss: $" + capitalGain);
        System.out.println("Total shares in possession: " + totalStocks);
    }

    /**
     * stockExchangeMenu: Display a menu and allow users to choose specified operations.
     *
     * @Postcondition: Continues repeating until the user quits, causing the program to exit.
     */
    public void stockExchangeMenu()
    {
        //Initialize scanner tool
        Scanner Scan = new Scanner(System.in);

        //Runs until user quits
        while(ContinueProgram)
        {
            //Introduction to program menu
            System.out.println("------------------------------------------");
            System.out.println("Welcome to the stock exchange terminal!");
            System.out.println("Please enter one of the following options:");
            System.out.println("Buy, Sell, Report, Quit");

            //Get user input on what they want to do
            String input = Scan.nextLine().toLowerCase();

            //Determine what operation to perform
            switch(input)
            {
                //If user wants to buy
                case "buy":
                    try { Buy(); }

                    //If input is not a number
                    catch (InputMismatchException e)
                    {
                        System.out.println("Invalid input, please enter numbers next time.");
                        System.out.println("Aborting task and returning to main menu...");
                    }

                    //If input is not logically correct
                    catch (IllegalArgumentException e)
                    {
                        System.out.println("One or more attributes were not above zero: Amount of Shares &/or Share Price");
                        System.out.println("Aborting task and returning to main menu...");
                    }
                    break;

                //If user wants to sell
                case "sell":
                    try{ Sell(); }

                    //If input is not a number
                    catch (InputMismatchException e)
                    {
                        System.out.println("Invalid input, please enter numbers next time.");
                        System.out.println("Aborting task and returning to main menu...");
                    }

                    //If no shares are present
                    catch (NoSuchElementException e)
                    {
                        System.out.println("Cannot sell when you have no shares.");
                        System.out.println("Aborting task and returning to main menu...");
                    }

                    //If more shares than in possession are to be sold
                    catch (IllegalStateException e)
                    {
                        System.out.println("Attempting to sell more shares than in possession.");
                        System.out.println("Aborting task and returning to main menu...");
                    }

                    //If input is not logically correct
                    catch (IllegalArgumentException e)
                    {
                        System.out.println("One or more attributes were not above zero: Amount of Shares &/or Share Price");
                        System.out.println("Aborting task and returning to main menu...");
                    }
                    break;

                //If user wants to vieew their reports
                case "report":
                    TotalCapitalGain();
                    break;

                //If user wants the quit
                case "quit":
                    ContinueProgram = false;
                    System.out.println("Quiting Program. We hope to see you again soon!");
                    break;

                //If user input is not one of the specified
                default:
                    System.out.println("Invalid input, returning to main menu.");
                    break;
            }
        }
    }
}
