package ph.edu.dlsu.csc.myarraylist;

/*
 * File: MyList.java
 * ------------------
 * This is the List ADT implementation
 */
import ph.edu.dlsu.csc.myexception.ListFullException;
import ph.edu.dlsu.csc.myexception.ListIndexOutOfBoundsException;
import java.util.Arrays;

public class MyListInteger {

    /// private data fields
    private final int MAX_LIST = 10;        // max length of list
    private int[] items;                     // array of list items
    private int NumItems;                  // current size of list

    /// list items are already allocated above with T items[MAX_LIST]
    @SuppressWarnings("unchecked")
    public void createList() {
        items = new int[MAX_LIST];
        NumItems = 0;
    }

    public void add(int index, int item) throws ListIndexOutOfBoundsException, ListFullException {
        if (index > 0 && index <= NumItems + 1) {
            if (NumItems == MAX_LIST) {
                throw new ListFullException("ERROR: List Already Full");
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

    public int get(int index) throws ListIndexOutOfBoundsException {
        if (index > 0 && index <= NumItems) {
            return items[index - 1];
        } else {
            throw new ListIndexOutOfBoundsException("ERROR: List Index Out Of Bounds");
        }
    }

    public int size() {
        return NumItems;
    }

    public boolean contains(int item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == item) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(MyListInteger list) {
        boolean confirm = false;
        for (int i = 0; i < items.length; i++) {
            for (int j = 1; j <= list.size(); j++) {
                if (items[i] == list.get(j)) {
                    confirm = true;
                }
            }
        }
        return confirm;
    }

    public boolean containsAll(MyListInteger list) {
        int counter = 0;
        for (int i = 0; i < items.length; i++) {
            for (int j = 1; j <= list.size(); j++) {
                if (items[i] == list.get(j)) {
                    counter++;
                }
            }
        }
        return counter == items.length;
    }

    public void addAll(MyListInteger list) {
        if(list.isEmpty()){
            return;
        }
        else{
            int index = items.length;
            for(int i = 0; i < list.size(); i++){
                add(index+i,list.get(i));
            }
        }
    }

    public void clear() {
        NumItems = 0;
    }

    public void removeAll(MyListInteger list) {
        if (list.size() < items.length) {
            for (int i = 1; i <= list.size(); i++) {
                for (int j = 1; j <= items.length; j++) {
                    if (list.get(i) == items[j]) {
                        remove(j);
                    }
                }
            }
        } else {
            for (int i = 1; i <= items.length; i++) {
                for (int j = 1; j <= list.size(); j++) {
                    if (items[i] == list.get(j)) {
                        remove(i);
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
        int[] lowerHalf = Arrays.copyOf(items, middle);
        int[] upperHalf = Arrays.copyOfRange(items, middle, NumItems);

        lowerHalf = sort(lowerHalf);
        upperHalf = sort(upperHalf);

        int[] merged = merge(lowerHalf, upperHalf);
        items = merged.clone();

        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }
    }

    private int[] sort(int[] array) {
        if (array.length == 1) {
            return array;
        }
        int middle = (int) Math.floor(array.length / 2);
        int[] lowerHalf = Arrays.copyOf(array, middle);
        int[] upperHalf = Arrays.copyOfRange(array, middle, array.length);

        lowerHalf = sort(lowerHalf);
        upperHalf = sort(upperHalf);

        int[] merged = merge(lowerHalf, upperHalf);
        return merged;
    }

    private int[] merge(int[] lower, int[] upper) {
        int i;
        int j;
        int k;
        i = j = k = 0;
        int max = upper.length + lower.length;
        int[] merged = new int[max];

        while (i < lower.length && j < upper.length) {
            if (lower[i] < upper[j]) {
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

    public static void main(String[] args) {
        MyListInteger list = new MyListInteger();
        list.createList();
        list.add(1, 5);
        list.add(2, 9);
        list.add(3, 4);
        list.add(4, 6);
        MyListInteger list2 = new MyListInteger();
        list2.createList();
        list2.add(1, 6);
        list2.add(2, 2);
        list2.add(3, 8);
        if (list.equals(list2)) {
            System.out.println("there are intersections");
        }
        list.sort();
        list.removeAll(list2);
        System.out.println(list2.size());
        list.clear();
        if (list.isEmpty()) {
            System.out.println("List 1 is empty");
        }
    }
}
