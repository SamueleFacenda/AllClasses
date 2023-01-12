package all.quinta.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class ClientUdp_Uno {
    private DatagramSocket socket;
    private byte[] bufferIN, bufferOUT;
    private InetSocketAddress address;
    public ClientUdp_Uno(int porta, String indirizzo, int portaServer){
        try{
            socket = new DatagramSocket(porta);
        }catch(Exception e){
            System.out.println("Errore: " + e.getMessage());
        }
        bufferIN = new byte[1024];
        address = new InetSocketAddress(indirizzo, portaServer);
    }
    public void comunica(){
        DatagramPacket receivePacket, sendPacket;
        Scanner in = new Scanner(System.in);
        receivePacket = new DatagramPacket(bufferIN, bufferIN.length);
        String messaggio = "", risposta;
        while(!messaggio.toUpperCase().equals("FINE")){
            System.out.println("Inserisci il messaggio da inviare al server: ");
            messaggio = in.nextLine();

            bufferOUT = messaggio.getBytes();
            sendPacket = new DatagramPacket(bufferOUT, bufferOUT.length, address);
            try{
                socket.send(sendPacket);
            }catch(IOException e){
                System.err.println("Errore: " + e.getMessage());
            }

            try{
                socket.receive(receivePacket);
            }catch(IOException e){
                System.err.println("Errore: " + e.getMessage());
            }
            risposta = new String(receivePacket.getData());
            risposta = risposta.substring(0, receivePacket.getLength());
            System.out.println("Ricevuto: " + risposta);


        }
    }

    public static void main(String[] args) {
        ClientUdp_Uno client = new ClientUdp_Uno(5001, "localhost", 5000);
        client.comunica();
    }
}
