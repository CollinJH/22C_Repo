// Collin Hargreaves
// CIS 22C
// Professor Mirsaeid Abolghasami
// 04/30/2022

import java.io.*;
import java.util.*;

/**
 * Primes is a program that will compute prime numbers using the sieve of Eratosthenes.
 * 
 * @author Charles Hoot
  * @version 4.0
 */

    
public class Primes
{

    public static void main(String args[])
    {

        int max;
        
        System.out.println("Please enter the maximum value to test for primality");
        max = getInt("   It should be an integer value greater than or equal to 2.");
        
        // COMPLETE THE MAIN

        // initialize three lists
        ListInterface<Integer> canidates = null;
        ListInterface<Integer> composites = null;
        ListInterface<Integer> primes = null;
        int prime;

        // create lists
        canidates = new AList<Integer>();
        composites = new AList<Integer>();
        primes = new AList<Integer>();

        for(int i = 2; i <= max; ++i) {
            canidates.add(i);
        }

        System.out.println(canidates);

        // take 2 out of the canidates and add it too primes
        prime = canidates.getEntry(1);
        canidates.remove(1);
        primes.add(prime);

        // first round of composites using 2
        System.out.printf("%s%s", prime, " is prime\n");
        getComposites(canidates, composites, prime);
        System.out.printf("%s%s%s","Canidates: ", canidates, " \n");
        System.out.printf("%s%s%s","Composites: ", composites, " \n");
        System.out.printf("%s%s%s","Primes: ", primes, " \n");
        
        // until canidate list is empty
        // check if 1st value in canidate list is prime
        // if it is do a round of checking composites

        while(canidates.getLength() != 0) {
            // get next prime value
            for (int i = 1; i <= canidates.getLength(); ++i) {
                if (isPrime(canidates.getEntry(i)) == true) {
                    prime = canidates.getEntry(i);
                    primes.add(canidates.getEntry(i));

                    System.out.printf("%s%s", prime, " is prime\n");

                    canidates.remove(i);
                    getComposites(canidates, composites, prime);
                    --i;
                } else {
                    continue;
                }

                System.out.printf("%s%s%s","Canidates: ", canidates, " \n");
                System.out.printf("%s%s%s","Composites: ", composites, " \n");
                System.out.printf("%s%s%s","Primes: ", primes, " \n");
            }
        }
        
    
    }


    // built a isPrime function to test values
    public static boolean isPrime(int num) {
        
        if (num <= 1 ) {
            return false;
        }

        // from 2 to n - 1
        for (int i = 2; i <= num/2; ++i) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
    
    
    /**
     * getComposites - Remove the composite values from possibles list and
     * put them in the composites list.
     *
     * @param  candidates   A list of integers holding the possible values.
     * @param  composites   A list of integers holding the composite values.
     * @param  prime   An Integer that is prime.
     */
    public static void getComposites(ListInterface<Integer> candidates, ListInterface<Integer> composites, Integer prime)
    {
        for (int i = 1; i <= candidates.getLength(); ++i) {
            if (candidates.getEntry(i) % prime == 0) {
                composites.add(candidates.getEntry(i));
                candidates.remove(i);
            }
        }
    }
    
    
    
    
    /**
     * Get an integer value.
     *
     * @return     An integer. 
     */
    private static int getInt(String rangePrompt)
    {
        Scanner input;
        int result = 10;        //Default value is 10
        try
        {
            input = new Scanner(System.in);
            System.out.println(rangePrompt);
            result = input.nextInt();
            
        }
        catch(NumberFormatException e)
        {
            System.out.println("Could not convert input to an integer");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }        
        catch(Exception e)
        {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }
        return result;
                                    
    }    
    
}
