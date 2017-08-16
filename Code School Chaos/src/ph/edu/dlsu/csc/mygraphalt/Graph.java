/* © 2017 by Patrick Matthew Chan */
package ph.edu.dlsu.csc.mygraphalt;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class Graph extends EdgeMap implements Serializable{
    private static final long serialVersionUID = 1L;
    LinkedList<GNode<Profile>> nodes;
    //EdgeMap m;
    public double accessCount=0;//+0.1 for findNode, +1 for node adding & etc.
            
    
    public Graph(){//constructor
        //edgemap
        srcs=new LinkedList<>();
        maps=new LinkedList<>();
        chkIntegrity("edgemap");
        
        nodes=new LinkedList<>();
        //m=new EdgeMap();
    }
    
    //other methods
    public GNode<Profile> findNode(Profile query){
        accessCount+=0.1;
        Iterator<GNode<Profile>> i=nodes.iterator();
        while(i.hasNext()){
            GNode<Profile> now=i.next();
            if(now.getItem().equals(query)){
                return now;
            }
        }
        return null;
    }
    public GNode<Profile> findNode(String name){
        accessCount+=0.1;
        Iterator<GNode<Profile>> i=nodes.iterator();
        while(i.hasNext()){
            GNode<Profile> now=i.next();
            if(now.getItem().getName().equals(name)){
                return now;
            }
        }
        return null;
    }
    public boolean isNode(Profile query){
        return findNode(query)!=null;
    }
    public boolean isNode(String name){
        return findNode(name)!=null;
    }
    
    public int size(){
        return nodes.size();
    }
    public List<Profile> getNodes(){
        accessCount+=0.1;
        return (List<Profile>) nodes.clone();
    }
    public GNode<Profile> getVtx(int index){
        accessCount+=0.1;
        return nodes.get(index);
    }
    public GEdge getGEdge(GNode src,GNode dest){
        accessCount+=0.1;
        return this.getGEdge(src, dest);//edgemap
    }
    public int indexOf(GNode<Profile> n){
        accessCount+=0.1;
        return nodes.indexOf(n);
    }
    public void clear(){
        accessCount++;
        nodes.clear();
        this.clear();//edgemap
    }
    
    public boolean add(Profile nodeItem){
        accessCount++;
        if(findNode(nodeItem)!=null){//no duplicates allowed
            return false;
        }
        return nodes.add(new GNode<>(nodeItem));
    }
    public boolean add(GNode<Profile> n){
        accessCount++;
        if(nodes.contains(n)){//no duplicates allowed
            return false;
        }
        return nodes.add(n);
    }
    public boolean add(int index,GNode<Profile> n){
        accessCount++;
        if(nodes.contains(n)){//no duplicates allowed
            return false;
        }
        nodes.add(index, n);
        return true;
    }
    public boolean remove (Profile nodeItem){
        accessCount++;
        GNode<Profile> n=findNode(nodeItem);
        if(n==null){
            System.out.println("node not found");
            return false;
        } else {
            return remove(n);
        }
    }
    public boolean remove(GNode<Profile> n){
        accessCount++;
        n.isolate();
        this.isolate(n);//edgemap
        return nodes.remove(n);
    }
    public boolean remove(int i){
        accessCount++;
        return remove(nodes.get(i));
    }
    
    public boolean connect(GNode<Profile> origin,GNode<Profile> endpt){
        accessCount++;
        if(origin.connectT(endpt)){//automatic
            this.modEdge(origin, endpt);//edgemap
            return true;
        }
        return false;
    }
    public boolean connect(int originIndex,int endptIndex){
        accessCount++;
        return connect(nodes.get(originIndex),nodes.get(endptIndex));
    }
    public boolean discOneEdge(GNode<Profile> origin,GNode<Profile> endpt){
        accessCount++;
        if(origin.discH(endpt)){//automatic
            this.remEdge(origin, endpt);//edgemap
            return true;
        }
        return false;
    }
    public boolean discOneEdge(int originIndex,int endptIndex){
        accessCount++;
        return discOneEdge(nodes.get(originIndex), nodes.get(endptIndex));
    }
    public boolean disc(GNode<Profile> origin,GNode<Profile> endpt){
        accessCount++;
        boolean a=discOneEdge(origin, endpt);
        boolean b=discOneEdge(endpt,origin);
        return a||b;
    }
    public boolean disc(int originIndex,int endptIndex){
        accessCount++;
        return disc(nodes.get(originIndex), nodes.get(endptIndex));
    }
    
    
    
    
    
    // <editor-fold defaultstate="collapsed" desc="toString">
    //++toString
    @Override
    public String toString() {
        Iterator<GNode<Profile>> it = nodes.iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            GNode<Profile> e = it.next();
            sb.append(e);
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(";;").append(System.getProperty("line.separator"));
        }
    }
    // </editor-fold>
}