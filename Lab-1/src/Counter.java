
/**
 * The counter class implements a counter that will roll over to the initial
 * value when it hits the maximum value.
 * 
 * @author Charles Hoot 
 * @version 4.0
 */
public class Counter
{
    // PUT PRIVATE DATA FIELDS HERE
    
    private final int minValue;
    private final int maxValue;

    private int counter;

    private boolean rolledOver;
    /**
     * The default constructor for objects of class Counter.  Minimum is 0 and the maximum
     * is the largest possible integer.
     */
    public Counter()
    {
        // set min and max values
        minValue = 0;
        maxValue = Integer.MAX_VALUE;

        // initalize counter value
        counter = 0;
    }
    
    
    /**
     * The alternate constructor for objects of class Counter.  The minimum and maximum values are given as parameters.
     * The counter starts at the minimum value.
     * @param min The minimum value that the counter can have
     * @param max The maximum value that the counter can have
     * */
    public Counter(int min, int max)
    {
        // assign instance variables to arguments using this
        this.minValue = min;
        this.maxValue = max;

        // set counter equal to min
        this.counter = min;

        // program has not rolled over
        this.rolledOver = false;

        // exception handling checking cases
        // min cannot be greater than max
        // min and max cannot be equal
        if (min > max) {
            throw new CounterInitializationException("Exception Thrown, max value cannot be less than min value");
        } else if (min == max) {
            throw new CounterInitializationException("Exception Thrown, min and max cannot be equal");
        }
    }
    
    /**
     * Determine if two counters are in the same state
     *
     * @param  otherObject   the object to test against for equality
     * @return     true if the objects are in the same state
     */
    public boolean equals(Object otherObject)
    {
        boolean result = true;

        // if is an instance of counter object, check if values are equal
        // if values are equal result remains true, if not returns false
        if (otherObject instanceof Counter other)
        {
            result = counter == other.counter
                     && minValue == other.minValue
                     && maxValue == other.maxValue
                     && rolledOver == other.rolledOver;
        }
        return result;
    }
    
    

    /**
     * Increases the counter by one
     */
    public void increase()
    {

        // increment counter
        counter++;

        // if counter becomes greater than the max value
        // rollover the counter back to min
        if (counter > maxValue) {
            counter = minValue;
            rolledOver = true;
        } else {
            rolledOver = false;
        }
        
    }
 
 
     /**
     * Decreases the counter by one
     */
    public void decrease()
    {
        //decrement counter
        counter--;
        
        // if counter becomes less than min value
        // rollover the counter back to max
        if (counter < minValue) {
            counter = maxValue;
            rolledOver = true;
        } else {
            rolledOver = false;
        }
    }
    
    /**
     * Get the value of the counter
     *
     * @return     the current value of the counter
     */
    public int value()
    {
        // returns the value of counter
        return counter;
    }
    
    
    /**
     * Accessor that allows the client to determine if the counter
     *             rolled over on the last count
     *
     * @return     true if the counter rolled over
     */
    public boolean rolledOver()
    {
        // returns true or false if the program has been rolled over
        return rolledOver;
    }
    
    /**
     * Override the toString method to provide a more informative
     * description of the counter
     *
     * @return     a descriptive string about the object
     */
    public String toString()
    {

        // returns a string of useful information
        // counter , minvalue, maxvalue and rolledover
        return "Counter has a value of: " + counter + "\n"
         + "Min value is: " + minValue
         + "\nMax value is: " + maxValue
         + "\nRolled over: " + rolledOver;	
    }
 
}
