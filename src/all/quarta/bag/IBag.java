package all.quarta.bag;

public interface IBag<T> {
    public boolean isEmpty();
    public void  add(T input);
    public boolean contains(T input);
    public void clear();
    public int size();
    public int getFrequency(T input);

}
