package all;

import all.Poli.Figlio;
import all.Poli.Padre;
import all.bag.Codifica;

import java.util.Scanner;


public class Main {
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        while(true){
            System.out.println(("inserire numero"));
            int num=in.nextInt();
            System.out.println("inserire la base: ");
            int base=in.nextInt();
            System.out.println("numero codificato: "+Codifica.codifica(num,base));
            System.out.println("numero decodificato: "+Codifica.decodifica(Codifica.codifica(num,base),base));
        }

    }
}
