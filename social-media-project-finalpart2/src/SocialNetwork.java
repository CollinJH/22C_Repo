// Collin Hargreaves
// CIS 22C
// Professor Mirsaeid Abolghasemi
// 06/23/2023


import java.util.Iterator;
import java.util.Arrays;
import util.*;


public class SocialNetwork {
    
    // using an undirected graph
    // this allows for friendships to be bidirectional
    // this also makes adding and removing friendships quick
    // adding an edge signifies a friendship and removing it takes that property away
    private static final UndirectedGraph<Profile> graph = new UndirectedGraph<>();

    // searches for a profile with a name query
    public static Profile searchProfile(String query) throws NoSuchUserException {

        // iterates over graph verticie keys
        // do not create a new profile object using query to compare to
        // this will create an entire new hashcode and will not be able to be located
        
        Iterator<Profile> iterator = graph.getVerticesKeyIterator();
        Profile currentProfile;

        // if the iterator has another iteration
        while (iterator.hasNext()) {
            // set profile object to next iteration
            currentProfile = iterator.next();

            // if profile object name is equal to the query name
            // we can return the profile as found
            if (currentProfile.getName().equals(query)) {
                return currentProfile;
            }
        }
        

        throw new NoSuchUserException();
    }


    // simple create profile method
    // adds profile to the graph
    public static void createProfile(String name, int age){
        Profile profile = new Profile(name, age);
        addMember(profile);
    }

    // returns a string of an array for all profiles
    public static String getAllProfiles() {
        // create a arraylist to store names, and key iterator 

        ListInterface<String> profileNames = new AList<>();
        Iterator<Profile> iterator = graph.getVerticesKeyIterator();

        // iterate through the keys
        while (iterator.hasNext()) {
            // add each key, the name in this case, to the array list
            profileNames.add(iterator.next().getName());
        }

        int n = profileNames.getLength();
        String[] result = new String[n];
        for (int i = 1; i <= n; i++) {
            result[i - 1] = profileNames.getEntry(i);
        }

        return Arrays.toString(result);
    }

    // takes in a profile object, and new name
    // sets a new name using the search query which will return a new updated object
    public static void modifyProfile(Profile profile, String newName) throws UserAlreadyExistsException {
        try {
            searchProfile(profile.getName()).setName(newName);
        } catch (NoSuchUserException e) {
            System.out.println(e.getMessage());
        }
    }

    // allows us to search the graph for a given profile
    public static VertexInterface<Profile> searchVertex(Profile profile) throws NoSuchUserException {

        // create iterator for vertice values
        Iterator<VertexInterface<Profile>> iterator = graph.getVerticesValueIterator();
        // track current vertex 
        VertexInterface<Profile> currentVertex;

        // iterate over graph
        while (iterator.hasNext()) {
            currentVertex = iterator.next();
            // check if current vertex in iteration is equal to vertex searching for
            if (currentVertex.getLabel().equals(profile)) {
                return currentVertex;
            }
        }

        throw new NoSuchUserException();
    }

    // allows us to iterate through each vertex
    // if search vertex is successful
    // method retrieves the neighboriterator which allows us to locate the adjacent verticies, (friends)
    public static Iterator<VertexInterface<Profile>> getFriendIterator(Profile profile) {
        try {
            return searchVertex(profile).getNeighborIterator();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // creates a vertex on the graph
    public static void addMember(Profile profile){
        graph.addVertex(profile);
    }

    // removes a vertex from the graph
    public static void removeMember(String name) {
        Profile profileToRemove = new Profile(name);
        graph.removeVertex(profileToRemove);
    }

    // add friendship
    public static void addFriend(Profile profile1, Profile profile2) {
        graph.addEdge(profile1, profile2);
    }

    // remove friendship
    public static void removeFriend(Profile profile1, Profile profile2) {
        graph.removeEdge(profile1, profile2);
    }

    // check if are friends by seeing if there are edges 
    public boolean areFriends(Profile profile1, Profile profile2) {
        return graph.hasEdge(profile1, profile2);
    }

    // creating a subclass of class exception
    // two constructors
    // first one is default, gives error message
    // second one can be a given String message
    public static class UserAlreadyExistsException extends Exception {
        public UserAlreadyExistsException() {
            super("User already exists..");
        }

        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }

    // subclass of class exception
    // same thing as above except for the case where there is no user with the key
    public static class NoSuchUserException extends Exception {
        public NoSuchUserException() {
            super("User does not exist..");
        }

        public NoSuchUserException(String message) {
            super(message);
        }
    }

}
    
