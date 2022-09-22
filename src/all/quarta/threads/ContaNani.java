package all.quarta.threads;

public class ContaNani extends Thread implements Runnable {

    public ContaNani(String nome){
        super();
        setName(nome);
    }

    @Override
    public void run(){
        for (int i = 0; i < 10; i++) {
            System.out.println(getName()+ "   "+i);
        }
    }
}
