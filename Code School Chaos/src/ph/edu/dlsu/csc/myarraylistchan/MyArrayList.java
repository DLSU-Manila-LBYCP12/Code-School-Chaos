package ph.edu.dlsu.csc.myarraylistchan;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 * File: MyArrayList.java
 * ------------------
 * This is the List ADT implementation
 */


import ph.edu.dlsu.csc.myinterface.List;
import ph.edu.dlsu.csc.myexception.ListFullException;
import ph.edu.dlsu.csc.myexception.ListIndexOutOfBoundsException;
import acm.program.*;
import acm.util.*;
import java.util.Arrays;
import java.util.Collection;


public class MyArrayList<E> implements List<E>{

    /// private data fields
    private final int DEF_MAX = 100;
    private int MAX_LIST = DEF_MAX;     // max length of list
    private E[] items;                     // array of list items
    private int NumItems;                  // current size of list




    public MyArrayList(){
          items = (E[])new Object[MAX_LIST]; 
          NumItems = 0;
    }
    public MyArrayList(int MaxList){//RIP naming conventions haha for convenience
           MAX_LIST=MaxList;
          items = (E[])new Object[MAX_LIST]; 
          NumItems = 0;
    }

    /// list items are already allocated above with T items[MAX_LIST]
    @SuppressWarnings("unchecked")
    public void createList(){
          items = (E[])new Object[MAX_LIST]; 
          NumItems = 0;
    }

    public void createList(int MaxList){//RIP naming conventions haha for convenience
           MAX_LIST=MaxList;
          items = (E[])new Object[MAX_LIST]; 
          NumItems = 0;
    }

    public void add(int index, E item) throws ListIndexOutOfBoundsException, ListFullException{
         if ( index > 0 && index <= NumItems + 1){
              if (isFull()){
                   throw new ListFullException("ERROR: List Already Full"); 
              }  
              else { // insert the element
                   int j = NumItems;
                   while(j >= index){
                       items[j] = items[j - 1];
                       j--; 
                   }
                   items[index-1] = item; 
                   NumItems++;
              }
         }
         else
           throw new ListIndexOutOfBoundsException("ERROR: List Index Out Of Bounds");
    } 

    public void remove(int index) throws ListIndexOutOfBoundsException{
          if ( index > 0 && index <= NumItems){
                    for(int i = index; i < NumItems; i++){
                        items[i-1] = items[i];
                    }
                    NumItems--;
              }
          else
            throw new ListIndexOutOfBoundsException("ERROR: List Index Out Of Bounds");
    } 

    public boolean isEmpty(){
           return NumItems == 0;          
    }
    
    public boolean isFull(){
           return NumItems == MAX_LIST;         
    }

    public E get(int index) throws ListIndexOutOfBoundsException{
         if ( index > 0 && index <= NumItems){
             return items[index-1];
         }
          else
            throw new ListIndexOutOfBoundsException("ERROR: List Index Out Of Bounds");
    }

    public int size(){
           return NumItems;
    }
    
    
    public void resize(){
        resize(items.length*3/2);
    }
    public void resize(int newSize){
        if(newSize>items.length){
            int oldSize=items.length;//1 based
            MAX_LIST = newSize;//la lang
            items=Arrays.copyOf(items, newSize);
            System.out.println("List resized, from "+oldSize+" to "+MAX_LIST);
        }
    }
    
    ///////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////
    
    public boolean contains(Object o){
        for(int i=1;i<=size();i++){
            E item=get(i);
            if(item.equals(o)){
                return true;
            }
        }
        return false;
    }
    public boolean add(E e){
        try{
            add(NumItems+1,e);
        }catch (ListFullException err){
            err.printStackTrace();
            return false;
        } catch (ListIndexOutOfBoundsException err2){
            err2.printStackTrace();
            return false;
        }
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
    public boolean containsAll(MyArrayList<E> c){
        for(int i=1;i<=c.size();i++){
            E item=c.get(i);
            if(!contains(item)){
                return false;
            }
        }
        return true;
    }
    public boolean addAll(MyArrayList<E> c){
        
        for(int i=1;i<=c.size();i++){
            E item=c.get(i);
            add(item);
        }
        return true;
    }
    public boolean removeAll(MyArrayList<E> c){
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
        NumItems=0;
    }
    public boolean equals(Object o){
        if(o instanceof MyArrayList){
            MyArrayList comp=(MyArrayList)o;
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
    public MyArrayList<E> intersection(MyArrayList<E> comp){
        MyArrayList<E> ans=new MyArrayList<>(size()>comp.size()?
                size()+10:comp.size()+10);//ternary operator
        for(int i=0;i<size();i++){
            E item=items[i];
            if(comp.contains(item)){
                ans.add(item);
            }
        }
        return ans;
    }
    public String toString(){
        return items.toString();
    }
}