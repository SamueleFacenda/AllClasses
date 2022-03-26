package all.bag;

import java.util.Iterator;
import java.util.ListIterator;

public class GroupedBag<T> implements IBag<T>,Iterable<T> {
    private int size;
    private NodeDouble<T> first;
    public GroupedBag(){
        size=0;
        first=null;
    }
    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public void add(T input) {
        NodeDouble<T> current=new NodeDouble<>(null,first,null);
        while(current.hasNext() && !current.getNext().getElemento().equals(input)) current=current.getNext();
        if(current.getNext().getElemento().equals(input)){
            NodeDouble<T> tmp=current.getNext(),niu=new NodeDouble<>(input,tmp.getNext(),tmp);
            tmp.getNext().setPrevious(niu);
            tmp.setNext(niu);
        }else{
            NodeDouble<T> niu=new NodeDouble<>(input,first,null);
            first.setPrevious(niu);
            first=niu;
        }
        size++;
    }

    @Override
    public boolean contains(T input) {
        ListIterator<T> it=(ListIterator<T>) iterator();
        while(it.hasNext() && !it.next().equals(input));
        return it.previous().equals(input);
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
    public int getFrequency(T input) {
        ListIterator<T> it=(ListIterator<T>) iterator();
        while(it.hasNext() && !it.next().equals(input));
        if(it.previous().equals(input)){
            int out=1;
            while(it.hasNext() && it.next().equals(input)) out++;
            return out;
        }else
            return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new GroupedBagIterator<>(this);
    }

    protected NodeDouble<T> getFirst(){
        return first;
    }
    protected void setFirst(NodeDouble<T> input){
        first=input;
    }
    protected void setSize(int i){
        size=i;
    }

    class NodeDouble<T>{
        private T elemento;
        private NodeDouble<T> next,previous;
        public NodeDouble(T elemento, NodeDouble<T> next,NodeDouble<T> previous){
            this.elemento=elemento;
            this.next=next;
            this.previous=previous;
        }
        public boolean hasNext(){
            return next!=null;
        }
        public T getElemento(){
            return elemento;
        }

        public void setNext(NodeDouble<T> next) {
            this.next = next;
        }

        public void setPrevious(NodeDouble<T> previous) {
            this.previous = previous;
        }

        public NodeDouble<T> getNext(){
            if(next==null)
                throw new NullPointerException();
            return next;
        }
        public NodeDouble<T> getPrevious(){
            if(previous==null)
                throw new NullPointerException();
            return previous;
        }

        public boolean hasPrevious() {
            return previous!=null;
        }
    }
    class GroupedBagIterator<T> implements ListIterator<T>{
        private NodeDouble<T> current,last;
        private GroupedBag<T> currentBag;
        private int i;
        public GroupedBagIterator(GroupedBag<T> in){
            current=new NodeDouble<T>(null, (NodeDouble<T>) in.getFirst(),null);
            i=0;
        }

        @Override
        public boolean hasNext() {
            return current.hasNext();
        }

        @Override
        public T next() {
            current=current.getNext();
            last=current;
            i++;
            return current.getElemento();
        }

        @Override
        public boolean hasPrevious() {
            return current.hasPrevious();
        }

        @Override
        public T previous() {
            current=current.getPrevious();
            i--;
            return current.getElemento();
        }

        @Override
        public int nextIndex() {
            return i;
        }

        @Override
        public int previousIndex() {
            return i-1;
        }

        @Override
        public void remove() {
            if(current.hasPrevious()){
                if(current.hasNext()) {
                    current.getPrevious().setNext(current.getNext());
                    current.getNext().setPrevious(current.getPrevious());
                }else{
                    current.getPrevious().setNext(null);
                }
                current=current.getPrevious();
            }else{
                if(current.hasNext()){
                    current.getNext().setPrevious(null);
                    currentBag.setFirst((GroupedBag<T>.NodeDouble<T>) current.getNext());
                }else{
                    current=new NodeDouble<>(null,null,null);
                    currentBag.setFirst(null);
                }
            }
            currentBag.setSize(currentBag.size()==0?0:currentBag.size()-1);
        }

        @Override
        public void set(T t) {

        }

        @Override
        public void add(T t) {

        }

    }
}
