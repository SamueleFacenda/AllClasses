package all.quinta.threads;

/*
Data una matrice 5*5 caricata di numeri casuali da 1 a 100
generare due thread;
Il primo thread calcola la somma delle prime tre righe della
matrice e registra in una risorsa condivisa il valore.
Il secondo thread calcola la somma degli elementi delle ultime due
righe e registra in una risorsa condivisa il valore. Il main
aspetta la terminazione dei due thread ed esegue la somma dei
valori calcolati dai due thread e stampa il risultato.
 */

public class A22SettEs8 {
    private static int[][] matrice = new int[5][5];
    private static int[] somme = new int[2];
    static class Sommatore extends Thread{
        private int[] righe;
        private int id;
        public Sommatore(int[] righe, int id){
            super();
            this.id = id;
            this.righe=righe;
        }
        @Override
        public void run(){
            for(int i=0; i<righe.length; i++){
                for (int j = 0; j < 5; j++) {
                    somme[id] += matrice[righe[i]][j];
                }
            }
        }
    }

    public static void main(String[] args) {
        for(int i=0; i<5; i++){
            for (int j = 0; j < 5; j++) {
                matrice[i][j] = (int)(Math.random()*100+1);
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println();
        }
        Thread t1 = new Sommatore(new int[]{0,1,2}, 0);
        Thread t2 = new Sommatore(new int[]{3,4}, 1);
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        }catch(Exception e){}
        System.out.println(somme[0]+somme[1]);
    }
}
