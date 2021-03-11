/**
 * Author: Omer Basar
 * Filename: Driver.java - simple driver program for playing king of stacks
 * Version: 10/20/20
 * Assignment: 2
 */

// import statements
import java.util.Scanner;

public class Driver
{
    public static void main(String[] args)
    {
        // initialize variables.
        boolean continueGame = true;
        String answer;
        Game kingOfStacks = new Game();

        // loop for asking to continue game.
        while (continueGame)
        {
            kingOfStacks.gameCode();
            answer = continueGame();
            if (answer.equals("no"))
                continueGame = false;
        }
    }

    /**
     * continueGame: asks and validates the user for
     * their choice playing another game
     *
     * @return userChoice - a string containing yes or no
     *
     * Precondition: a game has just finished
     *
     * Postcondition: the game is played again or stops
     */
    public static String continueGame()
    {
        // establish Scanner tool
        Scanner Scan = new Scanner(System.in);

        // initialize variables
        String userChoice = "";
        boolean validChoice = false;

        // asks for user input until a valid choice is given
        while (!validChoice)
        {
            // asks and scans for input
            System.out.println("Would you like to play again? Input Yes or No");
            userChoice = Scan.nextLine();
            userChoice = userChoice.toLowerCase();

            // checks if userChoice is valid
            if (userChoice.equals("yes") || userChoice.equals("no"))
                validChoice = true;

            else
                System.out.println("Please enter a valid response");
        }

        // return statement
        return userChoice;
    }
}
