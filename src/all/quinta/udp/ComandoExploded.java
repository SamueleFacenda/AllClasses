package all.quinta.udp;

import java.net.DatagramPacket;
import java.util.function.Consumer;

public class ComandoExploded extends Comando{

    public ComandoExploded(int port, String indirizzo, int portaServer, Consumer<String> print) {
        super(port, indirizzo, portaServer, print);
    }

    public String updateTemperature(int[] temperature){
        String messaggio = "T#" + temperature[0] + "#" + temperature[1] + "#" + temperature[2];
        return sendStringAndReciveRespone(messaggio);
    }

    public int[] readValues(){
        String out = sendStringAndReciveRespone("R");
        //extract integers from response
        int i=0;
        int[] values = new int[3];
        for(String s : out.split(" ")){
            // if value is an integer
            if(s.matches("\\d+")){
                values[i] = Integer.parseInt(s);
                i++;
            }
        }
        return values;
    }

    private String sendStringAndReciveRespone(String in){
        bufferOUT = in.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(bufferOUT, bufferOUT.length, getServer());
        try {
            socket.send(sendPacket);
        } catch (Exception e) {
            System.err.println("Errore: " + e.getMessage());
        }
        //recive response
        DatagramPacket receivePacket = new DatagramPacket(bufferIN, bufferIN.length);
        try {
            socket.receive(receivePacket);
        } catch (Exception e) {
            System.err.println("Errore: " + e.getMessage());
        }
        return new String(receivePacket.getData(), 0, receivePacket.getLength());
    }
}
