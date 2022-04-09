package all.bag;

public class MainIter {
    public static void main(String[] args) {
        CircularQueue<Integer> qu=new CircularQueue<>(5);
        qu.enqueue(2);
        qu.enqueue(4);
        qu.enqueue(6);
        qu.enqueue(8);
        qu.enqueue(10);
        qu.dequeue();
        qu.dequeue();
        qu.enqueue(23);
        qu.enqueue(3445);
        qu.enqueue(23);
    }
}
