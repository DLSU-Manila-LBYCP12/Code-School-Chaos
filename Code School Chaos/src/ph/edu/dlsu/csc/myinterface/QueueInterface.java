package ph.edu.dlsu.csc.myinterface;

/* © 2017 by Patrick Matthew Chan */


/**
 *
 * @author Patrick Matthew J. Chan
 */
public interface QueueInterface<E> {
    public void push(E item);
    public E front();
    public E rear();
    public void pop();
    public boolean isEmpty();
    //public boolean isFull();
    public int size();
}
