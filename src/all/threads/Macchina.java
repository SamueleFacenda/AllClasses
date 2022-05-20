package all.threads;

public class Macchina implements Runnable{
    private static int cisterna;
    private static Semaforo cist;

    @Override
    public void run() {
        int tmp;
        StringBuilder st;
        while (true) {

            int rif = (int) (Math.random() * 5) + 5;

            cist.P();
            if(cisterna-rif>=0)
                cisterna -= rif;
            tmp = cisterna;
            cist.V();

            st = new StringBuilder("Litri tolti:"+rif+"  Litri totali: "+tmp+" Cisterna: <");
            for (int i = 0; i < 100; i++) {
                st.append(i < tmp ? "=" : " ");

            }
            st.append(">");
            System.out.println(st.toString());
            try {
                Thread.sleep(rif * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class Refounder implements Runnable{
        @Override
        public void run(){
            while(true){
                cist.P();
                if(cisterna<20){
                    System.out.println("rifornimento");
                    cisterna=100;
                }
                cist.V();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        cisterna=100;
        cist=new Semaforo(1);
        Thread t1=new Thread(new Macchina()), t2=new Thread(new Macchina()),ref=new Thread(new Refounder());
        t1.start();
        t2.start();
        ref.start();
    }

}
