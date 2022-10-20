package all.quinta.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

public class ServerLettere {
    static class NuovoServer{
        private Socket socket;
        private ServerSocket ssocket;
        private BufferedReader br;
        private PrintWriter pw;
        public NuovoServer(int port){
            try{
                ssocket = new ServerSocket(port);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        public void accettaConnessione(){
            try{
                socket = ssocket.accept();
                ssocket.close();
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                pw = new PrintWriter(socket.getOutputStream(), true);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        public void send(String in){
            pw.println(in);
        }
        public String recive(){
            try {
                return br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }
        public void chiudi(){
            try{
                socket.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    private static final String VOCALI = "aeiou";
    private static int countInString(String in, Predicate<Character> fun){
        Character[] cha = in.chars().mapToObj(c -> (char)c).toArray(Character[]::new);
        return (int)Arrays.stream(cha)
                .filter(fun)
                .count();
    }
    private static int nConsonanti(String in){
        in = in.toLowerCase();
        return countInString(in, c -> Character.isAlphabetic((int)c) && !VOCALI.contains(c.toString()));
    }
    private static int nVocali(String in){
        in = in.toLowerCase();
        return countInString(in, c -> VOCALI.contains(c.toString()));
    }
    public static void main(String[] args) {
        NuovoServer ns = new NuovoServer(6789);
        ns.accettaConnessione();
        int voc, cons;
        String tmp;
        do{
            tmp = ns.recive();
            voc = nVocali(tmp);
            cons = nConsonanti(tmp);
            if(cons == voc / 2)
                ns.send("vocali: " + voc + ", consonanti: " + cons);
            else
                ns.send("FINE");
        }while(cons == voc / 2);
        ns.chiudi();
    }
}
