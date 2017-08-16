/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.csc.mystackkitane;

/**
 *
 * @author student
 */
public class myStackNum {
   private int maxSize;
   private int[] stackArray;
   private int top;
   
   public myStackNum(int s) {
      maxSize = s;
      stackArray = new int[maxSize];
      top = -1;
   }
   public void push(int j) {
      stackArray[++top] = j;
   }
   public int pop() {
      return stackArray[top--];
   }
   public int top() {
      return stackArray[top];
   }
   public boolean isEmpty() {
      return (top == -1);
   }
   public boolean isFull() {
      return (top == maxSize - 1);
   }
}
