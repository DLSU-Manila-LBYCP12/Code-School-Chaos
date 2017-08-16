package ph.edu.dlsu.csc.myqueuelist;

import java.util.Collection;
import ph.edu.dlsu.csc.mylinkedlist.MyLinkedList;

/* © 2017 by Patrick Matthew Chan */

/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class MyQueueList<E> extends QueueListBasic<E>{
    //constructor
    public MyQueueList(){
        super();
    }
    
    //addtl methods
    public void clear(){
        items.clear();//reset
    }
    
    public String toString(){
        return new String("Q:"+items.toString());
    }
    
    
    
    
    
    
    //////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////
    public MyQueueList<E> copyOf(){
        MyQueueList<E> tempQ=new MyQueueList<>();
        for(int i=1;i<=items.size();i++){
            tempQ.push(items.get(i));
        }
        return tempQ;
    }
    
}
