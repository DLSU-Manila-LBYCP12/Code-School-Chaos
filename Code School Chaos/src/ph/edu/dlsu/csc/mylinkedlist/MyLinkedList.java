package ph.edu.dlsu.csc.mylinkedlist;


import java.util.Collection;
import ph.edu.dlsu.csc.myinterface.List;
import ph.edu.dlsu.csc.myexception.ListIndexOutOfBoundsException;


public class MyLinkedList<E> implements List<E>{//1-based
    private Node<E> head;//first node
    private Node<E> tail;//last node
    private int size;
    private Node<E> curNode;//previously used node(in getNode),chance--quicker searches
    private int curNodeIndex;

    public MyLinkedList() {
        createList();
    }
    public void createList(){   
       size = 0;
       head = null;
       tail = null;
       curNode=null;
       curNodeIndex=0;
    } //end constructor
    
    //methods:
    /**
     * Locate a specified node in a linked list:
     * @param index index of the node
     * @throws ListIndexOutOfBoundsException if
     *  index not w/in current size, or empty.
     *  (i.e. if index>numItems or index<1).
     * @return the node of specified index
     */
    private Node<E> getNode(int index){
    // precondition: index is the number of the desired node
    // postcondition: returns a reference to the desired node.
    // postcondition: must also reassign curNode accordingly.
    //*****************************************************//
    //Note: curNode must always be assigned a value here to 
    //  prevent curnode indexing problems for add and delete.
    //*****************************************************//
        if(!isEmpty() && index>=1 && index<=size){//validity
            if(index==1){
                curNodeIndex=1;
                curNode=head;
                return head;
            } else if(index==size){
                //tail is useless as curnode
                curNodeIndex=0;
                curNode=null;//just to be safe,else prone to errors
                return tail;
            } else {
                //if curnode cannot be used
                if(curNode==null || curNodeIndex>index){
                    curNode=head;
                    curNodeIndex=1;
                }
                while(curNodeIndex<index){
                    curNode=curNode.getNext();
                    curNodeIndex++;
                }
                return curNode;
            }
        } else {
            throw new ListIndexOutOfBoundsException("find--error");
        }
    }
    
    //Checkers:
    public E get(int index) throws ListIndexOutOfBoundsException{
        try{
            return getNode(index).getItem();
        } catch (ListIndexOutOfBoundsException e){
            throw new ListIndexOutOfBoundsException("GET ERROR: List Index Out Of Bounds");
        }
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;          
    }

    //Modifiers:
    //ADD
    public void add(int index, E item) throws ListIndexOutOfBoundsException {
        if(index==1){//curNode always greater than this**
            addFirst(item);
        } else if(index==size+1){//curNode always less than this
            addLast(item);
        } else {//curnode auto reassigned to be 1 less than index
            Node<E> prev;
            try{
                prev=getNode(index-1);
            } catch (ListIndexOutOfBoundsException e){
                throw new ListIndexOutOfBoundsException("GET ERROR: List Index Out Of Bounds");
            }
            Node<E> newNode=new Node<>(item,prev.getNext());
            prev.setNext(newNode);
            size++;
        }
    }
    public void addFirst(E item){
        Node<E> newNode=new Node<>(item,head);
        head=newNode;
        curNodeIndex++;
        size++;
        if(size==1){//if list empty prior
            tail=head;
        }
    }
    public void addLast(E item){
        if(size==0){//if empty list
            addFirst(item);
        } else {
            Node<E> newNode=new Node(item,null);
            tail.setNext(newNode);
            tail=newNode;
            size++;
        }
    }
    //REMOVE
    public void remove(int index) throws ListIndexOutOfBoundsException{
        if(index==1){//curNode always greater than this**
            removeFirst();/////////////////////////^^
        } else {//curnode always reassigned to 1 less than index
            Node<E> prev;
            try{
                prev=getNode(index-1);
            } catch (ListIndexOutOfBoundsException e){
                throw new ListIndexOutOfBoundsException("GET ERROR: List Index Out Of Bounds");
            }
            prev.setNext(prev.getNext().getNext());
            if(prev.getNext()==null){
                tail=prev;
            }
            size--;
        }
    }
    public void removeFirst(){
        if(head==null){
            throw new ListIndexOutOfBoundsException("List Empty!");
        }
        head=head.getNext();
        curNodeIndex--;
        size--;
        if(size==0){//if list becomes empty
            tail=null;
        }
    }

    
    public boolean isFull() {
        return false;
    }
    
    
    
    
    public String toString(){
       StringBuilder a=new StringBuilder("[");
       
       for(int i=1;i<=size();i++){
           a.append(getNode(i).getItem().toString()+",");
       }
        a.append("\b]");
        return a.toString();
    }
    
    
    
    
    
    
    
    ///////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////
    
    public boolean contains(Object o){
        for(Node<E> cur=head;cur!=null;cur=cur.getNext()){
            if(cur.getItem().equals(o)){
                return true;
            }
        }
        return false;
    }
    public boolean add(E e){
        addLast(e);
        return true;
    }
    public boolean remove(Object o){
        for(int i=1;i<=size();i++){
            if(get(i).equals(o)){
                remove(i);
                return true;
            }
        }
        return false;
    }
    public boolean containsAll(MyLinkedList<E> c){
        for(int i=1;i<=c.size();i++){
            E item=c.get(i);
            if(!contains(item)){
                return false;
            }
        }
        return true;
    }
    public boolean addAll(MyLinkedList<E> c){
        for(int i=1;i<=c.size();i++){
            E item=c.get(i);
            add(item);
        }
        return true;
    }
    public boolean removeAll(MyLinkedList<E> c){
        boolean isChanged=false;
        for(int i=1;i<=c.size();i++){
            E item=c.get(i);
            if(remove(item)){
                isChanged=true;
            }
        }
        return isChanged;
    }
    public void clear(){
        size=curNodeIndex=0;
        head=tail=curNode=null;
    }
    public boolean equals(Object o){
        if(o instanceof MyLinkedList){
            MyLinkedList comp=(MyLinkedList)o;
            if(comp.size()!=size()){
                return false;
            }
            for(int i=1;i<=size();i++){
                if(!get(i).equals(comp.get(i))){
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
    public MyLinkedList<E> intersection(MyLinkedList<E> comp){
        MyLinkedList<E> ans=new MyLinkedList<>();
        for(Node<E> cur=head;cur!=null;cur=cur.getNext()){
            E item=cur.getItem();
            if(comp.contains(item)){
                ans.add(item);
            }
        }
        return ans;
    }
}//end class
