package all;

import all.Poli.Figlio;
import all.Poli.Padre;

public class Main {
    public static void main(String[] args){
      Padre p=new Padre();
        Figlio f=new Figlio();
        Padre pf=new Figlio();
        Padre pp=pf;
        System.out.println(p);
        System.out.println(pp);
        pp.incrementa();
        System.out.println(p);
        System.out.println(pp);
        pp.incrementa();
        System.out.println(p);
        System.out.println(pp);
        f.incrementa();
        System.out.println(f);
        pf.incrementa();
        System.out.println(pf);
        Figlio ff;
        ff=(Figlio)pp;
        System.out.println(ff);
    }
}
