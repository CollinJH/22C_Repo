/**
 * A class that represents a rational number. 
 * 
 * @author Charles Hoot 
 * @version 4.0
*/

public class Rational
{
    // PUT PRIVATE DATA FIELDS HERE
    
    private int numerator;
    private int denominator;

    /**
     * The default constructor for objects of class Rational.  Creates the rational number 1.
     */
    public Rational()
    {       
 
        // Creates the rational number 1 
        // for numerator and denominator
        numerator = 1;
        denominator = 1;
    }

    /**
     * The alternate constructor for objects of class Rational.  Creates a rational number equivalent to n/d.
     * @param n The numerator of the rational number.
     * @param d The denominator of the rational number.
     */    
    public Rational(int n, int d)
    {
        // Pre-Condition: check if the denominator is non-zero
        // if the denominator is non-zero throw error

        if (d == 0) {
            throw new ZeroDenominatorException("Denominator is set to 0, not a rational number ");
        }

        // construct rational number n/d
        // Setting the instance variables to their respective arguments
        numerator = n;
        denominator = d;

        // normalize the rational number using normalize method
        normalize();
        


    }
    
    /**
     * Get the value of the Numerator
     *
     * @return     the value of the numerator
     */
    public int getNumerator()
    {
        // returns numerator
        return numerator;
    }
    
    /**
     * Get the value of the Denominator
     *
     * @return     the value of the denominator
     */
    public int getDenominator()
    {
        // returns denomatinor
        return denominator;
    }


    /**
     * Negate a rational number r
     * 
     * @return a new rational number that is negation of this number -r
     */    
    public Rational negate()
    {               
        // new instantiates the class, allocating memory for a new object
        // returns new object reference to that memory
        // multiplying the numerator by (-1) to set to negative
        return new Rational((-1) * numerator, denominator);
    }


    /**
     * Invert a rational number r 
     * 
     * @return a new rational number that is 1/r.
     */    
    public Rational invert()
    {               
        // check if numerator is 0
        // 0 in numerator cannot be swapped to denominator
        if (numerator == 0) {
            throw new ZeroDenominatorException("Cannot move 0 from numerator to denominator, result would be undefined");
        }

        // invert into new placeholder variables
        int placeNum = denominator;
        int placeDenom = numerator;

        // if the denomatinor is negative it needs to be moved to the numerator
        if (placeDenom < 0) {
            placeNum *= (-1);
            placeDenom *= (-1);
        }

        

        // instanciating a new class to allocate memory for a object reference
        // returns denominator and numerator switched
        return new Rational(placeNum, placeDenom);
    }





    /**
     * Add two rational numbers
     *
     * @param other the second argument of the add
     * @return a new rational number that is the sum of this and the other rational
     */    
    public Rational add(Rational other)
    {       
        // a new rational is passed in as an argument
        // add together numerator/denominator + other.getNumerator(), other.getDenominator
        Rational addedRational = new Rational((numerator * (other.getDenominator()) + (other.getNumerator() * denominator)), denominator * other.getDenominator());

        // normalize to simplify
        addedRational.normalize();

        return addedRational;
    }
    
     /**
     * Subtract a rational number t from this one r
     *
     * @param other the second argument of subtract
     * @return a new rational number that is r-t
     */    
    public Rational subtract(Rational other)
    {               
        Rational subRational = new Rational((numerator * (other.getDenominator()) - (other.getNumerator() * denominator)), denominator * other.getDenominator());

        // normalize to simplify
        subRational.normalize();

        return subRational;
    }

    /**
     * Multiply two rational numbers
     *
     * @param other the second argument of multiply
     * @return a new rational number that is the sum of this object and the other rational.
     */    
    public Rational multiply(Rational other)
    {       

        // multiply the two fractions together
        Rational muRational = new Rational(numerator * other.getNumerator(), denominator * other.getDenominator());

        // normalize
        muRational.normalize();

        // return computed rational
        return muRational;
    }
        
 
     /**
     * Divide this rational number r by another one t
     *
     * @param other the second argument of divide
     * @return a new rational number that is r/t
     */    
    public Rational divide(Rational other)
    {               
        // dividing will just be multiplying by the reciprocol
        Rational invertedOther = other.invert();

        // multiply together
        Rational divRational = new Rational(numerator * invertedOther.getNumerator(), denominator * invertedOther.getDenominator());

        // simplify
        divRational.normalize();

        // return computed rational
        return divRational;
    }
     
 
 
 /**
     * Put the rational number in normal form where the numerator
     * and the denominator share no common factors.  Guarantee that only the numerator
     * is negative.
     *
     */
    private void normalize()
    {
        // get negative value of denominator into numerator
        if (denominator < 0) {
            denominator *= (-1);
            numerator *= (-1);
        }

        // finding the greatest common factor of the numerator/denominator
        int result = gcd(Math.abs(numerator),Math.abs(denominator));

        // simply the fraction by the gcf

        numerator /= result;
        denominator /= result;
    }
    
    /**
     * Recursively compute the greatest common divisor of two positive integers
     *
     * @param a the first argument of gcd
     * @param b the second argument of gcd
     * @return the gcd of the two arguments
     */
    private int gcd(int a, int b)
    {
        int result = 0;
        if(a<b)
            result = gcd(b,a);
        else if(b==0)
            result = a;
        else
        {
            int remainder = a % b;
            result = gcd(b, remainder);
        }
        return result;
    }
   
    
    
}
