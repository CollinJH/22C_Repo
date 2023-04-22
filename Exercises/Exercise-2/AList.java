// Collin Hargreaves
// CIS 22C
// Professor Mirsaeid Abolghasemi
// 04.21.2023

import java.util.Arrays;

public class AList <T> implements ListInterface <T> {

    private T[] list;
    private int numberOfEntries;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    public AList() {

        this(DEFAULT_CAPACITY);
        
    }

    public AList(int initialCapacity) {

        // is inital capacity too small?
        if (initialCapacity < DEFAULT_CAPACITY) {
            initialCapacity = DEFAULT_CAPACITY;
        } else {
            checkCapacity(initialCapacity);
        }

        @SuppressWarnings("unchecked")
        T[] tempList = (T[]) new Object[initialCapacity + 1];
        list = tempList;

        numberOfEntries = 0;
        initialized = true;

    } // end constructor

    // Doubles the capacity of the array list if it is full.
    // Precondition: checkInitialization has been called.
    private void ensureCapacity() 
    {
        int capacity = list.length - 1;
        if (numberOfEntries >= capacity) 
        {
            int newCapacity = 2 * capacity;
            checkCapacity(newCapacity); // is capacity too big?
            list = Arrays.copyOf(list, newCapacity+1);
        }
    }

    // Makes room for a new entry at newPosition.
    // Precondition: 1 <= newPosition <= numberOfEntries + 1;
    // numberOfEntries is list's length before addition;
    // checkInitialization has been called.
    private void makeRoom(int newPosition) {
        assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);

        int newIndex = newPosition;
        int lastIndex = numberOfEntries;

        // Move each entry to next higher index, starting at end of
        // list and continuing until the entry at newIndex is moved
        for (int index = lastIndex; index >= newIndex; index--)
        list[index+1] = list[index];
    } // end makeRoom


    // Shifts entries that are beyond the entry to be removed to the
    // next lower position.
    // Precondition: 1 <= givenPosition < numberOfEntries;
    // numberOfEntries is list's length before removal;
    // checkInitialization has been called.
    private void removeGap(int givenPosition) {
        assert (givenPosition >= 1) && (givenPosition < numberOfEntries);

        int removedIndex = givenPosition;
        int lastIndex = numberOfEntries;

        for (int index = removedIndex; index < lastIndex; index++) {
            list[index] = list[index + 1];
        }
    } // end removeGap

    // Throws an exception if the object is not initalized
    private void checkInitialization() {
        if (!initialized) {
            throw new SecurityException("AList object not initialized properly");
        }
    } // end checkInitialization


    // Throws an exception if the clients requests too large a capacity
    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY) {
            throw new IllegalAccessError("Attempt to create a list " +
            "whose capacity exceeds " +
            "allowed maximum.");
        }
    } // end checkCapacity

    @Override
    public void add(T newEntry) {
        
        checkInitialization();
        list[numberOfEntries + 1] = newEntry;
        numberOfEntries++;
        ensureCapacity();
        
    }

    @Override
    public void add(int newPosition, T newEntry) {
        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1))
        {
            if (newPosition <= numberOfEntries)
                makeRoom(newPosition);
            list[newPosition] = newEntry;
            numberOfEntries++;
            ensureCapacity();
        }
        else
            throw new IndexOutOfBoundsException("Given position of add's new is out of bounds");
    }

    @Override
    public T remove(int givenPosition) {
        
        checkInitialization();
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            assert !isEmpty();
            T result = list[givenPosition]; // get entry to be removed

            // move entries toward entry to be removed
            // unless it is last in the list
            if (givenPosition < numberOfEntries) {
                removeGap(givenPosition);
            }

            numberOfEntries--;
            return result; // return reference to removed entry
        }
        
        else
            throw new IndexOutOfBoundsException("Illegal position given to remove operation");
    }

    @Override
    public void clear() {
        for (int i = 0; i < numberOfEntries; i++) {
            list[i] = null;
        }

        numberOfEntries = 0;
    }

    @Override
    public T replace(int givenPosition, T newEntry) {
        checkInitialization();
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            assert !isEmpty();
            T originalEntry = list[givenPosition];
            list[givenPosition] = newEntry;
            return originalEntry;
        } else
            throw new IndexOutOfBoundsException("Illegal position given to replace Operation");
    }

    @Override
    public T getEntry(int givenPosition) {
        
        checkInitialization();
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            return list[givenPosition];
        } else
            throw new IndexOutOfBoundsException("Illegal position given to getEntry Operation");
    }

    @Override
    public boolean contains(T anEntry) {
        checkInitialization();
        boolean found = false;
        int i = 1;

        while (!found && (i <= numberOfEntries))
        {
            if (anEntry.equals(list[i]))
                found = true;
            i++;
        }

        return found;
    }

    @Override
    public int getLength() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public T[] toArray() {
        
        checkInitialization();

        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];
        for (int i = 0; i < numberOfEntries; i++)
        {
            result[i] = list[i + 1];
        }

        return result;
    }

    


}
