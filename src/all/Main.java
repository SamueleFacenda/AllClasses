package all;

public class Main {
    public static void main(String[] args){
       Parola p=new Parola(new char[]{'a','b','c','d','e'});
       p=new Parola(p.codifica());
        System.out.println(p);
    }
}
