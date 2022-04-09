package all.bag;

import java.util.Stack;

public class GiraRighe {
    public static String gira(String in){
        String[] righe=in.split("\n");
        Stack<String> rigaDritta=new Stack<>();
        for(String riga: righe)
            rigaDritta.push(riga);
        String out="";
        while(!rigaDritta.empty())
            out+=rigaDritta.pop()+"\n";
        return out;
    }
}
