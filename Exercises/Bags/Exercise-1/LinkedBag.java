// Collin Hargreaves
// CIS 22C
// Professor Mirsaeid Abolghasemi
// 05/05/2024

public class LinkedBag <T> implements BagInterface<T> 

{

private Node firstNode; // Reference to first node of chain
private int numberOfEntries; // number of entries

 

//default constructor
public LinkedBag()
{
firstNode = null;
numberOfEntries = 0;
} 

// end default constructor

 

// Implement the unimplemented methods 
/** Adds a new entry to the bag.
  @param newEntry The ojbect to be added as a new entry
  @return True.
 */
public boolean add(T newEntry) {

    // Add to beginning of chain:
    Node newNode = new Node(newEntry);
    newNode.next = firstNode;   // make new node reference rest of chain
                                    // firstNode is null if chain is empty
                                    // New node is at beginning of chain
    firstNode = newNode;
    numberOfEntries++;
    return true;

} // end add

/**
 * Counts the number of times a given entry appears in this bag
 * @param anEntry the entry to be counted.
 * @return The number of times anEntry appeasr in the bag
 */

 public int getFrequencyOf(T anEntry) {
    int frequency = 0;
    int counter = 0;
    Node currentNode = firstNode;
    while ((counter < numberOfEntries) && (currentNode != null))
    {
        if (anEntry.equals(currentNode.getData())) {
            frequency++;
        }
        counter++;
        currentNode = currentNode.next;
    }

    return frequency;
}

public boolean contains(T anEntry) {
    boolean found = false;
    Node currentNode = firstNode;

    while (!found && (currentNode != null)) {
        if (anEntry.equals(currentNode.data)) {
            found = true;
        } else {
            currentNode = currentNode.next;
        }
    }
    
    return found;
}

public void clear() {
    while (!isEmpty()) {
        remove();
    }
} // end clear


/** removes one unspecified entry from this bag, if possible
 * @return Either the removed object, if the removal was successful or null
 * 
 */

 public T remove() {
    T result = null;
    if (firstNode != null) {
        result = firstNode.data;
        firstNode = firstNode.next;
        numberOfEntries--;
    }

    return result;
 }

 /** Removes one occurence of a given entry from this bag, if possible.
  * @param anEntry the entry to be removed.
  * @return True if the removal was successful, or false otherwise. 
  */
  public boolean remove (T anEntry) {
    boolean result = false;
    Node nodeN = getReferenceTo(anEntry);

    if (nodeN != null) {
        nodeN.data = firstNode.data; // Replace located entry with entry
                                     // in first node
        firstNode = firstNode.next;  // remove first node
        numberOfEntries--;
        result = true;
    }

    return result;

  }

/** Retrieves all entries in the bag
 *  @return a newly allocated array of all the entries in the bag
 */
public T[] toArray() {

    // safe because the new array contains null entries
    @SuppressWarnings("unchecked")
    T[] result = (T[])new Object[numberOfEntries]; // unchecked cast
    int index = 0;
    Node currentNode = firstNode;
    while ((index < numberOfEntries) && (currentNode != null))
    {
        result[index] = currentNode.getData();
        index++;
        currentNode = currentNode.getNextNode();
    }

    return result;
}
 

// Locates a given entry within this bag.
// Returns a reference to the node containing the entry, if located,
// or null otherwise.
private Node getReferenceTo(T anEntry)
{
    boolean found = false;
    Node currentNode = firstNode;

while (!found && (currentNode != null))
{
    if (anEntry.equals(currentNode.getData()))
        found = true;
    else
        currentNode = currentNode.getNextNode();
} // end while

return currentNode;
} // end getReferenceTo

private class Node
{
    private T data; // entry in bag
    private Node next; // Link to next node

    //constructors

    private Node(T dataPortion) {
        this(dataPortion, null);
    }

    private Node(T dataPortion, Node nextNode) {
        data = dataPortion;
        next = nextNode;
    }

    // accessor and mutator methods

    private T getData() {
        return data;
    }

    private void setData(T newData) {
        data = newData;
    }

    private Node getNextNode() {
        return next;
    }

    private void setNextNode(Node nextNode) {
        next = nextNode;
    }

}// end Node

@Override
public int getCurrentSize() {
    return numberOfEntries;
}

@Override
public boolean isEmpty() {
    if (numberOfEntries == 0) {
        return true;
    } else
        return false;
}

 

}// end LinkedBag
