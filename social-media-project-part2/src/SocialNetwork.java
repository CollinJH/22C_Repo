import java.util.Iterator;

import util.AList;
import util.DirectedGraph;
import util.HashedDictionary;
import util.ListInterface;

import java.util.Arrays;

public class SocialNetwork {
    
    // the dictionary of profiles
    private HashedDictionary<String, Profile> profiles;
    private DirectedGraph<String> graph;

    // constructor create a new hasheddictionary
    public SocialNetwork() {
        profiles = new HashedDictionary<>();
        graph = new DirectedGraph<>();
    }

    // takes in a profile as an argument
    // checks to see if the profile is already in the dictionary
    // if not the profile is added
    public void joinNetwork(Profile profile) throws UserAlreadyExistsException {
        if (profiles.contains(profile.getName().toLowerCase())) {
            throw new UserAlreadyExistsException();
        }

        profiles.add(profile.getName().toLowerCase(), profile);
    }

    // takes a userName aka key as a argument
    // checks if that user exists in the network
    // if it does removes that profile

    public void leaveNetwork(String userName) throws NoSuchUserException {
        if (profiles.getValue(userName) == null) {
            throw new NoSuchUserException();
        } else {
            profiles.remove(userName);
        }
    }

    // method to create a new profile
    // after creating a profile will try to join the network
    // will not be able to with duplicate username under the useralreadyexistsexception

    public void createProfile(String name, int age) {
        Profile profile = new Profile(name, age);
        try {
            joinNetwork(profile);
            addMember(name);
        } catch (SocialNetwork.UserAlreadyExistsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // creates an arraylist of all the profile names
    // adds each profile name entry into a new result array
    // returns that array as a string
    
    public String getAllProfiles() {
        ListInterface<String> profileNames = new AList<>();

        Iterator<String> valIterator = profiles.getKeyIterator();

        while (valIterator.hasNext()) {
            profileNames.add(valIterator.next());
        }

        int n = profileNames.getLength();

        String[] result = new String[n];
        for(int i = 1; i <= n; i++) {
            result[i - 1] = profileNames.getEntry(i);
        }
        return Arrays.toString(result);
        
    }

    // this will essentially change the name of the profile and the key
    // this allows for an edit in the username to make sure the key is also changed
    // so it still corresponds to the expected profile

    public void modifyProfile(Profile profile, String newName) throws UserAlreadyExistsException {
        if (profile.getName().toLowerCase() != null) {
            newName = newName.toLowerCase();
            String oldProfileName = profile.getName().toLowerCase();
            try {
                leaveNetwork(oldProfileName);
            } catch (SocialNetwork.NoSuchUserException e) {
                e.printStackTrace();
            }
            profile.setName(newName);
            joinNetwork(profile);
            
        }


    }

    // this method will search for the key value username
    // returns the key value username if it is found
    public Profile searchProfile(String userName) throws NoSuchUserException {
        userName = userName.toLowerCase();
        if (profiles.getValue(userName) == null) {
            throw new NoSuchUserException();
        }
        return profiles.getValue(userName);
    }


    // creating a subclass of class exception
    // two constructors
    // first one is default, gives error message
    // second one can be a given String message
    public class UserAlreadyExistsException extends Exception {
        public UserAlreadyExistsException() {
            super("User already exists..");
        }

        public UserAlreadyExistsException(String message) {
            super(message);
        }

    }

    // subclass of class exception
    // same thing as above except for the case where there is no user with the key
    public class NoSuchUserException extends Exception {
        public NoSuchUserException() {
            super("User does not exist..");
        }

        public NoSuchUserException(String message) {
            super(message);
        }
    }

    // creating the addition of adding directed graph usage to track relationships of friends
    public void addMember(String memberName) {
        graph.addVertex(memberName);
    }

    // add friendship
    public void addFriendship(String member1, String member2) {
        graph.addEdge(member1, member2);
    }

    // remove friendship
    public void removeFriendship(String member1, String member2) {
        graph.removeEdge(member1, member2);
    }

}
    
