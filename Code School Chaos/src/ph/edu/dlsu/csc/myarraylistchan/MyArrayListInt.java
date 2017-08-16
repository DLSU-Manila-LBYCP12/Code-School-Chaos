/* © 2017 by Patrick Matthew Chan */
package ph.edu.dlsu.csc.myarraylistchan;
//import java.lang.reflect.Field;//optional,for toString shortcut
/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class MyArrayListInt extends MyArrayList<Integer> {
    
        
    public MyArrayListInt(){//constructor
        super();
    }
    public MyArrayListInt(int MaxList){
        super(MaxList);
    }
    
    //other methods
    public void sort(){
        MyArrayListInt temp=mergeSort(this);
        clear();
        for(int i=1;i<=temp.size();i++){
            add(i,temp.get(i));
        }
    }
    public MyArrayListInt mergeSort(MyArrayListInt input){
        if(input.size()<=0){
            throw new IllegalStateException("Empty");
        } else if (input.size()==1){
            return input;
        } else {
            int half=(int)Math.floor(input.size()/2);
            MyArrayListInt low=new MyArrayListInt(half+10);
            MyArrayListInt high=new MyArrayListInt(half+10);
            for(int i=1;i<=half;i++){
                low.add(input.get(i));
            }
            for(int i=half+1;i<=input.size();i++){
                high.add(input.get(i));
            }
            low=mergeSort(low);
            high=mergeSort(high);
            return merge(low,high);
        }
    }
    private MyArrayListInt merge(MyArrayListInt A,MyArrayListInt B){
        int i,j;
        j=1;
        i=1;
        MyArrayListInt result=new MyArrayListInt(A.size()+B.size()+10);
        while(i<=A.size() && j<=B.size()){
            if(A.get(i)>B.get(j)){
                result.add(B.get(j));
                j++;
            } else {
                result.add(A.get(i));
                i++;
            }
        }
        //left
        while(i<=A.size()){
            result.add(A.get(i));
            i++;
        }
        while(j<=B.size()){
            result.add(B.get(j));
            j++;
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
