package all.quinta.sockets;


import java.util.Scanner;

public class ClientApp {
    private static final String HOST = "10.0.75.20";
    private static final int PORT = 6789;

    public static void main(String[] args) {
        ClientTCP client = new ClientTCP(HOST, PORT);
        client.connect();
        System.out.println("connected to server");
        Scanner in = new Scanner(System.in);
        String tmp;
        do{
            client.send(in.nextLine());
            tmp = client.recive();
            System.out.println(tmp);
        }while(tmp != null && !tmp.equals("exit"));
        client.close();
    }
}
