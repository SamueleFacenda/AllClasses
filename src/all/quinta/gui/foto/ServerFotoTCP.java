package all.quinta.gui.foto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFotoTCP {
    private int port;
    private String host = "10.0.75.21";

    private Socket socket;
    private PrintWriter pw;
    private BufferedReader br;

    public ServerFotoTCP(){
        ServerFoto.getInstance().launch();

    }

    private void connect(){
        try(ServerSocket ssocket = new ServerSocket(port)){
            socket = ssocket.accept();
            pw = new PrintWriter(socket.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void stampaCmd(String in){
        ServerFoto.getInstance().stampaCmd(in);
    }

    public static void main(String[] args) {
        new ServerFotoTCP();
    }

}
