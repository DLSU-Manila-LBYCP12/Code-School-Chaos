/* © 2017 by Patrick Matthew Chan */
package ph.edu.dlsu.csc.mygraphalt;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class GNode<E>  implements Serializable {
    private static final long serialVersionUID = 1L;
    protected E item;
    protected LinkedList<GNode<E>> inNodes=new LinkedList<>();
    protected LinkedList<GNode<E>> outNodes=new LinkedList<>();
    
    
    //constructor
    public GNode(E newItem){
        item = newItem;
        inNodes.clear();
        outNodes.clear();
    }
    
    //other methods
    public void setItem(E newItem){
        item = newItem;
    }
    public E getItem(){
        return item;
    }
    
    //also handles the other node
    /**
     * connects a tail to the node (adds to outDegree)
     * (THIS)------->(n)
     */
    public boolean connectT(GNode<E> dest){
        if(outNodes.contains(dest)){
            System.out.println("already connected");
            return false;
        } else {
            LinkedList<GNode<E>> temp=dest.getInNodes(); 
            if(temp.contains(this)){
                System.out.println("Warning: other node is already half connected to this prior.");
                return outNodes.add(dest);
            } else {
                return outNodes.add(dest) && dest.addInNode(this);
            }
        }
    }
    /**
     * connects a head (ARROWHEAD) to the node (adds to inDegree)
     * (n)-------->(THIS)
     */
    public boolean connectH(GNode<E> src){
        if(inNodes.contains(src)){
            System.out.println("already connected");
            return false;
        } else {
            LinkedList<GNode<E>> temp=src.getOutNodes(); 
            if(temp.contains(this)){
                System.out.println("Warning: other node is already half connected to this prior.");
                return inNodes.add(src);
            } else {
                return inNodes.add(src) && src.addOutNode(this);
            }
        }
    }
    
    /**
     * disconnects a tail from the node (subtract from outDegree)
     * this node is the tail of the edge
     * (THIS)---/ /--->(n) 
     */
    public boolean discT(GNode<E> n){
        LinkedList<GNode<E>> temp=n.getInNodes(); 
        if(!temp.contains(this)){
            System.out.println("Warning: discT--other node is already half "
                    + "disconnected to this prior.\n Ignore if using disc()");
            return outNodes.remove(n);
        } else {
            return outNodes.remove(n) && n.remInNode(this);
        }
    }
    /**
     * disconnects a head from the node (subtract from inDegree)
     * this node is the head of the edge
     * (n)---/ /--->(THIS)
     */
    public boolean discH(GNode<E> n){
        LinkedList<GNode<E>> temp=n.getOutNodes(); 
        if(!temp.contains(this)){
            System.out.println("Warning: discH--other node is already half "
                    + "disconnected to this prior.\n Ignore if using disc()");
            return inNodes.remove(n);
        } else {
            return inNodes.remove(n) && n.remOutNode(this);
        }
    }
    public boolean disc(GNode<E> n){
        boolean a=discH(n);
        boolean b=discT(n);
        return a||b;
    }
    public void isolate(){
        while(!inNodes.isEmpty()){
            discH(inNodes.peek());
            //System.out.println("to remove (IN):"+inNodes.size());//debug
        }
        while(!outNodes.isEmpty()){
            discT(outNodes.peek());
            //System.out.println("to remove (OUT):"+outNodes.size());//debug
        }
    }
    
    /**
     * @return clone list of inNodes
     */
    public LinkedList<GNode<E>> getInNodes(){
        return (LinkedList<GNode<E>>) inNodes.clone();
    }
    /**
     * @return clone list of outNodes
     */
    public LinkedList<GNode<E>> getOutNodes(){
        return (LinkedList<GNode<E>>) outNodes.clone();
    }
    /**
     * one sided add
     */
    public boolean addInNode(GNode<E> n){
        return inNodes.add(n);
    }
    /**
     * one sided add
     */
    public boolean addOutNode(GNode<E> n){
        return outNodes.add(n);
    }
    /**
     * one sided remove
     */
    public boolean remInNode(GNode<E> n){
        return inNodes.remove(n);
    }
    /**
     * one sided remove
     */
    public boolean remOutNode(GNode<E> n){
        return outNodes.remove(n);
    }
    public int indexOfIn(GNode<E> n){
        return inNodes.indexOf(n);
    }
    public int indexOfOut(GNode<E> n){
        return outNodes.indexOf(n);
    }
    public boolean isInNode(GNode<E> n){
        return inNodes.contains(n);
    }
    public boolean isOutNode(GNode<E> n){
        return outNodes.contains(n);
    }
    public GNode<E> getVtxIn(int index){
        return inNodes.get(index);
    }
    public GNode<E> getVtxOut(int index){
        return outNodes.get(index);
    }
    
    public int indeg(){
        return inNodes.size();
    }
    public int outdeg(){
        return outNodes.size();
    }
    
    
    
    
    
    
    // <editor-fold defaultstate="collapsed" desc="toString">
    //++toString shortcut
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("item: "+item+"\n inNodes: [ ");
        Iterator<GNode<E>> it = inNodes.iterator();
        while(it.hasNext()){
            s.append(it.next().getItem());
            s.append(",");
        }
        s.append("\b]\n outNodes: [ ");
        Iterator<GNode<E>> it2 = outNodes.iterator();
        while(it2.hasNext()){
            s.append(it2.next().getItem());
            s.append(",");
        }
        s.append("\b]\n");
        return s.toString();
    }
    // </editor-fold>
}
