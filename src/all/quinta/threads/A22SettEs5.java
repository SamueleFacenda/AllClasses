package all.quinta.threads;

/*
Scrivere un programma che gestisca due risorse condivise
(variabili di tipo intero ad esempio di valore 200 e 300)
e generi due thread per “spostare” parte del primo numero
nel secondo numero.
La quantità da spostare (numero casuale compreso tra 10 e
20), diversa per ciascun thread, viene passata come
parametro alla creazione dei due thread.
Il processo principale attende mediante uno sleep la
terminazione dei due thread e visualizza i nuovi valori
delle due variabili ed il loro totale.
 */

import all.quarta.threads.Semaforo;

public class A22SettEs5 {
    private static int uno, due;
    private static Semaforo unoS, dueS;

    static class Sposta extends Thread{
        private int quantita;
        public Sposta(int quantita){
            this.quantita=quantita;
        }
        @Override
        public void run(){
            unoS.P();
            dueS.P();
            uno-=quantita;
            due+=quantita;
            System.out.println("uno: "+uno+" due: "+due);
            dueS.V();
            unoS.V();
        }
    }

    public static void main(String[] args) {
        uno = 200;
        due = 300;
        unoS = new Semaforo(1);
        dueS = new Semaforo(1);
        Thread t1 = new Sposta((int)(Math.random()*10+10));
        Thread t2 = new Sposta((int)(Math.random()*10+10));
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        }catch(Exception e){}
        System.out.println("uno: "+uno+" due: "+due);


    }
}
