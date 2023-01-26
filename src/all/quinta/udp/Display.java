package all.quinta.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;

public class Display {
    private final int[] temperatura = {40, 18, 12};
    protected static final List<String> NOME_TEMPERATURA = List.of(
            "acqua calda",
            "livello comfort",
            "livello economia"
    );
    private static final int[][] RANGE = {
        {40, 55},
        {18, 30},
        {12, 16}
    };
    private DatagramSocket socket;
    private byte[] bufferIN, bufferOUT;
    private final Map<InetSocketAddress, String> register;
    private final Consumer<String> print;

    public Display(int port, Consumer<String> print){
        this.print = print;
        try{
            socket = new DatagramSocket(port);
        }catch(Exception e){
            print.accept("Errore: " + e.getMessage());
        }
        bufferIN = new byte[1024];
        register = new HashMap<>();
    }
    public int getTemperatura(int index){
        return temperatura[index];
    }

    public void serve(){
        print.accept("Server partito in esecuzione...");
        print.accept("Temperatura impostata: ");
        for(int i = 0; i < temperatura.length; i++){
            print.accept("Temperatura " + NOME_TEMPERATURA.get(i) + ": " + temperatura[i]);
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

            // get the message
            messaggio = new String(receivePacket.getData());
            messaggio = messaggio.substring(0, receivePacket.getLength());

            // get the address and update the register
            address = (InetSocketAddress) receivePacket.getSocketAddress();
            now = LocalDateTime.now().format(formatter);
            if (!register.containsKey(address)){
                print.accept("Nuovo client connesso: " + address);
            }else{
                print.accept("Client: " + address);
                print.accept("Ultimo accesso: " + register.get(address));
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
                        print.accept("Impostata temperatura alle: " + now);
                        for (int i = 0; i < 3; i++) {
                            temperatura[i] = Integer.parseInt(command[i + 1]);
                            print.accept("Temperatura " + NOME_TEMPERATURA.get(i) + ": " + temperatura[i]);
                        }
                        yield "Temperatura regolata: " + now;
                    } else {
                        print.accept("Errore per il client " + address + ". Temperature non impostate");
                        sb = new StringBuilder();
                        for (int i = 0; i < 3; i++) {
                            sb.append(" Temperatura ").append(NOME_TEMPERATURA.get(i)).append(": ").append(error[i]);
                        }
                        yield sb.toString();
                    }
                }
                case "R" -> {
                    // case R return the temperature
                    sb = new StringBuilder();
                    for (int i = 0; i < 3; i++) {
                        sb.append(" Temperatura ").append(NOME_TEMPERATURA.get(i)).append(": ").append(temperatura[i]);
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
        Display server = new Display(60000, System.out::println);
        server.serve();
    }
}
