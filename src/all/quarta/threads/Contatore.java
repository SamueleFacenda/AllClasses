package all.quarta.threads;

public class Contatore implements Runnable {
    private int num;
    public Contatore(int from){
        num=from;
    }
    @Override
    public void run(){
        for (int i = num; i >= 0 ; i--) {
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
