package all.quinta.sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private DataOutputStream outData;
    private int porta;
    public ServerTCP(int porta){
        this.porta = porta;
    }
    public void waitConnection(){
        try(ServerSocket serverSocket = new ServerSocket(porta)) {
            socket = serverSocket.accept();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            outData = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public void send(String msg){
        out.println(msg);
    }
}
