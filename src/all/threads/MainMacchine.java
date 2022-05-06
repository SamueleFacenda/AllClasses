package all.threads;

import java.util.LinkedList;
import java.util.Queue;

public class MainMacchine {
    public static Queue<Auto> podio;

    public static void main(String[] args) {
        Semaforo trag=new Semaforo(1);
        Auto al1=new Planante("alu",10,30,trag,50),al2=new Planante("aldo",40,30,trag,200);
        Auto ru1=new SuperRuotata("ruomulo",10,30,trag,1),ru2=new SuperRuotata("ruatto",40,30,trag,4);

        podio=new LinkedList<>();

        al1.start();
        al2.start();
        ru1.start();
        ru2.start();

        try {
            al1.join();
            al2.join();
            ru1.join();
            ru2.join();
        }catch(Exception e){}
        int i=0;
        while(!podio.isEmpty()){
            System.out.println("Posizione "+(i+1)+":   "+podio.poll());
            i++;
        }
    }
}
