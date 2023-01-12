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
    private Map<String, AtomicInteger> counter;
    private boolean differentPortsDifferentClients;
    public ServerEsercitazione(int porta, boolean differentPortsDifferentClients){
        try{
            socket = new DatagramSocket(porta);
        }catch(Exception e){
            System.out.println("Errore: " + e.getMessage());
        }
        bufferIN = new byte[1024];
        counter = new HashMap<>();
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
            key = receivePacket.getAddress().getHostAddress() +(differentPortsDifferentClients ? ":" + receivePacket.getPort() : "");
            if(!counter.containsKey(key) && counter.size() >= 10)
                risposta = "Non posso fornire il servizio! Massimo numero di client raggiunto";
            else{
                if(!counter.containsKey(key))
                    counter.put(key, new AtomicInteger(0));
                tmp = counter.get(key).incrementAndGet();
                if(tmp > 10)
                    risposta = "ha esaurito il bonus gratuito e da questo momento il servizio orario è a pagamento";
                else
                    risposta = new java.util.Date().toString();
            }
            bufferOUT = risposta.getBytes();
            sendPacket = new DatagramPacket(bufferOUT, bufferOUT.length, receivePacket.getAddress(), receivePacket.getPort());
            try{
                socket.send(sendPacket);
            }catch(IOException e){
                System.err.println("Errore: " + e.getMessage());
            }
            System.out.println("Map("+counter.size()+","+counter.keySet().stream()
                    .map(ke -> ke + "=" + counter.get(ke))
                    .collect(Collectors.joining(", ", "{", "}"))+")");
        }
    }

    public static void main(String[] args) {
        ServerEsercitazione server = new ServerEsercitazione(5000, false);
        server.serve();
    }
}
