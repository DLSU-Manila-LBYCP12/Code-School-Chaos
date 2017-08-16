package ph.edu.dlsu.csc.myqueue;

import ph.edu.dlsu.csc.myexception.*;

/**
 *
 * @author NeilOliver
 */
public class MyQueue<E> {

    private static final int capacity = 5;
    private E[] queueArray;
    private final int NMAX; // capacity
    private int front = 0;
    private int rear = 0;

    public MyQueue() {
        this(capacity);
    }

    public MyQueue(int capacity) {
        NMAX = capacity;
        queueArray = (E[]) new Object[NMAX];
    }

    public int size() {
        if (rear > front) {
            return rear - front;
        }
        return NMAX - front + rear;
    }

    public boolean isEmpty() {
        return (rear == front);
    }

    public boolean isFull() {
        int diff = rear - front;
        return diff == -1 || diff == (NMAX - 1);
    }

    public void enqueue(E item) throws QueueFullException {
        if (isFull()) {
            throw new QueueFullException("Queue is Full.");
        } else {
            queueArray[rear] = item;
            rear = (rear + 1) % NMAX;
        }
    }
    
    public E peek(){
        return queueArray[front];
    }

    public E dequeue() throws QueueEmptyException {
        E item;
        if (isEmpty()) {
            throw new QueueEmptyException();
        } else {
            item = queueArray[front];
            queueArray[front] = null;
            front = (front + 1) % NMAX;
        }
        return item;
    }
    
    public boolean contains(E item){
        for(int i = front; i < rear+1; i++){
            if(queueArray[i] == item){
                return true;
            }
        }
        return false;
    }
    
    public boolean containsAll(MyQueue queue){
        E[] array = (E[]) new Object[queue.size()];
        int counter = 0;
        for(int i=0; i<queue.size(); i++){
            array[i] = (E) queue.dequeue();
        }
        for(int i = front; i < rear+1; i++){
            for(int j = 0; j < array.length; j++){
                if(queueArray[i] == array[j]){
                    counter++;
                }
            }
        }
        return counter == queue.size();
    }
    
    public static void main(String[] args){
        MyQueue<Integer> queue = new MyQueue<>(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        System.out.println("Dequeued: " + queue.dequeue());
        queue.enqueue(5);
        System.out.println("Dequeued: " + queue.dequeue());
        queue.enqueue(6);
        System.out.println("Dequeued: " + queue.dequeue());
        queue.enqueue(7);
        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("Dequeued: " + queue.dequeue());
    }
}
