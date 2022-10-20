package all.quinta.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Predicate;

public class ClientLettere {
    static class NuovoClient{
        private Socket socket;
        private BufferedReader br;
        private PrintWriter pw;
        public NuovoClient(String host, int port){
            try{
                socket = new Socket(host, port);
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
        return (int) Arrays.stream(cha)
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
    private static String getParola(){
        if(Math.random()>0.9){
            //parola a caso
            return new Random().ints(48, 122 + 1)
                    .limit(100)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
        }else{
            String tmp = new Random().ints(48, 122 + 1)
                    .limit(100)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            int voc = nVocali(tmp);
            int cons = nConsonanti(tmp);
            if(cons == voc / 2)
                return tmp;
            if(cons > voc / 2){
                int repeat = cons * 2 - voc;
                return tmp + "a".repeat(repeat);
            }else{
                if(voc % 2 != 0){
                    voc++;
                    tmp += "a";
                }
                int repeat = voc / 2 - cons;
                return tmp + "v".repeat(repeat);
            }
        }
    }
    public static void main(String[] args) {
        NuovoClient nc = new NuovoClient("localhost", 6789);
        String tmp;
        do{
            nc.send(getParola());
            tmp = nc.recive();
            System.out.println(tmp);
        }while(!tmp.equals("FINE"));
        nc.chiudi();
    }
}
