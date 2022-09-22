package all.quarta.threads;

public class MainThread {
    public static void main(String[] args) throws Exception{

        Bambino alberto=new Bambino("alberto"),bruno=new Bambino("bruno"),calogero=new Bambino("calogero"),dante=new Bambino("dante");
        alberto.start();
        bruno.start();
        calogero.start();
        dante.start();



        /*Thread cos1=new Thread(new Sposta((int)(Math.random()*10+10))),cos2=new Thread(new Sposta((int)(Math.random()*10+10)));
        cos1.start();
        cos2.start();
        cos1.join();
        cos2.join();
        Sposta.stampaValori();*/

    }
}
