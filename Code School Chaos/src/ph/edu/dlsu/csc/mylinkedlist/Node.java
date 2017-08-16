package ph.edu.dlsu.csc.mylinkedlist;


public class Node<E>{
   private E item;
   private Node<E> next;

   public Node(E newItem, Node<E> nextNode){
         item = newItem; // DATA
         next = nextNode; //POINTER/LINK
   }

   public Node(E newItem){
         item = newItem;
         next = null;
   }

   public void setItem(E newItem){
          item = newItem;
   }

   public E getItem(){
          return item;
   }
 
   public void setNext(Node<E> nextNode){
          next = nextNode;
   }

   public Node<E> getNext(){
          return next;
   }
}// end class Node








