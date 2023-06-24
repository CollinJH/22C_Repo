package util;


public final class LinkedQueue<T> implements QueueInterface<T>
{

    private Node head;
    private Node tail;


    public LinkedQueue() {
        head = null;
        tail = null;
    }

    @Override
    public void enqueue(T newEntry) {

        Node newNode = new Node(newEntry);

        if (head == null) {
            head = newNode;
            tail = head;

            return;
        }

        tail.setNextNode(newNode);
        tail = newNode;
    }

    @Override
    public T dequeue() {

        if (isEmpty()) throw new EmptyQueueException("ERROR: The following queue is empty!");

        Node old = head;
        head = head.getNextNode();
        if (tail == old) tail = head;


        return old.getData();
    }

    @Override
    public T getFront() {

        if (isEmpty()) throw new EmptyQueueException("ERROR: The following queue is empty!");

        return head.getData();
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
    }

    private class Node {

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
    }
} // end Linkedqueue
