package all.threads;

import java.util.LinkedList;
import java.util.Queue;

public class Semaforo {
    private int s;
    private Queue<Thread> coda;

    public Semaforo(int s){
        this.s=s;
        coda = new LinkedList<>();
    }

    public synchronized void P(){
        while(s == 0){
            try{
                wait();
            }catch(Exception e){
                System.err.println(e.getMessage());
            }
        }
        s--;
    }

    public synchronized void V(){
        s++;
        notify();
    }
}
