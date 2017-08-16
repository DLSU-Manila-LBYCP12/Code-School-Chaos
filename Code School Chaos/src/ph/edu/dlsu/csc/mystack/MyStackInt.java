/* © 2017 by Patrick Matthew Chan */
package ph.edu.dlsu.csc.mystack;
//import java.lang.reflect.Field;//optional,for toString shortcut
/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class MyStackInt extends MyStack<Integer> {
    
        
    public MyStackInt(){//constructor
        super();
    }
    public MyStackInt(int MaxList){
        super(MaxList);
    }
    
    //other methods
    //highest is TOS
    public void sort(){
        MyStackInt temp=mergeSort(this);
        clear();
        MyStackInt temp2=new MyStackInt(temp.size()+10);
        while(!temp.isEmpty()){
            temp2.push(temp.top());
            temp.pop();
        }
        while(!temp2.isEmpty()){
            push(temp2.top());
            temp2.pop();
        }
    }
    
    public MyStackInt mergeSort(MyStackInt input){
        if(input.size()<=0){
            throw new IllegalStateException("Empty");
        } else if (input.size()==1){
            return input;
        } else {
            int half=(int)Math.floor(input.size()/2);
            //System.out.println("half = " + half);
            MyStackInt low=new MyStackInt(half+10);
            MyStackInt high=new MyStackInt(half+10);
            for(int i=1;i<=half;i++){
                low.push(input.top());//System.out.println("input.top()[low] = " + input.top());
                input.pop();
            }//System.out.println("input.size() = " + input.size());
            while(!input.isEmpty()){
                high.push(input.top());//System.out.println("input.top()[high] = " + input.top());
                input.pop();
            }//System.out.println("high.size() = " + high.size());
            low=mergeSort(low);
            high=mergeSort(high);//System.out.println("iter done");
            return merge(low,high);
        }
    }
    private MyStackInt merge(MyStackInt A,MyStackInt B){
        MyStackInt AS=new MyStackInt();
        MyStackInt BS=new MyStackInt();
        while(!A.isEmpty()){
            AS.push(A.top());
            A.pop();
        }
        while(!B.isEmpty()){
            BS.push(B.top());
            B.pop();
        }
        MyStackInt result=new MyStackInt(AS.size()+BS.size()+10);
        while(!AS.isEmpty() && !BS.isEmpty()){
            //System.out.println("A.top() = " + A.top());
            //System.out.println("B.top() = " + B.top());
            if(AS.top()>BS.top()){
                result.push(BS.top());
                BS.pop();
            } else {
                result.push(AS.top());
                AS.pop();
            }
        }
        //left
        while(!AS.isEmpty()){
            result.push(AS.top());
            AS.pop();
        }
        while(!BS.isEmpty()){
            result.push(BS.top());
            BS.pop();
        }
        MyStack<Integer> aaa=result.copyOf();
        while(!aaa.isEmpty()){
            System.out.println("aaa.top() = " + aaa.top());
            aaa.pop();
        }
        return result;
    }
    
    
    
    // <editor-fold defaultstate="collapsed" desc="toString shortcut">
    /*//++toString shortcut
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Field f: getClass().getDeclaredFields()) {
            try {
            result
            .append(f.getName())
            .append(" : ")
            .append(f.get(this))
            .append(System.getProperty("line.separator"));
            }
            catch (IllegalStateException ise) {
                result
                .append(f.getName())
                .append(" : ")
                .append("[cannot retrieve value]")
                .append(System.getProperty("line.separator"));
            }
            // nope
            catch (IllegalAccessException iae) {}
        }
        return result.toString();
    }*/
    // </editor-fold>
}
