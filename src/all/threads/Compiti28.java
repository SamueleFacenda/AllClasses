package all.threads;

import java.util.Arrays;
import java.util.Scanner;

public class Compiti28 {
    public static int[] vett2,vett1;
    public static int x;
    static class Es1 implements Runnable{
        @Override
        public void run(){
            Compiti28.vett2=new int[Compiti28.vett1.length];
            for (int i = 0; i < Compiti28.vett1.length; i++)
                Compiti28.vett2[i] = Compiti28.vett1[i] * x;
        }
    }

    public static void es1() throws InterruptedException {
        System.out.println("inserire lunghezza vettore");
        Scanner in=new Scanner(System.in);
        vett1=new int[in.nextInt()];
        System.out.println("inserire valori");
        for (int i = 0; i < vett1.length; i++)
            vett1[i]=in.nextInt();
        Thread t=new Thread(new Es1());
        t.start();
        Thread.sleep(100);
        System.out.println("vettore uno");
        for(int i:vett1)
            System.out.println(i);
        System.out.println("vettore due");
        for(int i:vett2)
            System.out.println(i);
    }


    public static class Es2 extends Thread{
        private int dip;
        private int[][] mat;
        public Es2(int dip,int[][] mat){
            this.dip=dip;
            this.mat=mat;
        }
        @Override
        public void run(){
            int sum=Arrays.stream(mat[dip]).reduce(0,Integer::sum);
            System.out.println("somma di guadagno di "+dip+" = "+sum);
        }
    }


    public static void es2(){
        int[][] mat={
                {1,1,1},
                {2,2,2},
                {3,3,3},
                {4,4,4},
                {5,5,5}
        };
        Thread t=new Thread(new Es2(0,mat));
        t.start();
    }

    public static void main(String[] args) throws InterruptedException {
        es1();
    }
}
