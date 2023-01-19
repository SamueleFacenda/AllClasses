package all.quinta.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class Comando {
    private DatagramSocket socket;
    private byte[] bufferIN, bufferOUT;
    private InetSocketAddress server;

    public Comando(int port, String indirizzo, int portaServer){
        try{
            socket = new DatagramSocket(port);
        }catch(Exception e){
            System.out.println("Errore: " + e.getMessage());
        }
        bufferIN = new byte[1024];
        server = new InetSocketAddress(indirizzo, portaServer);
    }

    public void comunica(){
        Scanner in = new Scanner(System.in);
        String messaggio = "", risposta;
        boolean continua = true;
        DatagramPacket receivePacket, sendPacket;
        while(continua){
            System.out.println("Inserire comando: R(ichiedi), T(emperatura), F(ine)");
            messaggio = in.nextLine();
            if(messaggio.equalsIgnoreCase("F")){
                continua = false;
            }else{
                if(messaggio.equalsIgnoreCase("R")){
                    messaggio = "R";
                }else if(messaggio.equalsIgnoreCase("T")){
                    System.out.println("Inserire temperatura acqua calda: ");
                    messaggio = "T#" + in.nextLine();
                    System.out.println("Inserire temperatura livello comfort: ");
                    messaggio += "#" + in.nextLine();
                    System.out.println("Inserire temperatura livello economico: ");
                    messaggio += "#" + in.nextLine();
                }else{
                    System.out.println("Comando non valido");
                    continue;
                }

                bufferOUT = messaggio.getBytes();
                sendPacket = new DatagramPacket(bufferOUT, bufferOUT.length, server);
                try {
                    socket.send(sendPacket);
                } catch (Exception e) {
                    System.err.println("Errore: " + e.getMessage());
                }

                // ricezione risposta
                receivePacket = new DatagramPacket(bufferIN, bufferIN.length);
                try {
                    socket.receive(receivePacket);
                } catch (Exception e) {
                    System.err.println("Errore: " + e.getMessage());
                }
                risposta = new String(receivePacket.getData());
                risposta = risposta.substring(0, receivePacket.getLength());
                System.out.println("Ricevuto: " + risposta);
            }
        }
        System.out.println("Fine");
    }

    public static void main(String[] args) {
        Comando comando = new Comando(5001, "localhost", 60000);
        comando.comunica();
    }
}
