package ph.edu.dlsu.csc.myinterface;


import ph.edu.dlsu.csc.myexception.StackFullException;
import ph.edu.dlsu.csc.myexception.StackEmptyException;


/*@author Patrick*/
public interface StackInterface<E> {
    //methods
    public void push(E j) throws StackFullException;
    public void pop() throws StackEmptyException;
    public E top()  throws StackEmptyException;
    public boolean isEmpty();
    public boolean isFull();
    //resize()
}