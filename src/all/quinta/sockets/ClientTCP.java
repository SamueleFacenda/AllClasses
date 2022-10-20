package all.quinta.sockets;

import java.io.*;
import java.net.Socket;

public class ClientTCP {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private DataOutputStream outData;
    private String host;
    private int porta;
    public ClientTCP(String host, int porta) {
        this.host = host;
        this.porta = porta;
    }
    public void connect(){
        try {
            socket = new Socket(host, porta);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            outData = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void send(String msg){
        out.println(msg);
    }
    public String recive(){
        String msg = null;
        try {
            msg = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
