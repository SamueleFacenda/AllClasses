package all.quarta.pescaOggettiPoli;

import java.util.Scanner;

public class MainPartitaPoli {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        PartitaPoli ps=null;
        boolean correct=false;
        do{
            System.out.println("inserire il numero di righe: ");
            int n=in.nextInt();
            System.out.println("inserire il numero di colonne: ");
            int m=in.nextInt();
            System.out.println("inserire il numero di giocatori: ");
            try{
                ps=new PartitaPoli(in.nextInt(),n,m);
                correct=true;
            }catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("reinserire tutti i dati: ");
            }
        }while(!correct);
        int y;
        while(!ps.isEnded()){
            System.out.println("giocatore corrente: "+ps.getnPlayer());
            do{
                System.out.println("inserire la riga della cella scelta");
                y=in.nextInt();
                System.out.println("inserire la colonna della cella scelta");
                correct=ps.pesca(in.nextInt(),y);
                System.out.println(ps.getMessage());
            }while(!correct);
            System.out.println(ps);
        }
    }
}
