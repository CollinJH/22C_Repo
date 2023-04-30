// Collin Hargreaves
// CIS 22C
// Professor Mirsaeid Abolghasami
// 04/30/2022

import java.io.*;
import java.util.*;

/**
 * CountingGame is a program that will simulate a children's counting game used to select
 * someone.
 * 
 * @author Charles Hoot
 * @version 4.0
 */
    
public class CountingGame
{

    public static void main(String args[])
    {
        ListInterface<Integer> players = null;
        ListInterface<String> rhyme = null;
        
        int max;
        int position = 1;       // always start with the first player
        
        System.out.println("Please enter the number of players.");
        max = getInt("   It should be an integer value greater than or equal to 2.");
        System.out.println("Constructing list of players");
        
        // ADD CODE HERE TO CREATE THE LIST OF PLAYERS
        
        players = new AList<Integer>();

        for(int i = 1; i < max + 1; i++) {
            players.add(i);
        }

        System.out.println("The players list is " + players);
        
        rhyme = getRhyme();

        // ADD CODE HERE TO PLAY THE GAME

        System.out.println("The rhyme is " + rhyme);

        // create a loop that does the rhyme until there is only one remaining player

        // loop through until player length is only equal to one entry
        // new position will be equal to the returned value by doRhyme which plays the game one time
        // remove that position from the game
        // repeat until only one winner

        while (players.getLength() != 1) {
            position = doRhyme(players, rhyme, position);

            System.out.printf("%s%d%s" ,"removing player: ", players.getEntry(position), " \n");
            players.remove(position);

            // print remaining list of players after each round
            System.out.printf("%s%s%s", "remaining players: ", players.toString(), " \n");
        }


        
        System.out.println("The winner is " + players.getEntry(1));
    }
    
    
    /**
     * Do the rhyme with the players in the list and remove the selected
     * player.
     *
     * @param  players   A list holding the players.
     * @param  rhyme   A list holding the words of the rhyme.
     * @param  startAt A position to start the rhyme at.
     * 
     * @return The position of the player eliminated.
     */
   public static int doRhyme(ListInterface<Integer> players, ListInterface<String> rhyme, int startAt)
    {
        // COMPLETE THIS METHOD
        
        // loop through length of rhyme
        for(int i = 1; i <= rhyme.getLength(); i++) {
            // roll over if starting position is greater than length of player list
            if (startAt > players.getLength()) {
                startAt = 1;
            }

            // printing format to show what player is getting what word
            System.out.printf("%s%d%s%s%s", "Player:", players.getEntry(startAt)," Word:", rhyme.getEntry(i), "\n");

            // increment startAt, after loop finishes this will be the position to remove
            startAt++;
        }

        
        // return startAt - 1 so it returns the appropriate position
        return startAt - 1;

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
    
    /**
     * getRhyme - Get the rhyme.
     *
     * @return    A list of words that is the rhyme.
     */
    private static ListInterface<String> getRhyme()
    {
        Scanner input;
        String inString = "";
        ListInterface<String> rhyme = new AList<String>();
        
        try
        {
            input = new Scanner( System.in );
            
            System.out.println("Please enter a rhyme");
            inString = input.nextLine().trim();
            
            Scanner rhymeWords = new Scanner(inString);
            while(rhymeWords.hasNext())
            {
                rhyme.add(rhymeWords.next());
            }
            
        }
        catch(Exception e)
        {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will use a rhyme of size one");
        }

        // Make sure there is at least one word in the rhyme
        if(rhyme.getLength() < 1)
            rhyme.add("Default");
            
        return (ListInterface<String>)rhyme;
                                    
    }
    
}
