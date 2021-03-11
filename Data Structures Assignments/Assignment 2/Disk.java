/**
 * Author: Omer Basar
 * Filename: Disk.java - a blueprint class for Disk objects
 * Version: 10/20/20
 * Assignment: 2
 */

public class Disk
{

    //Attributes
    private int idValue;

    /**
     * Constructor for Disk class
     * @param desiredID - an int value used for identification
     */
    public Disk(int desiredID)
    { this.idValue = desiredID; }

    /**
     * getDiskInfo - returns the object's identification number
     *
     * @return idValue - an integer
     *
     * Precondition: a disk object exists
     */
    public int getDiskInfo()
    { return this.idValue; }
}
