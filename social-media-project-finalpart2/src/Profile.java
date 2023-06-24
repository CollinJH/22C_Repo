import java.util.Iterator;
import java.util.Arrays;
import util.*;

public class Profile {

    // declaring values to be able to set status
    private final static String[] STATUS_ARRAY  = {"Online", "Offline", "Busy..."};

    private final static int online = 0;
    private final static int offline = 1;
    private final static int busy = 2;

    // instance variable declarations
    private String name;
    private String status;
    private int age;

    // creating a private variable friends of type ListInferface<Profile>
    // this will be used to add and remove friends to an arraylist


    public Profile(String name) {
        this.name = name;
        this.status = STATUS_ARRAY[0];
    }

    // constructor with 
    public Profile(String name, int age) {
        this.name = name;
        this.age = age;
        this.status = STATUS_ARRAY[0];

    }

    // will print out all information of this profile object
    public void printInfo() {
        System.out.printf("%s", "\nName | Age | Status \n");
        System.out.printf("%s%s%d%s%s%s", name, "  ", age, "   ", status, "\n");
        
    }
    

    public void getFriends() {
        ListInterface<Profile> friends = new AList<>();
        Iterator<VertexInterface<Profile>> iterator = SocialNetwork.getFriendIterator(this);

        assert iterator != null;

        while(iterator.hasNext()) {
            friends.add(iterator.next().getLabel());
        }

        System.out.printf("Printing friends list...\n");
        for(int i = 1; i <= friends.getLength(); i++) {
            System.out.println(friends.getEntry(i).getName());
        }
        
    }

    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    // using the add method from the list adt to add a new friend to our friends ArrayList
    public void addFriend(Profile newFriend) {
        SocialNetwork.addFriend(this, newFriend);
    }

    // using the remove method from the list adt to remove a friend from our friend ArrayList
    // given a specific position
    public void removeFriend(Profile oldFriend) {
        SocialNetwork.removeFriend(this, oldFriend);
    }

    // setting and getting status
    public void setStatus(String newStatus) {
        this.status = newStatus;
    }

    public String getStatus() {
        return status;
    }

    // set each status calling setStatus method and respective input
    public void setOnline() {
        setStatus(STATUS_ARRAY[online]);
    }

    public void setOffline() {
        setStatus(STATUS_ARRAY[offline]);
    }

    public void setBusy() {
        setStatus(STATUS_ARRAY[busy]);
    }



}