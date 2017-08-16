/* © 2017 by Patrick Matthew Chan */
package ph.edu.dlsu.csc.mybinarytree;
//import java.lang.reflect.Field;//optional,for toString shortcut
/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class BinarySearchTreeKey<E> {//key is a double, data is <E>
    public double rootKey;
    public E rootData;
    public BinarySearchTreeKey<E> left=null;
    public BinarySearchTreeKey<E> right=null;
    

    public BinarySearchTreeKey(double rootKey, E rootData) {//constructor
        left=null;
        right=null;
        this.rootKey=rootKey;
        this.rootData=rootData;
    } 
    
    public void add(double key,E data){
        if(key<=rootKey){
            if(left==null){
                left=new BinarySearchTreeKey<E>(key,data);
            } else {
                left.add(key,data);
            }
        } else {
            if(right==null){
                right=new BinarySearchTreeKey<E>(key,data);
            } else {
                right.add(key,data);
            }
        }
    }
    
    public E search(double key){
        if(key==rootKey){
            return rootData;
        } else if (key<rootKey){
            if(left==null){
                return null;
            } else {
                return left.search(key);
            }
        } else {
            if(right==null){
                return null;
            } else {
                return right.search(key);
            }
        }
    }
    

    //other methods
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
