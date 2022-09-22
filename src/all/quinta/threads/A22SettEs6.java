package all.quinta.threads;

/*
Realizzare un programma che simuli la corsa alla merenda
di tre bambini (Carlo, Matilde e Francesco) facendo
avanzare casualmente la posizione di ciascuno di essi
(tra 1 e 10) e verificando se viene raggiunto il
traguardo: il primo che raggiunge i 100m mangia la
merenda. Alla partenza stampare ad esempio “E' partirto
Carlo!!!”. Ogni volta che un bambino avanza si deve
stampare il nome del bambino e la quantità di passi
totali che ha effettuato. Dopo ogni spostamento il
bambino si riposa e viene visualizzato il seguente

messaggio: “non ho finito, ma mi riposo un attimo !”.
Quando un bambino ha preso la merenda e cioè ha superato
i 100m viene visualizzato ad esempio “Carlo ha preso la
merenda!!!”.
 */

import all.quarta.threads.Semaforo;

public class A22SettEs6 {
    private static boolean merenda = true;
    private static Semaforo s;
    private static final int TRAGUARDO = 100;

    static class Bambino implements Runnable{
        private String nome;
        public Bambino(String nome){
            this.nome = nome;
        }
        @Override
        public void run(){
            int passi = 0;
            System.out.println("E' partito "+nome+"!!!");
            while(passi < TRAGUARDO){
                passi += (int)(Math.random()*10+1);
                System.out.println(nome+" "+passi);
                if(passi>=TRAGUARDO){
                    s.P();
                    if(merenda){
                        System.out.println(nome+" ha preso la merenda!!!");
                        merenda = false;
                    }else{
                        System.out.println(nome+" non ha preso la merenda");
                    }
                    s.V();
                }else{
                    System.out.println("non ho finito, ma mi riposo un attimo !");
                }
                try{
                    Thread.sleep(1000);
                }catch(Exception e){}
            }
        }
    }

    public static void main(String[] args) {
        s = new Semaforo(1);
        Thread t1 = new Thread(new Bambino("Carlo"));
        Thread t2 = new Thread(new Bambino("Matilde"));
        Thread t3 = new Thread(new Bambino("Francesco"));
        t1.start();
        t2.start();
        t3.start();
        try{
            t1.join();
            t2.join();
            t3.join();
        }catch(Exception e){}
    }
}
