package all.bag;

import java.util.Stack;

public class Codifica {
    public static char convert(int num){
        return (char)(num+(num<10?48:55));
    }
    public static int deconvert(char num){
        return (int)num-((int)num<65?48:55);
    }
    public static String codifica(int num,int base){
        Stack<Integer> s=new Stack<>();
        while(num!=0){
            s.push(num%base);
            num=num/base;
        }
        String out="";
        while(!s.isEmpty()){
            out+=convert(s.peek());
            s.pop();
        }
        return out;
    }
    public static int decodifica(String num,int base){
        int out=0,i=num.length()-1;
        while(i>=0){
            out+=deconvert(num.charAt(i))*Math.pow(base,num.length()-i-1);
            i--;
        }
        return out;
    }
}
