package all;

public class Main {
    public static void main(String[] args){
       Prodotto[] arr={new Prodotto("ciao",0.2,34),new Prodotto("ciao2",0.4,34)};
       Prodotto prr=new Prodotto("code",12,23);
       Supermercato sm=new Supermercato(arr);
       System.out.println(sm);
       sm.add(prr);
       System.out.println(sm);
    }
}
