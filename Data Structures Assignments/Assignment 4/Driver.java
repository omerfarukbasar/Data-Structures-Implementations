/**
 * Author: Omer Basar
 * Filename: Driver.java - simple driver program for running a stock market program
 * Version: 12/3/20
 * Assignment: 4
 * Additional Notes:
 * I received assistance from Chris Benson & Kaitlyn Torres on
 * debugging issues regarding files not being read.
 * I also received mental relief from them (requested to be kept here by Chris)
 */

public class Driver
{
    public static void main (String args[])
    {
        //Initialize attributes
        String sampleFile = "data.txt";
        DictionaryInterface sampleDictionary = new SortedArrayDictionary();
        FrequencyCount testRun = new FrequencyCount(sampleDictionary, sampleFile);

        //Run the program
        testRun.programCode();

    }
}
