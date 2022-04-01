package all.threads;

public class MainThread {
    public static void main(String[] args) throws Exception{

        Thread s1=new Thread(new Contatore(100));
        s1.start();
    }
}
