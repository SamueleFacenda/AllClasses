package all.quinta.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ServerEsercitazione {
    private DatagramSocket socket;
    private byte[] bufferIN, bufferOUT;
    private final Map<String, AtomicInteger> clientRegister;
    private final boolean differentPortsDifferentClients;
    public ServerEsercitazione(int porta, boolean differentPortsDifferentClients){
        try{
            socket = new DatagramSocket(porta);
        }catch(Exception e){
            System.out.println("Errore: " + e.getMessage());
        }
        bufferIN = new byte[1024];
        clientRegister = new HashMap<>();
        this.differentPortsDifferentClients = differentPortsDifferentClients;
    }
    public void serve(){
        DatagramPacket sendPacket,
                receivePacket = new DatagramPacket(bufferIN, bufferIN.length);
        int tmp;
        String key, risposta;
        while(true){
            // recive a message
            try{
                socket.receive(receivePacket);
            }catch(IOException e){
                System.err.println("Errore: " + e.getMessage());
            }
            // the key for the register is the address of the client
            // if the server is configured to consider different ports as different clients
            // the key is the address and the port of the client
            key = receivePacket.getAddress().getHostAddress() +(differentPortsDifferentClients ? ":" + receivePacket.getPort() : "");
            if(!clientRegister.containsKey(key) && clientRegister.size() >= 10)
                risposta = "Non posso fornire il servizio! Massimo numero di client raggiunto";
            else{
                if(!clientRegister.containsKey(key))
                    clientRegister.put(key, new AtomicInteger(0));

                tmp = clientRegister.get(key).incrementAndGet();
                if(tmp > 10)
                    risposta = "ha esaurito il bonus gratuito e da questo momento il servizio orario è a pagamento";
                else
                    risposta = new java.util.Date().toString();
            }

            // send the response
            bufferOUT = risposta.getBytes();
            sendPacket = new DatagramPacket(bufferOUT, bufferOUT.length, receivePacket.getAddress(), receivePacket.getPort());
            try{
                socket.send(sendPacket);
            }catch(IOException e){
                System.err.println("Errore: " + e.getMessage());
            }

            // print the client register
            System.out.println("Map("+ clientRegister.size()+","+ clientRegister.keySet().stream()
                    .map(ke -> ke + "=" + clientRegister.get(ke))
                    .collect(Collectors.joining(", ", "{", "}"))+")");
        }
    }

    public static void main(String[] args) {
        ServerEsercitazione server = new ServerEsercitazione(5000, true);
        server.serve();
    }
}
