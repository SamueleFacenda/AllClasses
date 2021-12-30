package all.pescaoggetti;

import java.util.Scanner;

public class MainPescaOggetti {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        PescaOggetti ps=null;
        boolean correct=false;
        do{
            System.out.println("inserire il numero di righe: ");
            int n=in.nextInt();
            System.out.println("inserire il numero di colonne: ");
            int m=in.nextInt();
            System.out.println("inserire il numero di giocatori: ");
            try{
                ps=new PescaOggetti(in.nextInt(),n,m);
                correct=true;
            }catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("reinserire tutti i dati: ");
            }
        }while(!correct);
        int x,y;
        while(!ps.isEnded()){
            System.out.println("giocatore corrente: "+ps.getGiocatoreCorrente());
            do{
                System.out.println("inserire la riga della cella scelta");
                y=in.nextInt();
                System.out.println("inserire la colonna della cella scelta");
                correct=ps.pesca(in.nextInt(),y);
                System.out.println(ps.getMessageAfterDraw());
            }while(!correct);
            System.out.println(ps);
        }
    }
}
