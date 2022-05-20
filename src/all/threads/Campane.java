package all.threads;

public class Campane {

    static class Campana extends Thread{
        private String suono;
        private Semaforo suo, dopo;
        public Campana(String suono,Semaforo suo, Semaforo dopo){
            super();
            this.suono=suono;
            this.suo=suo;
            this.dopo=dopo;
        }
        @Override
        public void run(){
            while(true){
                suo.P();
                System.out.println(suono);
                try{
                    sleep(200);
                }catch(Exception e){}
                dopo.V();
            }
        }
    }

    public static void main(String[] args) {
        Semaforo di=new Semaforo(1),doo=new Semaforo(0),da=new Semaforo(0);
        Thread din=new Campana("din",di,doo),don=new Campana("don",doo,da),dan=new Campana("dan",da,di);
        din.start();
        don.start();
        dan.start();
    }
}
