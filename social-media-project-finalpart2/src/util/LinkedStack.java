package util;


public final class LinkedStack<T> implements StackInterface<T>
{

    private Node topNode; // References the first node in the chain

    private final String emptyErrMsg = "ERROR: Cannot complete action || the following stack is empty.";

    //default constructor

    public LinkedStack() {
        topNode = null;
    }

    // Implement the unimplemented methods

    @Override
    public void push(T newEntry) {
        topNode = new Node(newEntry, topNode);
    }

    @Override
    public T pop() {

        if (topNode == null)
            throw new EmptyStackException(emptyErrMsg);

        Node old = topNode;
        topNode = topNode.getNextNode();

        return old.getData();
    }

    @Override
    public T peek() {

        if (topNode == null)
            throw new EmptyStackException(emptyErrMsg);

        return topNode.getData();
    }

    @Override
    public boolean isEmpty() {
        return topNode == null;
    }

    @Override
    public void clear() {
        topNode = null;
    }



    private class Node
    {

        private T data; // entry in bag
        private Node next; // link to next node

        private Node(T dataPortion) {
            this(dataPortion, null);
        } // end constructor

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        } // end constructor

        private T getData() {
            return data;
        } // end getData

        private void setData(T newData) {
            data = newData;
        } // end setData

        private Node getNextNode() {
            return next;
        } // end getNextNode

        private void setNextNode(Node nextNode) {
            next = nextNode;
        } // end setNextNode
    }// end Node



}// end LinkedStack