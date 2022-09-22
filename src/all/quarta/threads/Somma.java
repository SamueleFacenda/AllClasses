package all.quarta.threads;

public class Somma implements Runnable {
    private int num;
    public Somma(int num){
        this.num=num;
    }
    @Override
    public void run(){
        int sum=0;
        for (int i = 0; i <= num*2; i=i+2) {
            System.out.println("num: "+i);
            sum+=i;
        }
        System.out.println("Somma: "+sum);
    }
}
