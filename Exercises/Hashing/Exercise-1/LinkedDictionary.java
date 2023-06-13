// Collin Hargreaves
// CIS 22C
// Professor Mirsaeid Abolghasemi
// 05/22/2024

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedDictionary<K, V> implements DictionaryInterface<K, V> {

    private Node firstNode;
    private int numberOfEntries;

    public LinkedDictionary() {
        initalizeDataFields();
    }

    private void initalizeDataFields() {
        firstNode = null;
        numberOfEntries = 0;
    }

    public V add(K key, V value) {
        V result = null;

        // Search chain for a node containing key
        Node currentNode = firstNode;

        while ( (currentNode != null) && !key.equals(currentNode.getKey())) {
            currentNode = currentNode.getNextNode();
        }

        if (currentNode == null) {
            // key not in dictionary, add new node at beginning of chain
            Node newNode = new Node(key, value);
            newNode.setNextNode(firstNode);
            firstNode = newNode;
            numberOfEntries++;
        } else {
            // key in dictionary, replace corresponding value
            result = currentNode.getValue();
            currentNode.setValue(value); // replace value
        }
        
        return result;
    }

    public V remove(K key) {
        V result = null; // return value

        if (!isEmpty()) {
            // search chain for node containing key
            // save reference to preceding node

            Node currentNode = firstNode;
            Node nodeBefore = null;

            while ((currentNode != null) && !key.equals(currentNode.getKey())) {
                nodeBefore = currentNode;
                currentNode = currentNode.getNextNode();
            }

            if (currentNode != null) {
                // node found, remove
                Node nodeAfter = currentNode.getNextNode(); // node after the one to be removed

                if (nodeBefore == null) {
                    firstNode = nodeAfter;
                } else {
                    nodeBefore.setNextNode(nodeAfter);
                }

                result = currentNode.getValue();
                numberOfEntries--;
            }
        }

        return result;
    }

    public V getValue(K key) {
        V result = null;

        // search for a node that contains key
        Node currentNode = firstNode;

        while ( (currentNode != null) && !key.equals(currentNode.getKey())) {
            currentNode = currentNode.getNextNode();
        }

        if (currentNode != null) {
            // search key found
            result = currentNode.getValue();
        } 

        return result;
    }

    public boolean contains(K key) {

        return getValue(key) != null;
    }

    public Iterator<K> getKeyIterator() {
        return new KeyIterator();
    }
    public Iterator<V> getValueIterator() {
        return new ValueIterator();
    }

    public boolean isEmpty() {
        return numberOfEntries == 0;
    }
    
    public int getSize() {
        return numberOfEntries;
    }

    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }

    // same as in sortedlinkeddictionary
    // since iterators implement iterator, methods must be public
    private class KeyIterator implements Iterator<K> {
        private Node nextNode;

        private KeyIterator() {
            nextNode = firstNode;
        }

        public boolean hasNext() {
            return nextNode != null;
        }

        public K next() {
            K result;

            if (hasNext()) {
                result = nextNode.getKey();
                nextNode = nextNode.getNextNode();
            } else {
                throw new NoSuchElementException();
            }

            return result;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class ValueIterator implements Iterator<V> {
        private Node nextNode;

        private ValueIterator() {
            nextNode = firstNode;
        } // end default constructor

        public boolean hasNext() {
            return nextNode != null;
        } // end hasNext

        public V next() {
            V result;

            if (hasNext()) {
                result = nextNode.getValue();
                nextNode = nextNode.getNextNode();
            }
            else {
                throw new NoSuchElementException();
            } // end if

            return result;
        } // end next

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        } // end remove
    } // end getValueIterator


    private class Node {

        private K key;
        private V value;
        private Node next;

        private Node(K searchKey, V dataValue) {
            this.key = searchKey;
            this.value = dataValue;
            this.next = null;
        } // end constructor

        private Node(K searchKey, V dataValue, Node nextNode) {
            this.key = searchKey;
            this.value = dataValue;
            this.next = nextNode;
        }

        private K getKey() {
            return key;
        }

        private V getValue() {
            return value;
        }

        private void setValue(V newValue) {
            this.value = newValue;
        }

        private void setKey(K newKey) {
            this.key = newKey;
        }

        private Node getNextNode() {
            return next;
        }

        private void setNextNode(Node nextNode) {
            this.next = nextNode;
        }

    } // end class Node
}