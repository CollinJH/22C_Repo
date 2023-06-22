// Collin Hargreaves
// CIS 22C
// Professor Mirsaeid Abolghasemi
// 05/28/2024

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    static Scanner myScanner = new Scanner(System.in);

    // 
    private SocialNetwork existingUsers;
    private Profile currentUser;

    // network of profiles pass into constructor
    // instansiating existing users with profiles

    public Menu(SocialNetwork existingUsers) {
        this.existingUsers = existingUsers;

    }

    // this will be the menu the user will land on to pick a profile
    // to create a new profile
    // or to delete any existing profiles
    public void DisplayMenu() {
        System.out.println("Welcome to the Social Network!");
        System.out.println("1. Create Profile");
        System.out.println("2. Search Profile");
        System.out.println("3. Remove Profile");
        System.out.println("4. Pick Profile");
        System.out.println("5. Exit");

        int choice = myScanner.nextInt();
        myScanner.nextLine();

        switch (choice) {
            case 1:
                createNewProfile();
                break;
            case 2:
                searchProfile();
                break;
            case 3:
                removeProfile();
                break;
            case 4:
                pickProfile();
                break;
            case 5:
                System.out.println("Exiting... Goodbye");
                return;
            default:
            // recursion to call the menu until a correct option is chosen.
                System.out.println("Invalid choice, try again");
                DisplayMenu();
        }
        
    
    }

    
    public void searchProfile() {

        

        System.out.println("\nSearching for profiles...");
        System.out.println("Please choose an option, ");
        System.out.println("1. Manual Search (shows more information)");
        System.out.println("2. Display all profiles");

        int choice = myScanner.nextInt();
        myScanner.nextLine();

        switch (choice) {
            case 1:
                manualSerach();
                break;
            case 2:
                displayProfiles();
                break;
            default:
                System.out.println("Invalid choice, try again");
        }

        pressKeyToReturnMain();
    }

    public void manualSerach() {

        String nameSearch;
        Profile result;
        System.out.printf("Please enter a name to search for: ");
        nameSearch = myScanner.nextLine();

        try {
            result = existingUsers.searchProfile(nameSearch);
            result.printInfo();

        } catch (SocialNetwork.NoSuchUserException e) {
            e.printStackTrace();
        }

    }

    public void displayProfiles() {

        System.out.println(existingUsers.getAllProfiles());

    }

    public void removeProfile() {

        String removeName;
        System.out.println("Removing a profile...");
        System.out.println(existingUsers.getAllProfiles());
        System.out.println("Listed is all the current profiles in the system\n");
        System.out.printf("Please enter a profile name to remove: ");
        removeName = myScanner.nextLine();

        try {
            existingUsers.leaveNetwork(removeName);
        } catch (SocialNetwork.NoSuchUserException e) {
            e.printStackTrace();
        }

        System.out.println("\nUpdated profile list: ");
        System.out.println(existingUsers.getAllProfiles() + "\n");

        pressKeyToReturnMain();

    }


    // pick profile will turn you from system menu to user menu
    // user menu will be used to make actions at a user level
    // such as add friends, remove friends, setting status etc
    public void pickProfile() {

        String searchName;
        System.out.printf("Please enter a username: ");
        searchName = myScanner.nextLine();
        try {
            currentUser = existingUsers.searchProfile(searchName);
        } catch (SocialNetwork.NoSuchUserException e) {
            e.printStackTrace();
        }

        System.out.println("Logged in as... ");
        System.out.println(currentUser.getName());

        userMenu();
    }


    // reads input for user to create their profile
    public void createNewProfile() {

        String firstName;
        int age;

        System.out.println("Creating a profile...");
        System.out.printf("Please enter your first name: ");
        firstName = myScanner.nextLine();
        System.out.printf("Please enter your age: ");
        age = myScanner.nextInt();
        myScanner.nextLine();

        existingUsers.createProfile(firstName, age);
        System.out.println("Profile created sucessfully...\n");

        pressKeyToReturnMain();
    }

    // returning methods //
    public void printReturnToMain() {
        System.out.println("Returning to System Menu...");
    }

    public void printReturnToUser() {
        System.out.println("Returning to User Menu...");
    }

    public void pressKeyToReturnMain() {
        System.out.println("Press ENTER to continue...");
        try {
            System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        printReturnToMain();
        DisplayMenu();
    }

    public void pressKeyToReturnUser() {
        System.out.println("Press ENTER to continue...");
        try {
            System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        printReturnToUser();
        userMenu();
    }
    // returning methods //


    // FROM HERE ON WILL BE USER MENU METHODS, WILL MODIFY ELEMENTS AT THE SPECIFIC PROFILE LEVEL //

    public void userMenu() {



        System.out.println("Welcome to the User Menu!");
        System.out.println("Please select an option");
        System.out.println("1. Manage friends");
        System.out.println("2. Change status");
        System.out.println("3. Change name");
        System.out.println("4. Change age");
        System.out.println("5. Logout and return to main menu");

        int choice = myScanner.nextInt();
        myScanner.nextLine();

        switch (choice) {
            case 1:
                manageFriendsList();
                break;
            case 2:
                manageStatus();
                break;
            case 3:
                manageName();
                break;
            case 4:
                manageAge();
                break;
            case 5:
                printReturnToMain();
                DisplayMenu();
            default:
                System.out.println("Invalid choice, try again...");
                userMenu();
        }
        return;
    }


    public void manageFriendsList() {
        System.out.println("Manage friends list...");
        System.out.println("Please choose an option");
        System.out.println("1. View friends list");
        System.out.println("2. Add friend");
        System.out.println("3. Remove friend");

        int choice = myScanner.nextInt();
        myScanner.nextLine();

        switch (choice) {
            case 1:
                currentUser.printFriends();
                break;
            case 2:
                addFriend();
                break;
            case 3:
                removeFriend();
                break;
            default:
                System.out.println("Invalid choice, try again");
                manageFriendsList();
        }

        pressKeyToReturnUser();
    }

    public void addFriend() {

        System.out.println("Please enter a name you wish to add");
        String friendName = myScanner.nextLine();
        Profile newFriend;
        try {
            newFriend = existingUsers.searchProfile(friendName);
            currentUser.addFriend(newFriend);
        } catch (SocialNetwork.NoSuchUserException e) {
            e.printStackTrace();
        }

        System.out.println("Updated list of friends...");
        currentUser.printFriends();

        pressKeyToReturnUser();
    }

    public void removeFriend() {
        
        System.out.println("Printing current list of friends...");
        currentUser.printFriends();
        System.out.println("Please select a friend correspond with a number...");
        System.out.println("ie. 1, 2, 3... etc");
        
        int choice = myScanner.nextInt();
        myScanner.nextLine();

        System.out.println("Removing...");
        currentUser.removeFriend(choice);

        System.out.println("Printing updated list of friends...");
        currentUser.printFriends();

        pressKeyToReturnUser();
        
    
    }

    public void manageStatus() {

        System.out.println("\nSetting status...");
        System.out.println("Please choose an option to set your status");
        System.out.println("1. Set to online");
        System.out.println("2. Set to busy...");
        System.out.println("3. Set to offline");

        int choice = myScanner.nextInt();
        myScanner.nextLine();

        switch (choice) {
            case 1:
                currentUser.setOnline();
                System.out.println("Status set to Online...");
                break;
            case 2:
                currentUser.setOffline();
                System.out.println("Status set to Offline...");
                break;
            case 3:
                currentUser.setBusy();
                System.out.println("Status set to Busy...");
                break;
            default:
                System.out.println("Invalid input, try again...");
                manageStatus();
        }

        pressKeyToReturnUser();


    }

    public void manageName() {
        
        System.out.println("\nChanging name...");
        System.out.println("Please enter a new name: ");
        String newName = myScanner.nextLine();
        try {
            existingUsers.modifyProfile(currentUser, newName);
            pressKeyToReturnUser();
        } catch (SocialNetwork.UserAlreadyExistsException e) {
            e.printStackTrace();
        }

        pressKeyToReturnUser();

    }

    public void manageAge() {

        System.out.println("Chaning age...");
        System.out.println("Please enter your new age: ");
        int newAge = myScanner.nextInt();
        myScanner.nextLine();

        currentUser.setAge(newAge);

        System.out.println("Age updated to: " + newAge);

        pressKeyToReturnUser();
    }

    
    
}
