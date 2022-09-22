package all.quarta.threads;

public class Semaforo {
    private int s;
    public Semaforo(int s){
        this.s=s;
    }
    public synchronized void P(){
        while(s==0){
            try{
                wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        s--;
    }
    public synchronized void V(){
        s++;
        notify();
    }
}
