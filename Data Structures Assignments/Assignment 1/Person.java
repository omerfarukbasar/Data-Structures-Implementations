
/**
 * Person.java - basic object to store
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Person
{
    private String name;
    private int age;
    
    public Person(String n, int a)
    {
        name = n;
        age = a;
    }
    
    public String toString()
    {
        return name + ", " + age;
    }
    
    public boolean equals(Person other)
    {
        return (this.name.equals(other.name) && this.age == other.age);
    }
}
