/* © 2017 by Patrick Matthew Chan */
package ph.edu.dlsu.csc.mytree;

import ph.edu.dlsu.csc.myexception.*;
import ph.edu.dlsu.csc.mygraphalt.GEdge;
import java.util.ArrayList;

/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class Tree<E>{
    //here, tree has trees as children
    public Tree<E> parent;
    public E root=null;
    //these two should be in sync
    private ArrayList<Tree<E>> children=new ArrayList<>();
    private ArrayList<GEdge> edges=new ArrayList<>();
        
    public Tree(E rootItem){//constructor
        root=rootItem;
        parent=null;
        children=new ArrayList<>();
        edges=new ArrayList<>();
        checkSync("Tree");
    }
    
    //other methods
    public int getNumChildren(){
        checkSync("getNumChildren");
        return children.size();
    }
    
    
    public void addChild(Tree<E> childTree,GEdge e){
        if(children.contains(childTree)){
            System.out.println("cannot have two connections "
                    + "to the same child!\nchild:"+childTree.root);
            throw new TreeOperationException("");
        } else if (childTree.equals(this)){
            System.out.println("cannot connect a tree to itself");
            checkSync("addChild");
            throw new TreeOperationException("");
        }
        children.add(childTree);
        edges.add(e);
        childTree.setParent(this);
    }
    public void addChild(Tree<E> childTree, int weight, String desc){
        addChild(childTree, new GEdge(weight, desc));
    }
    public void addChild(Tree<E> childTree, int weight){
        addChild(childTree, new GEdge(weight));
    }
    public void addChild(Tree<E> childTree,  String desc){
        addChild(childTree, new GEdge(desc));
    }
    public void addChild(Tree<E> childTree){
        addChild(childTree, new GEdge());
    }
    public void addChild(E childItem,GEdge e){
        addChild(new Tree<E>(childItem), e);
    }
    public void addChild(E childItem, int weight, String desc){
        addChild(new Tree<E>(childItem), new GEdge(weight, desc));
    }
    public void addChild(E childItem, int weight){
        addChild(new Tree<E>(childItem), new GEdge(weight));
    }
    public void addChild(E childItem,  String desc){
        addChild(new Tree<E>(childItem), new GEdge(desc));
    }
    public void addChild(E childItem){
        addChild(new Tree<E>(childItem), new GEdge());
    }
    
    
    //false if not found
    public boolean remChild(Tree<E> childTree){
        int i=children.indexOf(childTree);
        if(i==-1){
            System.out.println("remove: child not found");
            checkSync("remChild");
            return false;
        } else {
            children.remove(i);
            edges.remove(i);
            checkSync("remChild");
            return true;
        }
    }
    public Tree<E> remChildByIndex(int index){
        Tree<E> temp=null;
        try{
            temp=children.remove(index);
            edges.remove(index);
        } catch (IndexOutOfBoundsException e){
            System.out.println("incorrect index, removal failed.");
            e.printStackTrace();
        }
        checkSync("remChildByIndex");
        return temp;
    }
    public Tree<E> remChildByItem(E childItem){
        int index=indexOfChildItem(childItem);
        if(index==-1){
            System.out.println("child item not found");
            checkSync("remChildByItem");
            return null;
        } else {
            return remChildByIndex(index);
        }
    }
    
    
    public int indexOfChildItem(E query){
        checkSync("indexOfChildItem");
        for(int i=0;i<children.size();i++){
            Tree<E> t=children.get(i);
            if(t.root.equals(query)){
                return i;
            }
        }
        return -1;
    }
    public int indexOf(Tree<E> query){
        checkSync("indexOf");
        return children.indexOf(query);
    }
    
    
    public boolean isChild(Tree<E> query){
        return indexOf(query)!=-1;
    }
    public boolean isChildByItem(E query){
        return indexOfChildItem(query)!=1;
    }
    
    
    public GEdge getGEdgeOfChild(Tree<E> child){
        int index=indexOf(child);
        if(index==-1){
            System.out.println("getGEdgeOfChild:not a child");
            checkSync("getGEdgeOfChild");
            return null;
        } else {
            checkSync("getGEdgeOfChild");
            return edges.get(index);
        }
    }
    public GEdge getGEdgeOfChildItem(E childItem){
        int index=indexOfChildItem(childItem);
        if(index==-1){
            System.out.println("getGEdgeOfChild:not a child");
            checkSync("getGEdgeOfChildItem");
            return null;
        } else {
            checkSync("getGEdgeOfChildItem");
            return edges.get(index);
        }
    }
    
    public Tree<E> getChild(int index){
        checkSync("getChild");
        return children.get(index);
    }
    public Tree<E> getChildByItem(E item){
        int index=indexOfChildItem(item);
        if(index==-1){
            System.out.println("getChildByItem:not a child");
            checkSync("getChildByItem");
            return null;
        } else {
            checkSync("getChildByItem");
            return children.get(index);
        }
    }
    
    public ArrayList<Tree<E>> getChildren(){
        ArrayList<Tree<E>> temp=new ArrayList<>((int)(1.5*children.size()));
        for(Tree<E> t:children){
            temp.add(t);
        }
            checkSync("getChildren");
        return temp;
    }
    
    public void setParent(Tree<E> parentTree){//only use this within the tree
        if(parentTree!=null){
            if(parentTree.isChild(this)){
                parent=parentTree;
            } else {
                checkSync("Tree");
                throw new TreeOperationException("parent in "
                        + "constructor does not have this as a child.");
            }
        } else {
            parent=null;
        }
        checkSync("Tree");
    }
    
    public Tree<E> getParent(){
        checkSync("getParent");
        return parent;
    }
    public boolean isOrphan(){
        checkSync("isOrphan");
        return parent==null;
    }
    
    
    ///////////////////////////////////////////////////////////////////
    //||--||--||ooooooooooooooooo A L G O ooooooooooooooooo||--||--||//
    ///////////////////////////////////////////////////////////////////
    public void preOrder(ArrayList<Tree<E>> output){
        output.add(this);
        for(Tree<E> t:children){
            t.preOrder(output);
        }
    }
    public void postOrder(ArrayList<Tree<E>> output){
        for(Tree<E> t:children){
            t.postOrder(output);
        }
        output.add(this);
    }
    //in order here uses 2nd passage
    public void inOrder(ArrayList<Tree<E>> output){
        children.get(0).inOrder(output);
        output.add(this);
        for(int i=1;i<children.size();i++){
            children.get(i).inOrder(output);
        }
    }
    
    
    public Tree<E> preOrderSearch(E query){
        ArrayList<Tree<E>> list=new ArrayList<>();
        preOrder(list);
        for(Tree<E> t:list){
            if(t.root.equals(query)){
                return t;
            }
        }
        return null; //not found
    }
    
    
    
    
    private void checkSync(String methodName){
        if(children.size()!=edges.size()){
            System.err.println("Sync Error");
            System.out.println("children.size() = " + children.size());
            System.out.println("edges.size() = " + edges.size());
            throw new TreeSyncException("last method call:"+methodName);
        }
    }
    private void checkSync(){
        if(children.size()!=edges.size()){
            System.err.println("Sync Error");
            System.out.println("children.size() = " + children.size());
            System.out.println("edges.size() = " + edges.size());
            throw new TreeSyncException("last method call: <see stacktrace>");
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="toString">
    
    @Override
    public String toString() {
        StringBuilder s=new StringBuilder("[parent:");
        if(parent==null){
            s.append("(null)");
        } else {
            s.append(parent.root);
        }
        s.append("],(tree) Root:"+root+"\n");
        if(children.isEmpty()){
            s.append("THIS IS A LEAF.\n\n");
            return s.toString();
        } else {
            s.append("children:[ ");
            for(Tree<E> t:children){
                s.append(t.root+",");
            }
            s.append("\b]\nedges:[ ");
            for(GEdge e:edges){
                s.append(e.wt+"("+e.desc+");");
            }

            s.append("\b]\n\nSubtrees:\n");
            for(Tree<E> t:children){
                s.append(t+"\n");
            }
            return s.toString();
        }
    }
    // </editor-fold>
}
