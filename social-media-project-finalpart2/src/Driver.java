import java.util.Scanner;

public class Driver {

    static Scanner myScanner = new Scanner(System.in);

    // create a object users so that we can add a set of exiting users to the hashed dictionary
    // this will be used to simulate activity on the social media platform

  
    public static void main(String[] args) {

        // create simulated user accounts
        // creating a network object will allow us to manipulate the dictionary
        // here in main we create new profiles
        // and new profile objects
        // we can manipulate these when we run the program

        try {
        SocialNetwork.createProfile("Sammy", 20);
        SocialNetwork.createProfile("Jasmine", 22);
        SocialNetwork.createProfile("Jackie", 21);
        SocialNetwork.createProfile("Adam", 19);
        SocialNetwork.createProfile("Billy", 18);
        SocialNetwork.createProfile("Steven", 22);
        
        
        Profile user1 = SocialNetwork.searchProfile("Steven");
        Profile user2 = SocialNetwork.searchProfile("Jasmine");
        Profile user3 = SocialNetwork.searchProfile("Sammy");
        Profile user4 = SocialNetwork.searchProfile("Billy");
        Profile user5 = SocialNetwork.searchProfile("Adam");
        Profile user6 = SocialNetwork.searchProfile("Jackie");

        user1.setOnline();
        user2.setBusy();
        user3.setOffline();
        user4.setBusy();
        user5.setAge(24);
        user3.setAge(22);
        user2.setAge(52);

        user1.addFriend(user2);
        user2.addFriend(user3);
        user3.addFriend(user2);
        user6.addFriend(user1);
        user6.addFriend(user2);
        user6.addFriend(user3);
        user5.addFriend(user4);


        } catch (SocialNetwork.NoSuchUserException e) {
            e.printStackTrace();
        }
        


        // start
        Menu.DisplayMenu();





        
    }
}


