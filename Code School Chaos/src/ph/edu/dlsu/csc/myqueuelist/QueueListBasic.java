package ph.edu.dlsu.csc.myqueuelist;


import ph.edu.dlsu.csc.mylinkedlist.MyLinkedList;
import ph.edu.dlsu.csc.myinterface.QueueInterface;
import ph.edu.dlsu.csc.myexception.QueueEmptyException;

/* © 2017 by Patrick Matthew Chan */

/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class QueueListBasic<E> implements QueueInterface<E>{
    protected MyLinkedList<E> items=new MyLinkedList<>();
    
    //constructor
    public QueueListBasic(){
        items.createList();
    }
    
    //other methods
    public void push(E item){
        //if(!isFull()){
            items.add(items.size()+1,item);
        //} else {
         //   throw new QueueFullException("Full");
        //}
    }
    public E front(){
        if(!isEmpty()){
            return items.get(1);
        } else {
            throw new QueueEmptyException("Empty");
        }
    }
    public E top(){
        return front();
    }
    public E rear(){
        if(!isEmpty()){
            return items.get(items.size());
        } else {
            throw new QueueEmptyException("Empty");
        }
    }
    public void pop(){
        if(!isEmpty()){
            items.remove(1);
        } else {
            throw new QueueEmptyException("Empty");
        }
    }
    public boolean isEmpty(){
        return items.isEmpty();
    }
    public int size(){
        return items.size();
    }
}
