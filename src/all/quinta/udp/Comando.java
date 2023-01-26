package all.quinta.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.function.Consumer;

public class Comando {
    protected DatagramSocket socket;
    protected byte[] bufferIN, bufferOUT;
    private final InetSocketAddress server;
    private final Consumer<String> print;

    public Comando(int port, String indirizzo, int portaServer, Consumer<String> print){
        this.print = print;
        try{
            socket = new DatagramSocket(port);
        }catch(Exception e){
            print.accept("Errore: " + e.getMessage());
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
            print.accept("Inserire comando: R(ichiedi), T(emperatura), F(ine)");
            messaggio = in.nextLine();
            if(messaggio.equalsIgnoreCase("F")){
                continua = false;
            }else{
                if(messaggio.equalsIgnoreCase("R")){
                    messaggio = "R";
                }else if(messaggio.equalsIgnoreCase("T")){
                    print.accept("Inserire temperatura acqua calda: ");
                    messaggio = "T#" + in.nextLine();
                    print.accept("Inserire temperatura livello comfort: ");
                    messaggio += "#" + in.nextLine();
                    print.accept("Inserire temperatura livello economico: ");
                    messaggio += "#" + in.nextLine();
                }else{
                    print.accept("Comando non valido");
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
                print.accept("Ricevuto: " + risposta);
            }
        }
        print.accept("Fine");
    }

    protected InetSocketAddress getServer(){
        return server;
    }

    public static void main(String[] args) {
        Comando comando = new Comando(5001, "localhost", 60000, System.out::println);
        comando.comunica();
    }
}
