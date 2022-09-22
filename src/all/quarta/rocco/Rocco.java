package all.quarta.rocco;

public class Rocco {

    public static int fattoriale(int n){
        int out;
        if( n <= 0  ){
            out = 1;
        }else{
            out = n * fattoriale(n-1);

        }
        return out;
    }


    public static int fibonacci(int posizione){
        if(posizione <= 2){
            return 1;                        //caso base
        }else{
            return fibonacci(posizione-1) + fibonacci(posizione-2);
        }


    }
    public static int fibonacciBest(int n){
        boolean c=true;
        int uno = 1,due= 1,i=3;
        while(i<=n){
            if(c)
                uno += due;
            else
                due += uno;
            c=!c;
            i++;
        }
        return c ? due : uno;
    }

    public static long fibonacciBestissimo(int n){
        long out = 1,sum = 1,tmp;
        int i=3;
        while(i<=n){
            tmp=out;
            out+=sum;
            sum=out;
            i++;
        }
        return out;
    }





    public static void main(String[] args) {
        System.out.println(fibonacciBestissimo(60));
    }
}
