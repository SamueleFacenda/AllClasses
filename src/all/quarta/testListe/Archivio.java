package all.quarta.testListe;

import java.util.*;

public class Archivio {
    private LinkedList<VideoLezione> link;
    private ArrayList<VideoLezione> arr;
    public Archivio(List<VideoLezione> in){
        double start=  System.currentTimeMillis();
        link=new LinkedList<>(in);
        System.out.println("Linked: "+(System.currentTimeMillis()-start));
        start=System.currentTimeMillis();
        arr=new ArrayList<>(in);
        System.out.println("Array: "+(System.currentTimeMillis()-start));
    }
    public void addInOrder(VideoLezione val){
        double start=  System.currentTimeMillis();

        ListIterator<VideoLezione> it=link.listIterator();
        while(it.hasNext() && it.next().getCode() < val.getCode());
        if(it.hasNext()){
            it.previous();
            it.add(val);
        }else
            it.add(val);

        System.out.println("Linked: "+(System.currentTimeMillis()-start));

        start=System.currentTimeMillis();

        int i=0;
        while(i< arr.size() && arr.get(i++).getCode() < val.getCode());
        if(i==arr.size())
            arr.add(val);
        else
            arr.add(i-1,val);

        System.out.println("Array: "+(System.currentTimeMillis()-start));
    }
}

