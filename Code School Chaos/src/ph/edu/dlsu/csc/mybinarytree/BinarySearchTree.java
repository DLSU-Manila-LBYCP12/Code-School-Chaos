/* © 2017 by Patrick Matthew Chan */
package ph.edu.dlsu.csc.mybinarytree;
//import java.lang.reflect.Field;//optional,for toString shortcut
/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class BinarySearchTree {
    public double root;
    public BinarySearchTree left=null;
    public BinarySearchTree right=null;
        
    public BinarySearchTree(double rootValue){//constructor
       root=rootValue;
       left=null;
       right=null;
    }
    
    //other methods
    public void add(double entry){
        if(entry<=root){
            if(left==null){
                left=new BinarySearchTree(entry);
            } else {
                left.add(entry);
            }
        } else {
            if(right==null){
                right=new BinarySearchTree(entry);
            } else {
                right.add(entry);
            }
        }
    }
    public void add(double... entries){
        for(double a:entries){
            add(a);
        }
    }
    
    public BinarySearchTree search(double query){
        if(query==root){
            return this;
        } else if (query<root){
            if(left==null){
                return null;
            } else {
                return left.search(query);
            }
        } else {
            if(right==null){
                return null;
            } else {
                return right.search(query);
            }
        }
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
