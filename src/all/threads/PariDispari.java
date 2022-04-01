package all.threads;

public class PariDispari implements Runnable {
    private int num;
    public PariDispari(int num){
        this.num=num;
    }
    @Override
    public void run(){
        for (int i = 0; i < num; i++)
            System.out.println("Numero "+i+": "+(i%2==0?"pari":"dispari"));
    }
}
