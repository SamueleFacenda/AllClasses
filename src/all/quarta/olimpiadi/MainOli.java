package all.quarta.olimpiadi;
import java.util.*;
import java.io.File;
import java.io.PrintStream;
import java.io.FileNotFoundException;


public class MainOli {
    //la uno e piu corta
    public static boolean checkOneAdd(String uno,String due){
        int iu=0,id=0,diff=0;
        while(iu<uno.length()){
            if(uno.charAt(iu)!=due.charAt(id)){
                if(diff==0)
                    iu--;
                diff++;
            }

            iu++;
            id++;
        }
        return diff>1;
    }
    public static void main(String[] args) throws FileNotFoundException{
        int n;
        Scanner in = new Scanner(new File("input.txt")); // Input fornito dalla piattaforma
        PrintStream fileStream = new PrintStream("output.txt"); // Output da inviare alla piattaforma
        System.setOut(fileStream);
        n=in.nextInt();
        in.nextLine();
        String niu,old;
        boolean upper,downer,strange,digit,cons;
        int car,last=0;
        for (int i = 0; i < n; i++) {
            old=in.nextLine();
            niu=old.split(" ")[0];
            old=old.split(" ")[1];
            upper=downer=strange=digit=cons=false;
            if(niu.length()>=8&&niu.length()<=16) {
                for (int j = 0; j < niu.length(); j++) {

                    car = (int) niu.charAt(j);
                    if(j!=0) {
                        if(car==last)
                            cons=true;
                    }
                    if(car>=48&&car<=57)
                        digit=true;
                    else if(car>=65&&car<=90)
                        upper=true;
                    else if(car>=97&&car<=122)
                        downer=true;
                    else
                        strange=true;
                    last=car;
                }
                if(upper&&downer&&strange&&digit && !cons){
                    if(niu.length()+1==old.length()){
                        if(checkOneAdd(niu,old))
                            System.out.println(1);
                        else
                            System.out.println(0);
                    }else if(old.length()+1==niu.length()){

                        if(checkOneAdd(old,niu))
                            System.out.println(1);
                        else
                            System.out.println(0);
                    }else if(niu.length()==old.length()){
                        int diff=0,e=0;
                        while(e<old.length()){
                            if(old.charAt(e)!=niu.charAt(e))
                                diff++;
                            e++;
                        }
                        if(diff>1)
                            System.out.println(1);
                        else
                            System.out.println(0);
                    }else
                        System.out.println(1);
                }else
                    System.out.println(0);
            }else{
                System.out.println(0);
            }
        }

    }
}
