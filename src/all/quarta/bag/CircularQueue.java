package all.quarta.bag;

import java.util.NoSuchElementException;

public class CircularQueue<T>{

    private int size;
    private int front;
    private int rear;
    private T[] queue;

    public CircularQueue(int size){
        this.size = 0;
        front = 0;
        rear = 0;
        queue = (T[]) new Object[size];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean isFull(){
        return size == queue.length;
    }

    public void enqueue(T item)throws NoSuchElementException {
        if(isFull())
            throw new NoSuchElementException("Queue is full");
        queue[rear] = item;
        size++;
        rear = (rear + 1) % queue.length;
    }

    public T dequeue()throws NoSuchElementException{
        if(isEmpty())
            throw new NoSuchElementException("Queue is empty");
        T item = queue[front];
        front = (front + 1) % queue.length;
        size--;
        return item;
    }

    public T peek()throws NoSuchElementException{
        if(isEmpty())
            throw new NoSuchElementException("Queue is empty");
        return queue[front];
    }

    public int size(){
        return size;
    }
}
