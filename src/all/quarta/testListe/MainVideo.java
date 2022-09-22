package all.quarta.testListe;

import java.util.*;

public class MainVideo {
    public static void main(String[] args) {
        LinkedList<VideoLezione> lista=new LinkedList<>();
        for(int i=0;i<10000000;i++){
            lista.add(new VideoLezione(i,i%100,i%60,"lezione su samuele"));
        }
        Archivio a=new Archivio(lista);
        a.addInOrder(new VideoLezione(5000,12,0,"nuova lezione"));
    }
}
