// Collin Hargreaves
// CIS 22C
// Professor Mirsaeid Abolghasemi
// 04.22.2023

import java.lang.reflect.InaccessibleObjectException;

public class LList<T> implements ListInterface<T> 

{
    private Node firstNode; // Reference to first node of chain
    private int numberOfEntries;

    public LList() 
    {
        initializeDataFields();
    }

    public final void clear() 
    {
        initializeDataFields();
    } 

    // Initializes the class's data fields to indicate an empty list.
    private void initializeDataFields()
    {
        firstNode = null;
        numberOfEntries = 0;
    } // end initializeDataFields

    public void add(T newEntry) 
    {
        Node newNode = new Node(newEntry);
        if (isEmpty())
            firstNode = newNode;
        else                            // add to end of nonempty list
        {
            Node lastNode = getNodeAt(numberOfEntries);
            lastNode.setNextNode(newNode); // make last node reference new node
        }

        numberOfEntries++;
    }

    // based on the previous add code
    // begin by checking the validity of insertion position
    // if within range we create a new node
    public void add(int givenPosition, T newEntry)
    {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries + 1))
        {
            Node newNode = new Node(newEntry);

            if (givenPosition == 1)             // case 1 adding new node to beginning of chain
            {
                newNode.setNextNode(firstNode);
                firstNode = newNode;
            } else {                            // case 2 adding the new node to any position other than the beginning
                Node nodeBefore = getNodeAt(givenPosition - 1);
                Node nodeAfter = nodeBefore.getNextNode();
                newNode.setNextNode(nodeAfter);
                nodeBefore.setNextNode(newNode);
            }

            numberOfEntries++;
        } else {
            throw new InaccessibleObjectException("Illegal position given to operation add");
        }
    }

    // could simply test that the length of the list is zero
    // but when a list is empty the reference firstNode is null
    public boolean isEmpty()
    {
        boolean result;
        if (numberOfEntries == 0) // or getLength() == 0
        {
            // Assert(firstNode == null)
            result = true;
        } else {
            // Assert(firstNode != null)
            result = false;

        }

        return result;
    }


    // we will be able to test previous methods that we have written
    // must traverse the chain and copy the data in each node to an element within an array
    // it needs a local variable to reference each node in the chain
    // ie currentNode could reference the node whose data we want to copy, currentNode.getData()
    // we can write a loop that iterates until currentNode becomes null
    public T[] toArray()
    {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];
        
        int i = 0;
        Node currentNode = firstNode;
        while ((i < numberOfEntries) && (currentNode != null))
            {
                result[i] = currentNode.getData();
                currentNode = currentNode.getNextNode();
                i++;
            }
        return result;
    }

    // to remove we the first entry from our list 
    public T remove(int givenPosition)
    {
        T result = null;            // return value
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            if (givenPosition == 1)             // case 1 remove first entry
            {
                result = firstNode.getData();           // save entry to be removed
                firstNode = firstNode.getNextNode();    // remove entry
            } else {
                Node nodeBefore = getNodeAt(givenPosition - 1);
                Node nodeToRemove = nodeBefore.getNextNode();
                result = nodeToRemove.getData();        // save entry to be removed
                Node nodeAfter = nodeToRemove.getNextNode();
                nodeBefore.setNextNode(nodeAfter);      // remove entry
            }
            numberOfEntries--;
            return result;
        } else {
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
        }
    }


    // replace method

    // requires us to replace the data portion of a node with other data
    // we can use the private method getNodeAt to locate the node and the replace its data portion
    // we also check that the list is not empty and the given position is valid
    public T replace(int givenPosition, T newEntry)
    {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            Node desiredNode = getNodeAt(givenPosition);
            T originalEntry = desiredNode.getData();
            desiredNode.setData(newEntry);
            return originalEntry;
        } else {
            throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
        }
    }

    // retrieving a list entry
    public T getEntry(int givenPosition)
    {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            return getNodeAt(givenPosition).getData();
        } else {
            throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
        }
    }

    // the contains method 
    public boolean contains(T anEntry)
    {
        boolean found = false;
        Node currentNode = firstNode;
        while (!found && (currentNode != null))
        {
            if (anEntry.equals(currentNode.getData()))
            {
                found = true;
            } else {
                currentNode = currentNode.getNextNode();
            }
        }

        return found;
    }


    // simply returns amount of nodes in our chain
    public int getLength() {
        return numberOfEntries;
    }


    // Returns a reference to the node at a given position.
    // Precondition: The chain is not empty;
    // 1 <= givenPosition <= numberOfEntries.
    private Node getNodeAt(int givenPosition)
    {
        
        assert (firstNode != null) && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
        
        Node currentNode = firstNode;

        // Traverse the chain to locate the desired node
        // (skipped if givenPosition is 1)
        for (int counter = 1; counter < givenPosition; counter++)
        {
            currentNode = currentNode.getNextNode();
        }

        assert currentNode != null;

        return currentNode;
    } // end getNodeAt

    private class Node 
    {

        private T data; // entry to list 
        private Node next; // link to next node
        
        // constructor
        private Node(T dataPortion)
        {
            this(dataPortion, null);
        } 
    
        // alternate constructor
        private Node(T dataPortion, Node nextNode) 
        {
            data = dataPortion;
            next = nextNode;
        } 
    
        // data getter
        private T getData() 
        {
            return data;
        }
    
        // data setter
        private void setData(T newData) 
        {
            data = newData;
        }
    
        // node getter
        private Node getNextNode() 
        {
            return next;
        }
    
        // node setter
        private void setNextNode(Node nextNode) 
        {
            next = nextNode;
        }
        
    }

}