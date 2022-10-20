package all.quinta.sockets;

import java.util.Map;
import java.util.Scanner;

public class ClientMenu {
    private static final String
            MENU = "1-Ingredenti\n" +
            "2-Prezzo\n" +
            "3-Acquista\n" +
            "0-Esci",
            PIZZE = "a)Margherita\nb)Vegetariana\nc)Capricciosa";

    private static String menu(){
        Scanner in = new Scanner(System.in);
        System.out.println(MENU);
        int scelta = in.nextInt();
        Map<String, String> mappa = Map.of(
                "a" , "M",
                "b" , "V",
                "c" , "C"
        );
        return switch(scelta){
            case 1:
            case 2:
                System.out.println(PIZZE);
                String pizza = in.next();
                yield (scelta == 1 ? "I" : "P") + mappa.get(pizza);
            case 3:
                System.out.println("Quante Margherite vuoi acquistare?");
                int m = in.nextInt();
                System.out.println("Quante Vegetariane vuoi acquistare?");
                int v = in.nextInt();
                System.out.println("Quante Capricciosi vuoi acquistare?");
                int c = in.nextInt();
                yield "ACQUISTA#" + m + "#" + v + "#" + c;
            default:
                yield "FINE";
        };
    }

    public static void main(String[] args) {
        ClientTCP client = new ClientTCP("localhost", 50000);
        client.connect();
        String tmp;
        do{
            tmp = menu();
            System.out.println("DA CLIENT>> " + tmp);
            client.send(tmp);
            System.out.println("DA SERVER<< " + client.recive());
        }while(!tmp.equals("FINE"));
        client.close();
    }
}
