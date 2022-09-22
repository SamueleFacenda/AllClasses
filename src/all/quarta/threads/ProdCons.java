package all.quarta.threads;

public class ProdCons {
    public static Semaforo vuoto,pieno;
    public static int buffer;

    static class Produttore implements Runnable{
        @Override
        public void run(){
            int i=0;
            while(i<10){
                vuoto.P();
                buffer++;
                System.out.println("prodotto: "+buffer);
                pieno.V();
                i++;
            }
        }
    }
    static class Consumatore implements Runnable{
        @Override
        public void run(){
            int i=0;
            while(i<10){
                pieno.P();
                buffer--;
                System.out.println("consumato: "+buffer);
                vuoto.V();
                i++;
            }
        }
    }

    public static void main(String[] args) {
        buffer = 10;
        Thread p=new Thread(new Produttore()),c=new Thread(new Consumatore());
        vuoto=new Semaforo(1);
        pieno=new Semaforo(0);
        p.start();
        c.start();
    }
}
