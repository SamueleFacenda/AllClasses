package all.threads;

public class MioConta implements Runnable {
    private int c;
    private String nome;

    public MioConta(String no){
        nome=no;
        c=0;
    }
    @Override
    public void run(){
        while(true){
            c++;
            contaSem.P();
            cont++;
            System.out.println(nome+" sem glob: "+cont+ "   mio cont: "+c);
            contaSem.V();
        }
    }

    protected static Semaforo contaSem=new Semaforo(1);
    protected static int cont=0;


    public static void main(String[] args) {
        Thread t=new Thread(new MioConta("uno")),t2=new Thread(new MioConta("due"));
        t.start();
        t2.start();
    }
}
