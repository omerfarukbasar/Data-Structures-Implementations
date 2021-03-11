/**
 * Author: Omer Basar
 * Filename: FrequencyCount.java - a blueprint class for FrequencyCount objects that
 * read an inputted file to calculate the frequency and total counts of each letter
 * Version: 12/3/20
 * Assignment: 4
 * Additional Notes:
 * I received assistance from Chris Benson & Kaitlyn Torres on
 * debugging issues regarding files not being read
 * I also received mental relief from them (requested to be kept here by Chris)
 */

//Import statements
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Iterator;

public class FrequencyCount
{
    //Declare private attributes of the class
    private DictionaryInterface dictionary;
    private String fileToBeUsed;

    /**
     * Constructor for FrequencyCount class objects
     *
     * @Parameter - sampleDictionary: a DictionaryInterface implementation
     *
     * @Parameter - sampleFile: a given File to be read
     */
    public FrequencyCount(DictionaryInterface sampleDictionary, String sampleFile)
    {
        //Initialize all attributes
        dictionary = sampleDictionary;
        fileToBeUsed = sampleFile;

        //Initialize all dictionary letter entries first
        for (int i = 65; i <= 90; i++)
        {
            dictionary.add((char) i, 0);
        }
    }

    /**
     * readFromFile: takes in a file and counts every instance of a letter before
     * adding entries to the dictionary
     *
     * @Parameter - fileToBeUsed: the given file that came from the constructor
     *
     * @Precondition: The program has started/FrequencyCount object exists
     *
     * @Postcondition: Each letter from the file is counted for frequency, then added
     * to the dictionary with the letter as the key and the count as the value
     */
    public void readFromFile(String fileToBeUsed)
    {
        //Attempt the creation of a file and subsequent operations that follow for reading
        try
        {
            //Initialize the file to be used
            File fileOne = new File(fileToBeUsed);

            //Initialize Scanner tool
            Scanner Scan = new Scanner(fileOne);

            //While there are more lines to read from the file...
            while (Scan.hasNext())
            {
                //Read and store one line of input and convert it to upper case,
                //then iterates to the next one
                String lineInUse = Scan.nextLine().toUpperCase();

                //Iterate through each index of the line
                for (int i = 0; i < lineInUse.length(); i++)
                {
                    //Store the character at index i of the line
                    char tempChar = lineInUse.charAt(i);

                    //When specified character is determined to be a letter
                    if (tempChar >= (char) 65 && tempChar <= (char) 90)
                    {
                        //Get the value from the entry
                        int tempValue = (int) dictionary.getValue(tempChar);

                        //Increment the value
                        tempValue++;

                        //Updates the entry with the new value
                        dictionary.add(tempChar, tempValue);
                    }
                }
            }
        }

        //Occurs when the file isn't found or typed incorrectly
        catch (FileNotFoundException e){System.out.println("The file was not found.");}
    }

    /**
     * totalLetterCount: counts all the letters in the file
     *
     * @Return - totalCount: Integer containing the total letters found in the file
     *
     * @Precondition: readFromFile has occurred
     *
     * @Postcondition: Each letter from the dictionary goes through an
     * iterator to extract and then tally up all the letters
     */
    public int totalLetterCount()
    {
        //Initialize integer for counting total letters
        int totalCount = 0;

        //Initialize iterator object for values
        Iterator valueIterator = dictionary.getValueIterator();

        //Checks if the value after the current one exists
        while (valueIterator.hasNext())
        {
            //Updates the count and iterates to the next one
            totalCount += (int) valueIterator.next();
        }

        //Returns the total letters from the file
        return totalCount;
    }

    /**
     * displayContents: displays information about all dictionary entries
     *
     * @Precondition: readFromFile has occurred
     *
     * @Postcondition: Each letter from the dictionary is printed out
     * with its symbol, number of occurrences, and its frequency
     */
    public void displayContents()
    {
        //Initialize iterator object for keys
        Iterator <Character> keyIterator = dictionary.getKeyIterator();

        //Initialize iterator object for values
        Iterator <Integer> valueIterator = dictionary.getValueIterator();

        //Store the total letter count after calling the appropriate function
        int totalLetters = totalLetterCount();

        //Prior to outputting results, print these statements
        System.out.println("The dictionary will display each entry in the following format...");
        System.out.println("Letter : Occurrences : Frequency");
        System.out.println("---------------------------------------------------------");

        //Checks if the key and value after the current one exists
        while (keyIterator.hasNext() && valueIterator.hasNext())
        {
            //Stores the current key and iterates to the next one
            char letter = keyIterator.next();

            //Stores the current value and iterates to the next one
            int count = valueIterator.next();

            //Calculates the frequency of occurrences for the current letter
            float frequency = (float) count/totalLetters;

            //Output the result for each letter
            System.out.println(letter + " : " + count + " : " + frequency);
        }
    }

    /**
     * programCode: runs the functions necessary to compute the desired results
     *
     * @Precondition: a FrequencyCount object exists
     *
     * @Postcondition: Reads the file, counts each letter's occurrence and
     * then outputs its number of occurrences and frequency percentage
     */
    public void programCode()
    {
        //Perform the file reading and the counting of letter occurrences
        readFromFile(fileToBeUsed);

        //Calculate the frequencies and output the results
        displayContents();
    }
}
