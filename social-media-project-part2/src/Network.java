import java.util.Scanner;

public class Network {

    static Scanner myScanner = new Scanner(System.in);

    // create a object users so that we can add a set of exiting users to the hashed dictionary
    // this will be used to simulate activity on the social media platform

    private static final SocialNetwork network = new SocialNetwork();
    public static void main(String[] args) {

        // create simulated user accounts
        // creating a network object will allow us to manipulate the dictionary
        // here in main we create new profiles
        // and new profile objects
        // we can manipulate these when we run the program

        try {
        network.createProfile("Sammy", 23);
        network.createProfile("Steven", 34);
        network.createProfile("Jasmine", 22);
        network.createProfile("Billy", 29);
        network.createProfile("Adam", 31);
        network.createProfile("Jackie", 24);
        
        Profile user1 = network.searchProfile("Steven");
        Profile user2 = network.searchProfile("Jasmine");
        Profile user3 = network.searchProfile("Sammy");
        Profile user4 = network.searchProfile("Billy");
        Profile user5 = network.searchProfile("Adam");
        Profile user6 = network.searchProfile("Jackie");

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
        
        // pass network as a parameter
        Menu menu = new Menu(network);

        // start
        menu.DisplayMenu();





        
    }
}


