/**
 * Author: Omer Basar
 * Filename: Game.java - a blueprint class for game objects that
 * utilizes helper methods, disk objects, and any StackInterface
 * object to play a game of King of Stacks
 * Version: 10/20/20
 * Assignment: 2
 */

// import statements
import java.util.EmptyStackException;
import java.util.Random;
import java.util.Scanner;

public class Game
{

    /**
     * Constructor for Game class
     */
    public Game() {}

    /**
     * readAndValidateUserChoice: asks and validates the user for
     * their choice in the amount of turns
     *
     * @return userChoice - a string containing the user's choice
     *
     * Precondition: the game hasn't started yet
     * Postcondition: the amount of turns has been set
     */
    public static int readAndValidateTurnInput()
    {
        // establish Scanner tool
        Scanner Scan = new Scanner(System.in);

        // initialize variables
        int userChoice = 0;
        boolean validChoice = false;

        // asks for user input until a valid choice is given
        while (!validChoice)
        {
            // asks and scans for input
            System.out.println("Enter an amount of 20 or more turns");
            userChoice = Scan.nextInt();

            // checks if userChoice is valid
            if (userChoice >= 20)
                validChoice = true;

            else
                System.out.println("Inputted amount is too small, please try again.");
        }

        // return statement
        return userChoice;
    }

    /**
     * generateStackChoice - randomly generates an int and returns the Stack with
     * the matching int assigned to it
     *
     * @param a,b,c - StackInterface objects (could be VectorStack, ArrayStack, LinkedStack)
     *
     * @return StackInterface object based on whatever random int was chosen
     *
     * Precondition: 3 StackInterface objects exist
     *
     * Postcondition: randomly chosen stack is selected to be used
     */
    public static StackInterface generateStackChoice(StackInterface a, StackInterface b, StackInterface c)
    {
        //Establish Random tool.
        Random rand = new Random();

        //Initialize variables.
        String computerChoice;
        int numberGen;
        StackInterface chosen = null;

        //Generate the random number in range 0 to 2.
        numberGen = rand.nextInt(3);

        //Select which stack to be used
        if (numberGen == 0)
            chosen = a;

        else if (numberGen == 1)
            chosen = b;

        else
           chosen = c;

        // return statement
        return chosen;
    }

    /**
     * popTimer - attempts to pop a disk from each stack based on turn intervals
     *
     * @param turn - int value containing the current turn of the game
     * @param a,b,c - StackInterface objects (could be VectorStack, ArrayStack, LinkedStack)
     *
     * Precondition: 3 StackInterface objects exist and a game is in progress
     *
     * Postcondition: one or more stacks pop and an exception is handled when one of the stacks is empty
     */
    public static void popTimer(int turn, StackInterface a, StackInterface b, StackInterface c)
    {
        // initialize variable
        Disk tempDisk;

        // tries to pop from stack A
        try
        {
            // every 3 turns
            if (turn % 3 == 0)
            {
                tempDisk = (Disk) a.pop();
                System.out.println("A disk has been popped from Stack A");
            }
        }
        catch (EmptyStackException e)
        {System.out.println("Stack A was unable to pop due to being empty, moving onwards");}

        // tries to pop from stack B
        try
        {
            // every 5 turns
            if (turn % 5 == 0)
            {
                tempDisk = (Disk) b.pop();
                System.out.println("A disk has been popped from Stack B");
            }
        }
        catch (EmptyStackException e)
        {System.out.println("Stack B was unable to pop due to being empty, moving onwards");}

        // tries to pop from stack C
        try
        {
            // every 7 turns
            if (turn % 7 == 0)
            {
                tempDisk = (Disk) c.pop();
                System.out.println("A disk has been popped from Stack C");
            }

        }
        catch (EmptyStackException e)
        {System.out.println("Stack C was unable to pop due to being empty, moving onwards");}

    }

    /**
     * tallyScores - removes disks from each stack and checks each disk ID to tally up a score
     *
     * @param tally - int array that holds the scores for each player
     * @param a,b,c - StackInterface objects (could be VectorStack, ArrayStack, LinkedStack)
     *
     * Precondition: 3 StackInterface objects exist, tally array exists, and the game has finished
     *
     * Postcondition: the tally array has been updated with the scores
     */
    public static void tallyScores(int[] tally, StackInterface a, StackInterface b, StackInterface c)
    {
        // initialize variable
        Disk tempDisk;

        //remove and tally player disks from Stack A
        while (!a.isEmpty())
        {
            tempDisk = (Disk) a.pop();

            //player one disks
            if(tempDisk.getDiskInfo() == 1)
            {
                tally[0]++;
            }

            //player two disks
            else
                tally[1]++;
        }

        //remove and tally player disks from Stack B
        while (!b.isEmpty())
        {
            tempDisk = (Disk) b.pop();

            //player one disks
            if(tempDisk.getDiskInfo() == 1)
            {
                tally[0]++;
            }

            //player two disks
            else
                tally[1]++;
        }

        //remove and tally player disks from Stack C
        while (!c.isEmpty())
        {
            tempDisk = (Disk) c.pop();

            //player one disks
            if(tempDisk.getDiskInfo() == 1)
                tally[0]++;

            // player two disks
            else
                tally[1]++;
        }

    }

    /**
     * gameCode - plays one game of King of the Stacks
     *
     * Precondition: a game object exists
     *
     * Postcondition: plays one game and displays scores at the end
     */
    public static void gameCode()
    {
        // initialize variables
        StackInterface stackA = new VectorStack();
        StackInterface stackB = new ArrayStack();
        StackInterface stackC = new LinkedStack();
        StackInterface tempStack;
        int[] tally = new int[2];
        Disk playerOne = new Disk(1);
        Disk playerTwo = new Disk(2);

        // get user input turn amount
        int turns = readAndValidateTurnInput();

        // play game
        for (int i = 1; i <= turns; i++)
        {
            // declare the current turn
            System.out.println("For turn " + i + ":" );

            // determines when it's player two's turn
            if(i % 2 == 0)
            {
                tempStack = generateStackChoice(stackA,stackB, stackC);
                tempStack.push(playerTwo);
                if (tempStack == stackA)
                {
                    System.out.println("Player Two pushed a disk onto Stack A");
                }
                else if (tempStack == stackB)
                {
                    System.out.println("Player Two pushed a disk onto Stack B");
                }
                else
                    System.out.println("Player Two pushed a disk onto Stack C");
            }

            // determines when it's player one's turn
            else
                {
                    tempStack = generateStackChoice(stackA,stackB, stackC);
                    tempStack.push(playerOne);
                    if (tempStack == stackA)
                    {
                        System.out.println("Player One pushed a disk onto Stack A");
                    }
                    else if (tempStack == stackB)
                    {
                        System.out.println("Player One pushed a disk onto Stack B");
                    }
                    else
                        System.out.println("Player One pushed a disk onto Stack C");
                }

            // pops a disk from a stack(s) when a turn condition has been met
            popTimer(i,stackA,stackB, stackC);
        }

        // tally score
        System.out.println("Game has reached max turns, tallying scores now...");
        tallyScores(tally, stackA, stackB, stackC);

        // print results
        System.out.println("Player One had " + tally[0] + " disks at the end");
        System.out.println("Player Two had " + tally[1] + " disks at the end");

    }
}
