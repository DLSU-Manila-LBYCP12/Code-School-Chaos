package ph.edu.dlsu.csc.myarraylist;

/*
 * File: MyList.java
 * ------------------
 * This is the List ADT implementation
 */
import ph.edu.dlsu.csc.myexception.ListFullException;
import ph.edu.dlsu.csc.myexception.ListIndexOutOfBoundsException;
import java.util.Arrays;

public class MyList<E> {

    /// private data fields
    private int MAX_LIST = 10;        // max length of list
    private E[] items;                     // array of list items
    private int NumItems;                  // current size of list

    /// list items are already allocated above with T items[MAX_LIST]
    @SuppressWarnings("unchecked")
    public void createList() {
        items = (E[]) new Object[MAX_LIST];
        NumItems = 0;
    }

    public void add(int index, E item) throws ListIndexOutOfBoundsException, ListFullException {
        if (index > 0 && index <= NumItems + 1) {
            if (NumItems == MAX_LIST) {
                grow(item);
                throw new ListFullException("Growing the function");
            } else { // insert the element
                int j = NumItems;
                while (j >= index) {
                    items[j] = items[j - 1];
                    j--;
                }
                items[index - 1] = item;
                NumItems++;
            }
        } else {
            throw new ListIndexOutOfBoundsException("ERROR: List Index Out Of Bounds");
        }
    }

    public void add(E item) throws ListIndexOutOfBoundsException, ListFullException {
        if (NumItems == MAX_LIST) {
            throw new ListFullException("ERROR: List Already Full");
        } else {
            int j = NumItems;
            items[j] = item;
            NumItems++;
        }
    }

    public void remove(int index) throws ListIndexOutOfBoundsException {
        if (index > 0 && index <= NumItems) {
            for (int i = index; i < NumItems; i++) {
                items[i - 1] = items[i];
            }
            NumItems--;
        } else {
            throw new ListIndexOutOfBoundsException("ERROR: List Index Out Of Bounds");
        }
    }

    public boolean isEmpty() {
        return NumItems == 0;
    }

    public E get(int index) throws ListIndexOutOfBoundsException {
        if (index > 0 && index <= NumItems) {
            return items[index - 1];
        } else {
            throw new ListIndexOutOfBoundsException("ERROR: List Index Out Of Bounds");
        }
    }

    public int size() {
        return NumItems;
    }
    
    private void grow(E item){
        items = Arrays.copyOf(items, MAX_LIST*2);
        MAX_LIST = MAX_LIST*2;
        add(item);
    }

    public boolean contains(E item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == item) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(MyList list) {
        int count = 0;
        if (list.size() == items.length) {
            for (int i = 0; i < items.length; i++) {
                for (int j = 1; j <= list.size(); j++) {
                    if (items[i] == list.get(j)) {
                        count++;
                    }
                }
            }
        }
        return count == items.length;
    }

    public boolean containsAll(MyList list) {
        int counter = 0;
        for (int i = 0; i < items.length; i++) {
            for (int j = 1; j <= list.size(); j++) {
                if (items[i] == list.get(j)) {
                    counter++;
                }
            }
        }
        return counter == list.size();
    }

    public void addAll(MyList list) {
        if (list.isEmpty()) {
            return;
        } else {
            int index = NumItems + 1;
            for (int i = 0; i < list.size(); i++) {
                add(index + i, (E) list.get(i + 1));
            }
        }
    }

    public void clear() {
        NumItems = 0;
    }

    public void removeAll(MyList list) {
        if (list.size() < items.length) {
            for (int i = 1; i <= list.size(); i++) {
                for (int j = 0; j < items.length; j++) {
                    if (list.get(i) == items[j]) {
                        remove(j + 1);
                    }
                }
            }
        } else {
            for (int i = 0; i < items.length; i++) {
                for (int j = 1; j <= list.size(); j++) {
                    if (items[i] == list.get(j)) {
                        remove(i + 1);
                    }
                }
            }
        }
    }

    //mergeSort
    public void sort() {
        if (isEmpty()) {
            return;
        }
        int middle = (int) Math.floor(NumItems / 2);
        E[] lowerHalf = Arrays.copyOf(items, middle);
        E[] upperHalf = Arrays.copyOfRange(items, middle, NumItems);

        lowerHalf = sort(lowerHalf);
        upperHalf = sort(upperHalf);

        E[] merged = merge(lowerHalf, upperHalf);
        items = merged.clone();

        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }
    }

    private E[] sort(E[] array) {
        if (array.length == 1) {
            return array;
        }
        int middle = (int) Math.floor(array.length / 2);
        E[] lowerHalf = Arrays.copyOf(array, middle);
        E[] upperHalf = Arrays.copyOfRange(array, middle, array.length);

        lowerHalf = sort(lowerHalf);
        upperHalf = sort(upperHalf);

        E[] merged = merge(lowerHalf, upperHalf);
        return merged;
    }

    private E[] merge(E[] lower, E[] upper) {
        int i;
        int j;
        int k;
        i = j = k = 0;
        int max = upper.length + lower.length;
        E[] merged = (E[]) new Object[max];

        while (i < lower.length && j < upper.length) {
            if ((Double)lower[i] < (Double)upper[j]) {
                merged[k++] = lower[i++];
            } else {
                merged[k++] = upper[j++];
            }
        }
        while (i < lower.length) {
            merged[k++] = lower[i++];
        }
        while (j < upper.length) {
            merged[k++] = upper[j++];
        }
        return merged;
    }

}
