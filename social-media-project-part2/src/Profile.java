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
    private ListInterface<Profile> friends;

    public Profile(String name, int age) {

        this.name = name;
        this.age = age;
        this.status = STATUS_ARRAY[0];

        // here the constructor assigns the Profile list friends to a new, empty ArrayList
        this.friends = new AList<>();

    }

    // will print out all information of this profile object
    public void printInfo() {
        System.out.printf("%s", "\nName | Age | Status \n");
        System.out.printf("%s%s%d%s%s%s", name, "  ", age, "   ", status, "\n");
        printFriends();
        
    }
    
    // method to print out friends list
    public void printFriends() {
        System.out.println("Friends List: ");
        if (friends.getLength() == 0) {
            System.out.println("No friends... :(");
        }
        for (int i = 1; i <= friends.getLength(); i++) {
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

    // adding and removing friends using ArrayList

    // using the add method from the list adt to add a new friend to our friends ArrayList
    public void addFriend(Profile newFriend) {
        friends.add(newFriend);
    }

    // using the remove method from the list adt to remove a friend from our friend ArrayList
    // given a specific position
    public void removeFriend(int position) {
        friends.remove(position);
    }

    public ListInterface<Profile> getFriends() {
        return friends;
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