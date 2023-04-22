public class LList<T> implements ListInterface<T> 

{
    private Node firstNode; // Reference to first node of chain
    private int numberOfEntries;

    // Initializes the class's data fields to indicate an empty list.
    private void initializeDataFields()
    {
        firstNode = null;
        numberOfEntries = 0;
    } // end initializeDataFields



    // Returns a reference to the node at a given position.
    // Precondition: The chain is not empty;
    // 1 <= givenPosition <= numberOfEntries.
    private Node getNodeAt(int givenPosition)
    {
        assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
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

    private class ClassNode {

        private T data; // entry to list 
        private Node next; // link to next node
        
        // constructor
        private Node(T dataPortion)
        {
            this(dataPortion, null);
        } 
    
        // alternate constructor
        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        } 
    
        // data getter
        private T getData() {
            return data;
        }
    
        // data setter
        private void setData(T newData) {
            data = newData;
        }
    
        // node getter
        private Node getNextNode() {
            return next;
        }
    
        // noder setter
        private void setNextNode(Node nextNode) {
            next = nextNode;
        }
        
    }

}