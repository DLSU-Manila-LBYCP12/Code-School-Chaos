/* © 2017 by Patrick Matthew Chan */
package ph.edu.dlsu.csc.mygraphalt;
//import java.lang.reflect.Field;//optional,for toString shortcut

import ph.edu.dlsu.csc.myexception.EdgeMapSyncException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import ph.edu.dlsu.csc.myexception.NonExistentEdgeException;

/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class EdgeMap implements Serializable {
    protected static final long serialVersionUID = 2L;
    protected LinkedList<GNode> srcs=new LinkedList<>();
    protected LinkedList<Map<GNode,GEdge>> maps=new LinkedList<>();
    
        
    public EdgeMap(){//constructor
        srcs=new LinkedList<>();
        maps=new LinkedList<>();
        chkIntegrity("edgemap");
    }
    
    //other methods
    public void clear(){
        srcs.clear();
        maps.clear();
    }
    
    /**
     * creates an edge if it does not exist, and modifies it if it does. (in map)
     * @param src head node
     * @param dest tail node
     * @param wt weight
     * @param desc description
     * @return true if edge already exists prior to modification, false
     *  if it creates a new edge (in map)
     */
    public boolean modEdge(GNode src,GNode dest,int wt,String desc){
        int i=srcs.indexOf(src);
        if(i==-1){
            srcs.add(src);
            Map<GNode,GEdge> temp=new Map();
            temp.add(dest,new GEdge(wt, desc));
            maps.add(temp);
            chkIntegrity("modedge");
            return false;
        } else {
            Map<GNode,GEdge> curmap=maps.get(i);
            int j=curmap.indexOfK(dest);
            if(j==-1){
                curmap.add(dest,new GEdge(wt, desc));
                chkIntegrity("modedge");
                return false;
            } else {//edge already exists
                GEdge curedge=curmap.getV(dest);
                curedge.desc=desc;
                curedge.wt=wt;
                chkIntegrity("modedge");
                return true;
            }
        }
    }
    public boolean modEdge(GNode src,GNode dest,int wt){
        String d="";
        try{
            d=desc(src, dest);
        } catch (NonExistentEdgeException e){}
        chkIntegrity("modedge");
        return modEdge(src, dest, wt, d);
    }
    public boolean modEdge(GNode src,GNode dest,String desc){
        int i=1;
        try{
            i=wt(src, dest);
        } catch (NonExistentEdgeException e){}
        chkIntegrity("modedge");
        return modEdge(src, dest, i, desc);
    }
    public boolean modEdge(GNode src,GNode dest){//basically add edge if not exist
        if(isEdge(src, dest)){
            chkIntegrity("modedge");
            return false;
        } else {
            chkIntegrity("modedge");
            return modEdge(src, dest, 1, "");
        }
    }
    
    public boolean remEdge(GNode src, GNode dest){
        int i=srcs.indexOf(src);
        if(i==-1){
            chkIntegrity("remedge");
            return false;
        } else {
            Map<GNode,GEdge> curmap=maps.get(i);
            int j=curmap.indexOfK(dest);
            if(j==-1){
                chkIntegrity("remedge");
                return false;
            } else {//edge already exists
                curmap.removeByK(dest);
                if(curmap.isEmpty()){
                    maps.remove(curmap);
                    srcs.remove(src);
                }
                chkIntegrity("remedge");
                return true;
            }
        }
    }
    public void isolate(GNode n){
        int i=srcs.indexOf(n);
        if(i!=-1){
            maps.remove(i);
            srcs.remove(i);
        }
        
        for(int j=0;j<maps.size();j++){
            maps.get(j).removeByK(n);
        }
        
        chkIntegrity("isolate");
    }
    
    public boolean isEdge(GNode src,GNode dest){
        int i=srcs.indexOf(src);
        if(i==-1){
            chkIntegrity("isedge");
            return false;
        } else {
            Map<GNode,GEdge> curmap=maps.get(i);
            int j=curmap.indexOfK(dest);
            if(j==-1){
                chkIntegrity("isedge");
                return false;
            } else {//edge already exists
                chkIntegrity("isedge");
                return true;
            }
        }
    }
    public GEdge getGEdge(GNode src,GNode dest){
        int i=srcs.indexOf(src);
        if(i==-1){
            chkIntegrity("getGEdge");
            return null;
        } else {
            Map<GNode,GEdge> curmap=maps.get(i);
            int j=curmap.indexOfK(dest);
            if(j==-1){
                chkIntegrity("getGEdge");
                return null;
            } else {//edge already exists
                chkIntegrity("getGEdge");
                return curmap.getV(dest);
            }
        }
    }
    
    public int wt(GNode src,GNode dest){
        chkIntegrity("wt");
        if(isEdge(src, dest)){
            return maps.get(srcs.indexOf(src)).getV(dest).wt;
        } else {
            throw new NonExistentEdgeException("wt method");
        }
    }
    public String desc(GNode src,GNode dest){
        chkIntegrity("desc");
        if(isEdge(src, dest)){
            return maps.get(srcs.indexOf(src)).getV(dest).desc;
        } else {
            //return null;//comment out, so that value can be null
            throw new NonExistentEdgeException("desc method");
        }
    }
    
    
    /*this is based on map, so no edit here yet
    public int indexOfK(K key){
        chkIntegrity("indexofk");
        return keys.indexOf(key);
    }
    public int indexOfV(V val){
        chkIntegrity("indexofv");
        return values.indexOf(val);
    }*/
    
    public int numEdges(){
        chkIntegrity("numEdges");
        return srcs.size();
    }
    public boolean isEmpty(){
        chkIntegrity("isempty");
        return numEdges()==0;
    }
    
    
    protected final void chkIntegrity(String mthdName){
        if(srcs.size()!=maps.size()){
            throw new EdgeMapSyncException("list sizes unsycnhronized:\n"
                    +"srcs:"+ srcs.size()+"maps:"+maps.size()+
                    "\n last method: "+mthdName+"\n");
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
