package all.quarta.threads;

public class Sposta implements Runnable {
    private static int var1=200,var2=300;
    private int quanto;
    public Sposta(int quanto){
        this.quanto=quanto;
    }
    @Override
    public void run(){
        var1+=quanto;
        var2-=quanto;
    }
    public static void stampaValori(){
        System.out.println("var1: "+var1);
        System.out.println("var2: "+var2);
    }
}
