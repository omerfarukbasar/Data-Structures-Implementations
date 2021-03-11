/**
 * Author: Omer Basar
 * Filename: Driver.java - simple driver program for running a stock market program
 * Version: 11/12/20
 * Assignment: 3
 */

public class Driver
{
    public static void main(String[] args)
    {
        //Initialize variables.
        DequeInterface sampleDeque = new LinkedDeque();
        StockLedger testRun = new StockLedger(sampleDeque);

        //Run the program
        testRun.stockExchangeMenu();
    }

}
