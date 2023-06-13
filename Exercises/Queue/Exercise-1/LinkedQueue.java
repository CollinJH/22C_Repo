
// Collin Hargreaves
// CIS 22C
// Professor Mirsaeid Abolghasemi
// 05.17.2023

public final class LinkedQueue<T> implements QueueInterface<T> {

    private Node firstNode;
    private Node lastNode;

    // allocate a new node and add it to the end of the chain
    // if empty, we make both data fields, first and last node
    // reference the new node
    // otherwise both last node in chain, and datafield must reference the new node

    public void enqueue(T newEntry) {
        Node newNode = new Node(newEntry, null);
        if (isEmpty()) {
            firstNode = newNode;
        } else 
            lastNode.setNextNode(newNode);
        lastNode = newNode;
    }

    // operation requires O(1)
    // get entry at front of queue by accessing data portion
    // of the first node in the chain

    public T getFront() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        } else {
            return firstNode.getData();
        }
    }

    // removing the front entry
    // retrieves the entry at the front of the queue
    // then removes the chains first node
    // this is done by making fistNode reference the second node in the chain
    // if the chain had one node dequeue would make the chain empty

    public T dequeue() {
        T front = getFront();
        firstNode.setData(null);
        firstNode = firstNode.getNextNode();
        if (firstNode == null) {
            lastNode = null;
        }
        return front;
    }

    public boolean isEmpty() {
        if (firstNode == null && lastNode == null){
            return true;
        } else 
            return false;
    }

    public void clear()
    {
        firstNode = null;
        lastNode = null;
    }

    private class Node {
        private T data;
        private Node next;

        private Node (T dataPortion) {
            this(dataPortion, null);
        }

        private Node (T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }

        private T getData() {
            return data;
        }

        private Node getNextNode() {
            return next;
        }

        private void setData(T newData) {
            this.data = newData;
        }

        private void setNextNode(Node newNode) {
            this.next = newNode;
        }
    }
}