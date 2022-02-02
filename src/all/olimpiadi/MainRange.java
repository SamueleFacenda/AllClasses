package all.olimpiadi;
import java.util.*;
import java.io.File;
import java.io.PrintStream;
import java.io.FileNotFoundException;

public class MainRange {
    public static long conta(int N, int K, long ranges[][]) {
        long[] swap;
        for (int i = 0; i < ranges.length; i++) {
            for (int j = 0; j < ranges.length-1; j++) {
                if(ranges[j][0]>ranges[j+1][0]){
                    swap=ranges[j];
                    ranges[j]=ranges[j+1];
                    ranges[j+1]=swap;
                }
            }
        }

        LinkedList<long[]> lista=new LinkedList<>();
        int currentSovr=0,out=0;
        int i=0,number=0;
        ListIterator<long[]> it;
        while(i<ranges.length || lista.size()>0){
            while(i < ranges.length && number==ranges[i][0]){
                currentSovr++;
                lista.addLast(ranges[i]);
                i++;
            }
            it= lista.listIterator();
            while(it.hasNext()){
                if(it.next()[1]<number){
                    it.remove();
                    currentSovr--;
                }
            }
            number++;
            if(currentSovr==K)
                out++;
        }
        return out;
    }

    public static void main(String[] args) throws FileNotFoundException {

        // Se vuoi leggere/scrivere da linea di comando decommenta qua
        //Scanner scanner = new Scanner(System.in);

        // Se vuoi leggere/scrivere da file decommenta qua
         Scanner scanner = new Scanner(new File("input.txt"));
         PrintStream fileStream = new PrintStream("C:\\Users\\samue\\IdeaProjects\\AllClasses\\src\\all\\olimpiadi\\output.txt");
         System.setOut(fileStream);

        int N = scanner.nextInt();
        int K = scanner.nextInt();
        scanner.nextLine(); // Move scanner to the next line

        long[][] ranges = new long[N][2];

        for(int i=0; i<N; i++) {
            ranges[i][0] = scanner.nextLong();
            ranges[i][1] = scanner.nextLong();
            //scanner.nextLine();
        }

        System.out.println(conta(N, K, ranges));

    }
}
