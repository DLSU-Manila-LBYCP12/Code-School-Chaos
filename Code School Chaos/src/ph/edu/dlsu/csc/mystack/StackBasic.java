package ph.edu.dlsu.csc.mystack;



import ph.edu.dlsu.csc.myinterface.StackInterface;
import ph.edu.dlsu.csc.myexception.StackEmptyException;
import java.util.Arrays;

/*@author Patrick*/
public class StackBasic<E> implements StackInterface<E>{
    protected int maxSize;//1-based
    protected int DEF_SIZE=500;
    protected E[] stackArray;
    protected int top;//TOS,0-based, -1 empty, size=TOS+1
    
    //constructor
    public StackBasic(){
        maxSize = DEF_SIZE;
        stackArray = (E[])new Object[maxSize]; 
        top = -1;//-1 empty
    }
    public StackBasic(int s) {
        maxSize = s;
        stackArray = (E[])new Object[maxSize]; 
        top = -1;
    }
    //methods
    public void push(E j)/* throws StackFullException*/{
        if(isFull()){
            //throw new StackFullException("FULL!");
            resize();
        }
        top++;
        stackArray[top] = j;
    }
    public void pop() throws StackEmptyException{
        if(!isEmpty()){
            top--;
            //return stackArray[top+1];
        } else {
            throw new StackEmptyException("EMPTY!");
        }
    }
    public E top()  throws StackEmptyException{
        //System.out.println(stackArray[top]);
        if(!isEmpty()){
            return stackArray[top];
        } else {
            throw new StackEmptyException("EMPTY!");
        }
    }
    public boolean isEmpty() {
        return (top == -1);
    }
    public boolean isFull() {
        return (top == maxSize - 1);
    }
    
    
    
    public void resize(){
        resize(stackArray.length*3/2);
    }
    public void resize(int newSize){
        if(newSize>stackArray.length){
            int oldSize=stackArray.length;//1 based
            maxSize = newSize;//la lang
            stackArray=Arrays.copyOf(stackArray, newSize);
            System.out.println("Stack resized, from "+oldSize+" to "+maxSize);
        }
    }
}
