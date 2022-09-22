package all.quarta.bag;


import java.util.Iterator;

public class Bag<T> implements IBag<T>, Iterable<T> {
    class Node<T>{
        private T elemento;
        private Node<T> next;
        public Node(T elemento,Node<T> next){
            this.elemento=elemento;
            this.next=next;
        }
        public boolean hasNext(){
            return elemento!=null;
        }
        public T getElemento(){
            return elemento;
        }
        public Node<T> getNext(){
            if(next==null)
                throw new NullPointerException();
            return next;
        }
    }

    private int size;
    private Node<T> first;

    public Bag(){
        size=0;
        first=null;
    }


    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public void add(T input) {
        first= new Node<>(input,first);
        size++;
    }


    @Override
    public boolean contains(T input) {
        if(size==0)
            return false;
        Node<T> current=first;
        while(current.hasNext() && !current.getElemento().equals(input)) current=current.next;
        return current.getElemento().equals(input);
    }

    @Override
    public void clear() {
        size=0;
        first=null;
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public String toString(){
        String out="";
        Iterator<T> it=iterator();
        for(T cur:this)
            out+=cur.toString()+"\n";
        return out;
    }

    @Override
    public int getFrequency(T input) {
        if(size==0)
            return 0;
        Node<T> current=first;
        int out=0;
        for(T cur: this){
            if(cur.equals(input))
                out++;
        }
        return out;
    }


    @Override
    public Iterator<T> iterator() {
        return new BagIterator<T>(first);
    }


    public class BagIterator<T> implements Iterator<T> {

        private Node<T> next;

        protected BagIterator(Node<T> in){
            next=in;
        }

        @Override
        public boolean hasNext() {
            return next!=null;
        }

        @Override
        public T next() {
            T out=next.getElemento();
            try {
                next=next.getNext();
            }catch(NullPointerException e){
                next=null;
            }
            return out;
        }


    }
}
