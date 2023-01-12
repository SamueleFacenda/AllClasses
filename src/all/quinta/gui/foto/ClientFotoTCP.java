package all.quinta.gui.foto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientFotoTCP {
    private int port = 4444;
    private String host = "localhost";
    private Socket socket;
    private PrintWriter pw;
    private BufferedReader br;
    public void connetti(){
        try {
            socket = new Socket(host, port);
            pw = new PrintWriter(socket.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
