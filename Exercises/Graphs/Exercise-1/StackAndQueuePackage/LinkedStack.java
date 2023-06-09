
// Collin Hargreaves
// CIS 22C
// Professor Mirsaeid Abolghasemi
// 05/06/2024

import java.util.EmptyStackException;


public final class LinkedStack<T> implements StackInterface<T> {

private Node topNode; // References the first node in the chain

//default constructor
public LinkedStack() {
    topNode = null;
}
 

// Implement the unimplemented methods 

public void push(T newEntry) {

    Node newNode = new Node(newEntry, topNode);
    topNode = newNode;
}

public T peek () {
    if(isEmpty()) 
        throw new EmptyStackException();
    else
        return topNode.getData();
}

public T pop() {

    T top = peek(); // might throw empty stack exception
    topNode = topNode.getNextNode();

    return top;
}

public boolean isEmpty() {

    // returns true or false whether topNode is null or not
    return topNode == null;

}

public void clear() {

    // sets topNode to null
    topNode = null;
}
 

 

private class Node
{
    private T data;     // entry in stack
    private Node next;  // link to next node

    private Node(T dataPortion) {
        this(dataPortion, null);
    }

    private Node(T dataPortion, Node nextNode) {
        data = dataPortion;
        next = nextNode;
    }

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

 

}// end LinkedStack
