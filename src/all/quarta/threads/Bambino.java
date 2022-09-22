package all.quarta.threads;

public class Bambino extends Thread implements Runnable {
    private String nome;
    private int passi;
    public static final int traguardo=100;
    private static int posizione=0;
    private static boolean pos=true;
    public Bambino(String nome){
        this.nome=nome;
        passi=0;
    }
    @Override
    public void run(){
        System.out.println(nome+" è partito");
        while(passi<traguardo){
            int rnd=(int)(Math.random()*10+1);
            System.out.println(nome+" avanza di "+rnd+" passi");
            passi+=rnd;
            if(passi<traguardo){
                System.out.println(nome+" si riposa, non ha raggiunto la merenda");
                try{
                    Thread.sleep(1000);
                }catch(Exception e){}
            }
        }
        lock();
        posizione++;
        if(posizione==1)
            System.out.println(nome+" ha preso la merenda");
        else
            System.out.println(nome+" è arrivato in "+posizione+" posizione");
        unlock();
    }

    private synchronized void lock(){
        while(!pos);
        pos=false;
    }
    private synchronized void unlock(){
        pos=true;
    }
}
