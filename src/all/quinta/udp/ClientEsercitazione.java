package all.quinta.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class ClientEsercitazione {
    private DatagramSocket socket;
    private byte[] bufferIN, bufferOUT;
    private InetSocketAddress address;
    public ClientEsercitazione(String indirizzo, int portaServer){
        try{
            socket = new DatagramSocket(portaServer +(int) (Math.random()*1000 + 1));
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
        while(!messaggio.equalsIgnoreCase("FINE")){
            System.out.println("Inviare messaggio al server?(y/n)");
            messaggio = in.nextLine();
            if(messaggio.equalsIgnoreCase("Y")){
                bufferOUT = messaggio.getBytes();
                sendPacket = new DatagramPacket(bufferOUT, bufferOUT.length, address);
                try {
                    socket.send(sendPacket);
                } catch (IOException e) {
                    System.err.println("Errore: " + e.getMessage());
                }

                try {
                    socket.receive(receivePacket);
                } catch (IOException e) {
                    System.err.println("Errore: " + e.getMessage());
                }
                risposta = new String(receivePacket.getData());
                risposta = risposta.substring(0, receivePacket.getLength());
                System.out.println("Ricevuto: " + risposta);
            }


        }
    }

    public static void main(String[] args) {
        ClientEsercitazione client = new ClientEsercitazione("localhost", 5000);
        client.comunica();
    }
}
