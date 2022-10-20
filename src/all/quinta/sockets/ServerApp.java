package all.quinta.sockets;

import java.util.Scanner;

public class ServerApp {
    private static final int PORT = 9999;

    public static void main(String[] args) {
        ServerTCP server = new ServerTCP(PORT);
        server.waitConnection();
        Scanner in = new Scanner(System.in);
        System.out.println(server.recive());
        server.send(in.nextLine());
        server.close();
    }
}
