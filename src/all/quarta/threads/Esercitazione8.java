package all.quarta.threads;

public class Esercitazione8 {
    public static class Segretatore extends Thread{
        private Semaforo[] amici;
        public Segretatore(Semaforo[] amici){
            this.amici=amici;
        }
        @Override
        public void run(){
            for(int i=0;i<20;i++){
                int amicoScelto = (int)(Math.random()*amici.length);
                amici[amicoScelto].V();
                System.out.println("Segretatore: ho scelto l'amico "+amicoScelto);
                try{
                    Thread.sleep((int)(Math.random()*1000));
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.exit(0);
        }
    }
    public static class Amico extends Thread{
        private Semaforo segretatore;
        public Amico(Semaforo segretatore){
            this.segretatore=segretatore;
        }
        @Override
        public void run(){
            while(true){
                segretatore.P();
                System.out.println("Amico: sono stato scelto");
            }
        }
    }

    public static void main(String[] args) {
        Semaforo[] amici = new Semaforo[3];
        for(int i=0;i<amici.length;i++){
            amici[i]=new Semaforo(0);
        }
        Segretatore segretatore = new Segretatore(amici);
        for(int i=0;i<amici.length;i++){
            Amico amico = new Amico(amici[i]);
            amico.start();
        }
        segretatore.start();
    }

}
