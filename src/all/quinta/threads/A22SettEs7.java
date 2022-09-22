package all.quinta.threads;

/*
Dato in input un vettore (vettore1) e un numero x,
generare un thread che riceva come parametro x e
memorizzi in un secondo vettore (vettore2) il prodotto
tra ogni elemento del vettore1 e x. Il processo
principale attende mediante uno sleep la terminazione dei
due thread e stampa il contento dei due vettori.
 */

import all.quarta.threads.Semaforo;

public class A22SettEs7 {
    private static int[] vettore1 = {1,2,3,4,5,6,7,8,9,10};
    private static int[] vettore2 = new int[vettore1.length];
    private static Semaforo s;

    static class Moltiplica extends Thread{
        private final int x;
        public Moltiplica(int x){
            this.x=x;
        }
        @Override
        public void run(){
            for(int i=0; i<vettore1.length; i++){
                s.P();
                vettore2[i] = vettore1[i]*x;
                s.V();
            }
        }
    }

    public static void main(String[] args) {
        s = new Semaforo(1);
        Thread t1 = new Moltiplica(2);
        t1.start();
        try{
            t1.join();
        }catch(Exception e){}
        for(int i=0; i<vettore1.length; i++){
            System.out.println(vettore1[i]+" "+vettore2[i]);
        }
    }
}
