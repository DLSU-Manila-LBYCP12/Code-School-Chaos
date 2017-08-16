package ph.edu.dlsu.csc.mybinarytree;

/* © 2017 by Patrick Matthew Chan */

//import java.lang.reflect.Field;//optional,for toString shortcut

import ph.edu.dlsu.csc.mygraphalt.GEdge;
import ph.edu.dlsu.csc.myexception.TreeOperationException;
import java.util.ArrayList;

/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class BinaryTree<E> {
    private BinaryTree<E> parent; 
    private E root;
    private BinaryTree<E> left;
    private GEdge leftEdge;
    private BinaryTree<E> right;
    private GEdge rightEdge;
    
    
    public BinaryTree(E rootItem){//constructor
        root=rootItem;
        parent=null;
        left=null;
        leftEdge=null;
        right=null;
        rightEdge=null;
    }
    
    //other methods
    /**
     * only use this within the tree ADT.
     */
    public void setParent(BinaryTree<E> parentTree){
        if((parentTree.left!=null && parentTree.left.equals(this))||
                (parentTree.right!=null && parentTree.right.equals(this))){
            parent=parentTree;
        } else {
            throw new TreeOperationException("setParent(bin):this"
                    + " is not the parent's child");
        }
    }
    public BinaryTree<E> getParent(){
        return parent;
    }
    
    public void setLeft(BinaryTree<E> leftTree,GEdge edge){
        left=leftTree;
        leftEdge=edge;
        leftTree.setParent(this);
    }
    public void setLeft(BinaryTree<E> leftTree,int wt,String desc){
        setLeft(leftTree,new GEdge(wt, desc));
    }
    public void setLeft(BinaryTree<E> leftTree,int wt){
        setLeft(leftTree,new GEdge(wt));
    }
    public void setLeft(BinaryTree<E> leftTree,String desc){
        setLeft(leftTree,new GEdge( desc));
    }
    public void setLeft(BinaryTree<E> leftTree){
        setLeft(leftTree,new GEdge());
    }
    public void setLeft(E leftTreeItem,GEdge edge){
        left=new BinaryTree<>(leftTreeItem);
        leftEdge=edge;
        left.setParent(this);
    }
    public void setLeft(E leftTreeItem,int wt,String desc){
        setLeft(leftTreeItem,new GEdge(wt, desc));
    }
    public void setLeft(E leftTreeItem,String desc){
        setLeft(leftTreeItem,new GEdge(desc));
    }
    public void setLeft(E leftTreeItem,int wt){
        setLeft(leftTreeItem,new GEdge(wt));
    }
    public void setLeft(E leftTreeItem){
        setLeft(leftTreeItem,new GEdge());
    }
    public void setRight(BinaryTree<E> rightTree,GEdge edge){
        right=rightTree;
        rightEdge=edge;
        rightTree.setParent(this);
    }
    public void setRight(BinaryTree<E> rightTree,int wt,String desc){
        setRight(rightTree,new GEdge(wt, desc));
    }
    public void setRight(BinaryTree<E> rightTree,int wt){
        setRight(rightTree,new GEdge(wt));
    }
    public void setRight(BinaryTree<E> rightTree,String desc){
        setRight(rightTree,new GEdge( desc));
    }
    public void setRight(BinaryTree<E> rightTree){
        setRight(rightTree,new GEdge());
    }
    public void setRight(E rightTreeItem,GEdge edge){
        right=new BinaryTree<>(rightTreeItem);
        rightEdge=edge;
        right.setParent(this);
    }
    public void setRight(E rightTreeItem,int wt,String desc){
        setRight(rightTreeItem,new GEdge(wt, desc));
    }
    public void setRight(E rightTreeItem,String desc){
        setRight(rightTreeItem,new GEdge(desc));
    }
    public void setRight(E rightTreeItem,int wt){
        setRight(rightTreeItem,new GEdge(wt));
    }
    public void setRight(E rightTreeItem){
        setRight(rightTreeItem,new GEdge());
    }
    
    
    public BinaryTree<E> getLeft(){
        return left;
    }
    public GEdge getLeftEdge() {
        return leftEdge;
    }
    public BinaryTree<E> getRight() {
        return right;
    }
    public GEdge getRightEdge() {
        return rightEdge;
    }
    
    
    public void preOrder(ArrayList<BinaryTree<E>> output){
        output.add(this);
        left.preOrder(output);
        right.preOrder(output);
    }
    public void postOrder(ArrayList<BinaryTree<E>> output){
        left.postOrder(output);
        right.postOrder(output);
        output.add(this);
    }
    public void inOrder(ArrayList<BinaryTree<E>> output){
        left.inOrder(output);
        output.add(this);
        right.inOrder(output);
    }
    
    
    public BinaryTree<E> inOrderSearch(E query){
        ArrayList<BinaryTree<E>> list=new ArrayList<>();
        inOrder(list);
        for(BinaryTree<E> t:list){
            if(t.root.equals(query)){
                return t;
            }
        }
        return null; //not found
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
        s.append("],(BinTree) Root:"+root+"\n ");
        if(left!=null){
            s.append("left="+left.root+"("+leftEdge.wt+","+
                    leftEdge.desc+");");
        }
        if(right!=null){
            s.append("right="+right.root+"("+rightEdge.wt+","+
                    rightEdge.desc+") ");
        }
        if(left!=null){
            s.append("\b\nLEFT:\n"+left);
        }
        if(right!=null){
            s.append("\b\nRIGHT:\n"+right);
        }
        if(left==null && right==null){
            s.append("THIS IS A LEAF\n\n\n");
        }
        return s.toString();
    }
    // </editor-fold>
}