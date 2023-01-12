package all.quinta.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerUdp_Uno {
    private DatagramSocket socket;
    private byte[] bufferIN, bufferOUT;
    public ServerUdp_Uno(int porta){
        try{
            socket = new DatagramSocket(porta);
        }catch(Exception e){
            System.out.println("Errore: " + e.getMessage());
        }
        bufferIN = new byte[1024];
    }
    public void comunica(){
        DatagramPacket receivePacket, sendPacket;
        receivePacket = new DatagramPacket(bufferIN, bufferIN.length);
        String ricevuto="", risposta;
        while(!ricevuto.toUpperCase().equals("FINE")){
            try{
                socket.receive(receivePacket);

            }catch(IOException e){
                System.err.println("Errore: " + e.getMessage());
            }
            ricevuto = new String(receivePacket.getData());
            ricevuto = ricevuto.substring(0, receivePacket.getLength());
            System.out.println("Ricevuto: " + ricevuto);

            risposta = ricevuto.toUpperCase();
            bufferOUT = risposta.getBytes();
            sendPacket = new DatagramPacket(bufferOUT, bufferOUT.length, receivePacket.getAddress(), receivePacket.getPort());
            try{
                socket.send(sendPacket);
            }catch(IOException e){
                System.err.println("Errore: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        ServerUdp_Uno server = new ServerUdp_Uno(5000);
        server.comunica();
    }
}
