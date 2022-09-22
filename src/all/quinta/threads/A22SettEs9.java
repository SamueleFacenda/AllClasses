package all.quinta.threads;

/*
Allo stadio di Milano si sta disputando un’importante
partita: Inter – Milan. Dopo i tempi regolamentari e
supplementari il risultato è fermo sullo zero a zero. Si
arriva alla batteria dei rigori. Dopo il fischio
dell’arbitro, il calciatore deve decidere a caso se
tirare a sinistra o a destra; Il portiere deve decidere a
caso se tuffarsi a sinistra o a destra. A questo punto
l’arbitro comunica che il rigore è stato segnato se il
portiere si è tuffato dalla parte opposta rispetto al
tiro del calciatore.
Solo dopo la comunicazione dell’arbitro si può passare a
tirare un altro calcio di rigore, sempre usando il
fischio dell'arbitro per coordinare il nuovo tiro.
Ad ogni goal, l’arbitro aggiorna il punteggio della
squadra che ha segnato.
Il programma concorrente deve terminare quando una
squadra segna dieci rigori. L’arbitro comunica a questo
punto il vincitore.
 */

import all.quarta.threads.Semaforo;

public class A22SettEs9 {
    private static boolean[] tiro = {false, false}, tuffo = {false, false};
    private static int[] punteggio =  {0, 0}; // 0 inter 1 milan
    static class Portiere implements Runnable{
        private Semaforo sTuffo, arbitro;
        private int squadra;
        public Portiere(Semaforo sTuffo, Semaforo arbitro, int squadra){
            this.sTuffo = sTuffo;
            this.arbitro = arbitro;
            this.squadra = squadra;
        }
        @Override
        public void run(){
            while(punteggio[0]<10 && punteggio[1]<10){
                sTuffo.P();
                tuffo[squadra] = (int)(Math.random()*2)==0;
                arbitro.V();
            }
        }
    }
    static class Calciatore implements Runnable{
        private Semaforo sTiro, arbitro;
        private int squadra;
        public Calciatore(Semaforo sTiro, Semaforo arbitro, int squadra){
            this.sTiro = sTiro;
            this.arbitro = arbitro;
            this.squadra = squadra;
        }
        @Override
        public void run(){
            while(punteggio[0]<10 && punteggio[1]<10){
                sTiro.P();
                tiro[squadra] = (int)(Math.random()*2)==0;
                arbitro.V();
            }
        }
    }
    static class Arbitro implements Runnable{
        private Semaforo[] tuffoS, tiroS;
        private Semaforo arbitro;
        public Arbitro(Semaforo[] tuffo, Semaforo[] tiro, Semaforo arbitro){
            this.tuffoS = tuffo;
            this.tiroS = tiro;
            this.arbitro = arbitro;
        }
        @Override
        public void run(){
            int tiroId = 0, tuffoId = 1;
            while(punteggio[0]<10 && punteggio[1]<10){
                tiroS[tiroId].V();
                tuffoS[tuffoId].V();
                arbitro.P();
                arbitro.P();
                if(tiro[tiroId] != tuffo[tuffoId]){
                    punteggio[tiroId]++;
                    System.out.println("Goal "+punteggio[0]+"-"+punteggio[1]);
                }else{
                    System.out.println("Goal sbagliato");
                }
                tiroId = (tiroId+1)%2;
                tuffoId = (tuffoId+1)%2;
            }
            if(punteggio[0]>punteggio[1]){
                System.out.println("Inter vince");
            }else{
                System.out.println("Milan vince");
            }
        }
    }
}
