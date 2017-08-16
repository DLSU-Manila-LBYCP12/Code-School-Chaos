package ph.edu.dlsu.csc.myarraygraph;

/* © 2017 by Patrick Matthew Chan */

//import java.lang.reflect.Field;//optional,for toString shortcut

import ph.edu.dlsu.csc.myexception.GraphFullException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import ph.edu.dlsu.csc.myqueuelist.MyQueueList;

/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class ArrayGraph<E> {
    //array implementation
    E nodeVals[];//node names (null means no node)
    boolean adj[][];//adjacency matrix adj[head][tail]
    double wt[][];//weights wt[head][tail]
    //dijkstra
    double dist[];//sync to node names
    E par[];//sync to node names
    
        
    public ArrayGraph(int size){//constructor
        nodeVals=(E[])new Object[size];//node names
        adj=new boolean[size][size];//adjacency matrix
        wt=new double[size][size];//weights
        dist=new double[size];
        par=(E[])new Object[size];
        
        for(int i=0;i<nodeVals.length;i++){
            nodeVals[i]=null;
        }
        for(int i=0;i<adj.length;i++){
            for(int j=0;j<adj[i].length;j++){
                adj[i][j]=false;
            }
        }
        for(int i=0;i<wt.length;i++){
            for(int j=0;j<wt[i].length;j++){
                wt[i][j]=0;
            }
        }
    }
    public ArrayGraph(){
        this(500);
    }
    
    //other methods
    /* Method: linSe(list,query) */
    /**
     * Does linear search.
     * 
     * @param arr array
     * @param query query (name)
     * @return -1 if not found, index if found (1 based, since list)
     */
    static <T> int linSe(T[] arr, T query){
        if(query==null){
            for(int i=0;i<arr.length;i++){
                if(arr[i]==null){
                    return i;
                }
            }
            return -1;
        } else {
            for(int i=0;i<arr.length;i++){
                if(arr[i]!=null && arr[i].equals(query)){
                    return i;
                }
            }
            return -1;
        }
    }
    
    
    
    
    public void addNode(E nodeValue){
        int i=linSe(nodeVals, null);
        if(i==-1){
            throw new GraphFullException("");
        } else {
            nodeVals[i]=nodeValue;
        }
    }
    public void delNode(E nodeValue){
        int i=linSe(nodeVals, nodeValue);
        if(i==-1){
            System.err.println("delNode: NODE NOT FOUND");
        } else {
            nodeVals[i]=null;
            for(int j=0;j<adj[i].length;j++){
                adj[i][j]=false;
                adj[j][i]=false;
            }
        }
    }
    
    public void connect(E src,E dest,int weight){
        int i1=linSe(nodeVals, src);
        int i2=linSe(nodeVals, dest);
        if(i1==-1){
            System.err.println("connect, Node1: NODE NOT FOUND");
        } else if(i2==-1){
            System.err.println("connect, Node2: NODE NOT FOUND");
        } else {
            adj[i1][i2]=true;
            wt[i1][i2]=weight;
        }
    }
    public void disconnect(E src,E dest){
        int i1=linSe(nodeVals, src);
        int i2=linSe(nodeVals, dest);
        if(i1==-1){
            System.err.println("disconnect, Node1: NODE NOT FOUND");
        } else if(i2==-1){
            System.err.println("disconnect, Node2: NODE NOT FOUND");
        } else {
            adj[i1][i2]=false;
            wt[i1][i2]=0;//la lang
        }
    }
    
    public double weight(E dest,E src){
        int i1=linSe(nodeVals, src);
        int i2=linSe(nodeVals, dest);
        if(i1==-1){
            System.err.println("weight, Node1: NODE NOT FOUND");
            return -999;
        } else if(i2==-1){
            System.err.println("weight, Node2: NODE NOT FOUND");
            return -999;
        } else {
            if(isEdge(src, dest)){
                return wt[i1][i2];
            } else {
                System.out.println("!!!!!!!!!!!!!!!!weight: not an edge:"+src+" and "+dest);
                return -999;
            }
        }
    }
    public boolean isEdge(E src,E dest){
        int i1=linSe(nodeVals, src);
        int i2=linSe(nodeVals, dest);
        if(i1==-1){
            System.err.println("isNode, Node1: NODE NOT FOUND in nodeVals");
            return false;
        } else if(i2==-1){
            System.err.println("isNode, Node2: NODE NOT FOUND in nodeVals");
            return false;
        } else {
            return adj[i1][i2];
        }
    }
    public boolean isNode(E nodeVal){
        return linSe(nodeVals, nodeVal)!=-1;
    }
    
    public ArrayList<E> adj(E src){//returns list of adjacent nodes (outdegree)
        ArrayList<E> ans=new ArrayList<>();
        int i=linSe(nodeVals, src);
        if(i==-1){
            System.err.println("adj: NODE NOT FOUND");
            return ans;//exception maybe
        } else {
            for(int j=0;j<adj[i].length;j++){
                if(adj[i][j]){//==true
                    ans.add(nodeVals[j]);
                }
            }
            return ans;
        }
    }
    public ArrayList<E> V(){
        ArrayList<E> ans=new ArrayList<>();
        for(int i=0;i<nodeVals.length;i++){
            if(nodeVals[i]!=null){
                ans.add(nodeVals[i]);
            }
        }
        return ans;
    }
    
    
    
    //////////////////DIJKSTRA//////////////////////////////
    public double getDdist(E vtx){
        int i=linSe(nodeVals, vtx);
        if(i==-1){
            System.err.println("getDdist: NODE NOT FOUND");
            return -999;//exception maybe
        } else {
            return dist[i];
        }
    }
    public void setDdist(E src,double value){
        int i=linSe(nodeVals, src);
        if(i==-1){
            System.err.println("getDdist: NODE NOT FOUND");
            //exception maybe
        } else {
            //System.out.println(dist[i]+"dist[i] value");
            dist[i]=value;
        }
    }
    public E getDpar(E vtx){
        int i=linSe(nodeVals, vtx);
        if(i==-1){
            System.err.println("getDdist: NODE NOT FOUND");
            return null;//exception maybe
        } else {
            return par[i];
        }
    }
    public void setDpar(E src,E value){
        int i=linSe(nodeVals, src);
        if(i==-1){
            System.err.println("getDdist: NODE NOT FOUND");
            //exception maybe
        } else {
            par[i]=value;
        }
    }
    
    
    
    /////////////////////////////////////////////////////////
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(int i=0;i<nodeVals.length;i++){
            if(nodeVals[i]!=null){
                s.append("Node: "+nodeVals[i])
                        .append(System.getProperty("line.separator"));
                        //.append("Adjacencies(out) => Weights:\n");
                for(int j=0;j<adj[i].length;j++){
                    if(adj[i][j]){//==true
                        s.append(nodeVals[j]+" => "+wt[i][j])
                                .append(System.getProperty("line.separator"));
                    }
                }
                //s.append("node DONE!\n\n");
                s.append("-----------------\n");
            }
        }
        return s.toString();
    }
    
    
    
    
    
    
    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////
    public static <E> void DIJKSTRA(ArrayGraph<E> G,E a){
        ArrayList<E> GV=G.V();
        ArrayList<E> C=new ArrayList<>();
        for(E v:GV){//for each vertex v in G.V()
            G.setDdist(v,Double.MAX_VALUE);
            G.setDpar(v, null);
            C.add(v);
        }
        //System.out.println("loop1 done");
        G.setDdist(a, 0);
        while(!C.isEmpty()){
            E curNode=findMinimumDist(C, G);
            for(E v:G.adj(curNode)){
                if(G.weight(v, curNode)+G.getDdist(curNode)<G.getDdist(v)){
                    //System.out.println(curNode+","+v+"wt:"+G.weight(v, curNode));
                    G.setDdist(v, G.weight(v, curNode)+G.getDdist(curNode));
                    G.setDpar(v, curNode);
                }
                //System.out.println("cheking adj of "+v+"[["+G.getDdist(v)+";"+G.getDpar(v));
            }
        }
    }
    public static <E> E findMinimumDist(ArrayList<E> C,ArrayGraph<E> G){
        E ans=C.get(0);
        for(int i=1;i<C.size();i++){
            if(G.getDdist(ans)>G.getDdist(C.get(i))){
                ans=C.get(i);
            }
        }
        C.remove(ans);
        //System.out.println("min="+ans);
        return ans;
    }
    
    
    public static <E> void DFS(ArrayGraph<E> G,E a){
        ArrayList<E> GV=G.V();
        ArrayList<E> Z=new ArrayList<>();
        Stack<E> S=new Stack<>();
        
        for(E v:GV){//for each vertex v in G.V()
            Z.add(v);
        }
        Z.remove(a);
        output(a);
        S.push(a);
        while(!S.isEmpty()){
            //System.out.println("Stack: "+S);
            E curNode=S.peek();
            E next=getNextPriorityNotInSet(G.adj(curNode), Z);
            if(next!=null){
                S.push(next);
                output(next);
            } else {
                S.pop();
            }
        }
    }
    public static <E> void output(E a){
        System.out.println("DFS node: "+a);
    }
    public static <E> E getNextPriorityNotInSet(ArrayList<E> adjacentNodes,ArrayList<E> unvisitedNodes){
        //well, since no criteria
        for(int i=0;i<adjacentNodes.size();i++){
            if(unvisitedNodes.contains(adjacentNodes.get(i))){//not in set
                //System.out.println("long function result: "+adjacentNodes.get(i));
                unvisitedNodes.remove(adjacentNodes.get(i));
                return adjacentNodes.get(i);
            }
        }
        //System.out.println("long function result: null");
        return null;
    }
    public static <E> void BFS(ArrayGraph<E> G,E a){
        ArrayList<E> GV=G.V();
        ArrayList<E> Z=new ArrayList<>();
        MyQueueList<E> Q=new MyQueueList<>();
        
        for(E v:GV){//for each vertex v in G.V()
            Z.add(v);
        }
        Z.remove(a);
        output(a);
        Q.push(a);
        while(!Q.isEmpty()){
            //System.out.println("Stack: "+S);
            E curNode=Q.front();
            E next=getNextPriorityNotInSet(G.adj(curNode), Z);
            if(next!=null){
                Q.push(next);
                output(next);
            } else {
                Q.pop();
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
