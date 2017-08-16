/* © 2017 by Patrick Matthew Chan */
package ph.edu.dlsu.csc.mygraphalt;
//import java.lang.reflect.Field;//optional,for toString shortcut

import ph.edu.dlsu.csc.mytree.Tree;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class GraphGeneral<NodeItemType> extends EdgeMap implements Serializable{
    private static final long serialVersionUID = 1L;
    protected LinkedList<GNode<NodeItemType>> nodes;
    //EdgeMap m;
    protected double accessCount=0;//+0.1 for findNode, +1 for node adding & etc.
            
    
    public GraphGeneral(){//constructor
        //edgemap
        srcs=new LinkedList<>();
        maps=new LinkedList<>();
        chkIntegrity("edgemap");
        
        nodes=new LinkedList<>();
        //m=new EdgeMap();
    }
    
    //other methods
    public GNode<NodeItemType> findNode(NodeItemType query){
        accessCount+=0.1;
        Iterator<GNode<NodeItemType>> i=nodes.iterator();
        while(i.hasNext()){
            GNode<NodeItemType> now=i.next();
            if(now.getItem().equals(query)){
                return now;
            }
        }
        return null;
    }
    public boolean isNode(NodeItemType query){
        return findNode(query)!=null;
    }
    
    public int size(){
        return nodes.size();
    }
    public List<GNode<NodeItemType>> getNodes(){
        accessCount+=0.1;
        return (List<GNode<NodeItemType>>) nodes.clone();
    }
    public GNode<NodeItemType> getVtx(int index){
        accessCount+=0.1;
        return nodes.get(index);
    }
    public GEdge getGEdge(GNode src,GNode dest){
        accessCount+=0.1;
        return this.getGEdge(src, dest);//edgemap
    }
    public int indexOf(GNode<NodeItemType> n){
        accessCount+=0.1;
        return nodes.indexOf(n);
    }
    public void clear(){
        accessCount++;
        nodes.clear();
        this.clear();//edgemap
    }
    
    public boolean add(NodeItemType nodeItem){
        accessCount++;
        if(findNode(nodeItem)!=null){//no duplicates allowed
            return false;
        }
        return nodes.add(new GNode<>(nodeItem));
    }
    public boolean add(GNode<NodeItemType> n){
        accessCount++;
        if(nodes.contains(n)){//no duplicates allowed
            return false;
        }
        return nodes.add(n);
    }
    public boolean add(int index,GNode<NodeItemType> n){
        accessCount++;
        if(nodes.contains(n)){//no duplicates allowed
            return false;
        }
        nodes.add(index, n);
        return true;
    }
    public boolean remove (NodeItemType nodeItem){
        accessCount++;
        GNode<NodeItemType> n=findNode(nodeItem);
        if(n==null){
            System.out.println("node not found");
            return false;
        } else {
            return remove(n);
        }
    }
    public boolean remove(GNode<NodeItemType> n){
        accessCount++;
        n.isolate();
        this.isolate(n);//edgemap
        return nodes.remove(n);
    }
    public boolean remove(int i){
        accessCount++;
        return remove(nodes.get(i));
    }
    
    public boolean connect(GNode<NodeItemType> origin,GNode<NodeItemType> endpt){
        accessCount++;
        if(origin.connectT(endpt)){//automatic
            this.modEdge(origin, endpt);//edgemap
            return true;
        }
        return false;
    }
    /**
     * IMPT: only use this if there are NO DUPLICATES in your data set
     * @param origin
     * @param endpt
     * @return 
     */
    public boolean connect(NodeItemType origin,NodeItemType endpt){
        GNode<NodeItemType> or=findNode(origin);
        GNode<NodeItemType> en=findNode(endpt);
        if(or==null || en==null){
            return false;
        }
        return connect(or,en);
    }
    public boolean connect(int originIndex,int endptIndex){
        accessCount++;
        return connect(nodes.get(originIndex),nodes.get(endptIndex));
    }
    public boolean discOneEdge(GNode<NodeItemType> origin,GNode<NodeItemType> endpt){
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
    public boolean disc(GNode<NodeItemType> origin,GNode<NodeItemType> endpt){
        accessCount++;
        boolean a=discOneEdge(origin, endpt);
        boolean b=discOneEdge(endpt,origin);
        return a||b;
    }
    public boolean disc(int originIndex,int endptIndex){
        accessCount++;
        return disc(nodes.get(originIndex), nodes.get(endptIndex));
    }
    
    ///////////////////////////////////////////////////////////////////
    //||--||--||ooooooooooooooooo A L G O ooooooooooooooooo||--||--||//
    ///////////////////////////////////////////////////////////////////
    public Tree<GNode<NodeItemType>> Dijkstra(GNode<NodeItemType> source){
        Map<GNode<NodeItemType>,GNode<NodeItemType>> parentMap=new Map<>();
        Map<GNode<NodeItemType>,Integer> distMap=new Map<>();
        ArrayList<GNode<NodeItemType>> toVisit=new ArrayList<>(nodes.size());
        Tree<GNode<NodeItemType>> result=new Tree<>(source);
        ArrayList<Tree<GNode<NodeItemType>>> forest=new ArrayList<>();
        //init
        forest.add(result);
        for(GNode<NodeItemType> n:nodes){
            if(n.equals(source)){//src
                parentMap.add(n,null);
                distMap.add(n,0);
                toVisit.add(n);
            } else {//others
                parentMap.add(n,null);
                distMap.add(n, Integer.MAX_VALUE);
                toVisit.add(n);
                //all orphans are added to the forest until they are adopted
                forest.add(new Tree<>(n));
            }
        }
        //body
        while(!toVisit.isEmpty()){
            GNode<NodeItemType> minNode=findMinDistDijk(toVisit, distMap);
            toVisit.remove(minNode);
            for(GNode<NodeItemType> u:minNode.getOutNodes()){
                if(toVisit.contains(u)){
                    if(distMap.getV(u)>distMap.getV(minNode)+wt(minNode, u)){
                        //(source will be first minNode, never the u)
                        //##tree 
                        //we look for former parent's tree
                        Tree<GNode<NodeItemType>> parentTree=
                                lookForTreeInForestOfForests(parentMap.getV(u),forest);
                        //get u's tree
                        Tree<GNode<NodeItemType>> uTree=
                                    lookForTreeInForestOfForests(u,forest);
                        //get minNode's tree
                        Tree<GNode<NodeItemType>> minNodeTree=
                                    lookForTreeInForestOfForests(minNode,forest);
                        if(parentTree==null){//orphan-->about to be adopted
                            forest.remove(uTree);
                        } else {//transfer of parenthood
                            //we disconnect old parent
                            parentTree.remChildByItem(u);
                        }                        
                        //connect new parent
                        minNodeTree.addChild(uTree,wt(minNode, u));
                        
                        //##map
                        distMap.setV(u,distMap.getV(minNode)+wt(minNode, u));
                        parentMap.setV(u, minNode);
                    }
                }
            }
        }
        return result;
    }
    
    private GNode<NodeItemType> findMinDistDijk(
            ArrayList<GNode<NodeItemType>> toVisit,
            Map<GNode<NodeItemType>,Integer> distMap){
        Integer min=distMap.getV(toVisit.get(0));
        GNode<NodeItemType> minNode=toVisit.get(0);
        
        for(GNode<NodeItemType> n:toVisit){
            if(min>distMap.getV(n)){
                min=distMap.getV(n);
                minNode=n;
            }
        }
        return minNode;
    }
    private Tree<GNode<NodeItemType>> lookForTreeInForestOfForests(
            GNode<NodeItemType> query,
            ArrayList<Tree<GNode<NodeItemType>>> forest){
        //we look for query's tree
        //Tree<GNode<NodeItemType>> queryTree=null;
        for(Tree<GNode<NodeItemType>> t:forest){
            Tree<GNode<NodeItemType>> tempo=t.preOrderSearch(query);
            if(tempo!=null){
                return tempo;
                //queryTree=tempo;
                //return queryTree;
            }
        }
        return null;//not found
    }
    
    
    
    // <editor-fold defaultstate="collapsed" desc="toString">
    //++toString
    @Override
    public String toString() {
        Iterator<GNode<NodeItemType>> it = nodes.iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            GNode<NodeItemType> e = it.next();
            sb.append(e);
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(";;").append(System.getProperty("line.separator"));
        }
    }
    // </editor-fold>
}