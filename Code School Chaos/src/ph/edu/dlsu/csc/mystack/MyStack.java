package ph.edu.dlsu.csc.mystack;

import java.util.Collection;
import ph.edu.dlsu.csc.myarraylistchan.MyArrayList;


public class MyStack<E> extends StackBasic<E>{
    //constructor
    public MyStack(){
        super();
    }
    public MyStack(int s) {
        super(s);
    }
    //additional methods
    public int size(){//1-based
        return top+1;
    }
    public void clear(){
        top=-1;
    }
    public void reverse(){
        E[] temp=(E[])new Object[stackArray.length];
        for(int i=0;i<size();i++){
            temp[size()-1-i]=stackArray[i];
        }
        stackArray=temp;
    }
    public MyStack<E> copyOf(){
        MyStack<E> temp=new MyStack<>(maxSize);
        for(int i=0;i<size();i++){
            temp.push(stackArray[i]);
        }
        return temp;
    }
    public int getMaxItems(){//++
        return maxSize;
    }
    
    
    
    ///////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////
    
    public boolean contains(Object o){
        for(int i=0;i<=top;i++){
            E item=stackArray[i];
            if(item.equals(o)){
                return true;
            }
        }
        return false;
    }
    public boolean containsAll(MyStack<E> c){
        MyStack<E> temp=c.copyOf();
        while(!temp.isEmpty()){
            E item=temp.top();
            temp.pop();
            if(!contains(item)){
                return false;
            }
        }
        return true;
    }
    public boolean addAll(MyStack<E> c){
        MyStack<E> temp=c.copyOf();
        while(!temp.isEmpty()){
            E item=temp.top();
            temp.pop();
            push(item);
        }
        return true;
    }
    public boolean removeAll(MyStack<E> c){
        boolean isChanged=false;
        MyArrayList<E> tempL1=new MyArrayList(size()+10);
        while(!isEmpty()){
            tempL1.add(top());
            pop();
        }
        MyArrayList<E> tempL2=new MyArrayList(c.size()+10);
        while(!c.isEmpty()){
            tempL2.add(c.top());
            c.pop();
        }
        isChanged=tempL1.removeAll(tempL2);
        //return to stack
        while(!tempL1.isEmpty()){
            push(tempL1.get(tempL1.size()));
            tempL1.remove(tempL1.size());
        }
        while(!tempL2.isEmpty()){
            c.push(tempL2.get(tempL2.size()));
            tempL2.remove(tempL2.size());
        }
        return isChanged;
    }
    public boolean equals(Object o){
        if(o instanceof MyStack){
            MyStack comp=(MyStack)o;
            if(comp.size()!=size()){
                return false;
            }
            MyStack A=copyOf();
            MyStack B=comp.copyOf();
            while(!A.isEmpty()){
                if(!A.top().equals(B.top())){
                    return false;
                }
                A.pop();
                B.pop();
            }
            return true;
        } else {
            return false;
        }
    }
    
    public MyStack<E> intersection(MyStack<E> comp){
        MyStack<E> ans=new MyStack<>(size()>comp.size()?
                size()+10:comp.size()+10);//ternary operator
        for(int i=0;i<=top;i++){
            E item=stackArray[i];
            if(comp.contains(item)){
                ans.push(item);
            }
        }
        return ans;
    }
}