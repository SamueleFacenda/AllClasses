package all.quinta.sockets;

import java.util.stream.IntStream;

public class ServerMenu {
    private static final int[] COSTI = {6,7,8};
    private static String response(String in){
        return switch(in){
            case "IM" -> "Pomodoro, mozzarella";
            case "IV" -> "Pomodoro, mozzarella, zucchine, melanzane, peperoni";
            case "IC" -> "Pomodoro, mozzarella, uovo, funghi, prosciutto";
            case "PM" -> String.valueOf(COSTI[0]);
            case "PV" -> String.valueOf(COSTI[1]);
            case "PC" -> String.valueOf(COSTI[2]);
            case "FINE" -> "Disconnessione...";
            default ->{
                if(!in.startsWith("ACQUISTA"))
                    yield "Comando non valido";
                String[] tmp = in.split("#");
                yield String.valueOf(
                        IntStream
                        .range(0,3)
                        .map(i -> Integer.parseInt(tmp[i+1]) * COSTI[i])
                        .sum()
                );
            }
        };
    }
    public static void main(String[] args) {
        ServerTCP server = new ServerTCP(50000);
        server.waitConnection();
        String tmp;
        do{
            tmp = server.recive();
            server.send(response(tmp));
        }while(!tmp.equals("FINE"));
        server.close();
    }
}
