package all.quarta.olimpiadi;
import java.util.*;
import java.io.File;
import java.io.PrintStream;
import java.io.FileNotFoundException;
public class MainTerzo {
    public static int controlla(int N, int V[]) {
        int pos=0;
        while(pos<N-2 && V[pos]<=V[pos+1])pos++;
        int corr,num,equal=0;
        boolean maggiore=V[pos+1]>=V[pos-1];
        if(maggiore){
            //e` maggiore
            corr=pos+1;
            num=V[pos];
            int sba=V[pos+1];
            while(corr<N && V[corr]<num) {
                if(V[corr]== V[corr+1]) equal++;
                corr++;
            }
        }else{
            //e` minore
            pos++;
            num=V[pos];
            corr=pos-1;

            int sba=V[pos+2];
            while(corr>=0 && V[corr]>num) {
                if(V[corr]== V[corr-1]) equal++;
                corr--;
            }
        }




        return Math.abs(corr-pos)-1-equal;
    }

    public static void main(String[] args) throws FileNotFoundException {

        // Se vuoi leggere/scrivere da linea di comando decommenta qua
        //Scanner scanner = new Scanner(System.in);

        // Se vuoi leggere/scrivere da file decommenta qua
        Scanner scanner = new Scanner(new File("input.txt")); // Input fornito dalla piattaforma
        PrintStream fileStream = new PrintStream("output.txt"); // Output da inviare alla piattaforma
        System.setOut(fileStream);

        int N = scanner.nextInt();
        scanner.nextLine(); // Move scanner to the next line
        int[] V = new int[N];

        for(int i=0; i<N; i++) {
            V[i] = scanner.nextInt();
        }

        System.out.println(controlla(N, V));
    }
}
