package all.quinta.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Display {
    private final int[] temperatura = {40, 18, 12};
    private static final String[] NOME_TEMPERATURA = {"acqua calda", "livello comfort", "livello economia"};
    private static final int[][] RANGE = {
        {40, 55},
        {18, 30},
        {12, 16}
    };
    private DatagramSocket socket;
    private byte[] bufferIN, bufferOUT;
    private final Map<InetSocketAddress, String> register;

    public Display(int port){
        try{
            socket = new DatagramSocket(port);
        }catch(Exception e){
            System.out.println("Errore: " + e.getMessage());
        }
        bufferIN = new byte[1024];
        register = new HashMap<>();
    }

    public void serve(){
        System.out.println("Server partito in esecuzione...");
        System.out.println("Temperatura impostata: ");
        for(int i = 0; i < temperatura.length; i++){
            System.out.println("Temperatura " + NOME_TEMPERATURA[i] + ": " + temperatura[i]);
        }
        DatagramPacket receivePacket, sendPacket;
        receivePacket = new DatagramPacket(bufferIN, bufferIN.length);
        String messaggio, risposta, now;
        String[] command, error = {"ok", "ok", "ok"};
        InetSocketAddress address;
        StringBuilder sb;
        boolean ok;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        while (true){
            // recive a packet
            try {
                socket.receive(receivePacket);
            } catch (Exception e) {
                System.err.println("Errore: " + e.getMessage());
            }
            now = LocalDateTime.now().format(formatter);

            // get the message
            messaggio = new String(receivePacket.getData());
            messaggio = messaggio.substring(0, receivePacket.getLength());

            // get the address
            address = (InetSocketAddress) receivePacket.getSocketAddress();
            if (!register.containsKey(address)){
                System.out.println("Nuovo client connesso: " + address);
            }else{
                System.out.println("Client: " + address);
                System.out.println("Ultimo accesso: " + register.get(address));
            }
            register.put(address, now);

            // process the message
            command = messaggio.split("#");
            risposta = switch (command[0]){
                case "T" -> {
                    // case T update the temperature
                    // check if temperatures are in range
                    ok = true;
                    for (int i = 0; i < 3; i++) {
                        if (Integer.parseInt(command[i + 1]) < RANGE[i][0]) {
                            ok = false;
                            error[i] = "Troppo bassa!!!";
                        } else if (Integer.parseInt(command[i + 1]) > RANGE[i][1]) {
                            ok = false;
                            error[i] = "Troppo alta!!!";
                        } else {
                            error[i] = "ok";
                        }
                    }
                    if (ok) {
                        System.out.println("Impostata temperatura alle: " + now);
                        for (int i = 0; i < 3; i++) {
                            temperatura[i] = Integer.parseInt(command[i + 1]);
                            System.out.println("Temperatura " + NOME_TEMPERATURA[i] + ": " + temperatura[i]);
                        }
                        yield "Temperatura regolata: " + now;
                    } else {
                        System.out.println("Errore per il client " + address + ". Temperature non impostate");
                        sb = new StringBuilder();
                        for (int i = 0; i < 3; i++) {
                            sb.append(" Temperatura ").append(NOME_TEMPERATURA[i]).append(": ").append(error[i]);
                        }
                        yield sb.toString();
                    }
                }
                case "R" -> {
                    // case R return the temperature
                    sb = new StringBuilder();
                    for (int i = 0; i < 3; i++) {
                        sb.append(" Temperatura ").append(NOME_TEMPERATURA[i]).append(": ").append(temperatura[i]);
                    }
                    yield sb.toString();
                }
                default -> "Comando non riconosciuto";
            };

            // send the response
            bufferOUT = risposta.getBytes();
            sendPacket = new DatagramPacket(bufferOUT, bufferOUT.length, address);
            try {
                socket.send(sendPacket);
            } catch (Exception e) {
                System.err.println("Errore: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Display server = new Display(60000);
        server.serve();
    }
}
