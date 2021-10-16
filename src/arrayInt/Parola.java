package arrayInt;

public class Parola {
    char[] par;
    public Parola(char[] par){
        this.par=new char[par.length];
        for(int i=0;i<par.length;i++)
            this.par[i]=par[i];
    }
    public char[] codifica(){
        char[] out=new char[(int)Math.ceil((double)par.length/2)];
        for(int i=par.length%2==0?par.length-2:par.length-1;i>=0;i-=2)
            out[(par.length-1-i)/2]=par[i];
        return out;
    }
    @Override
    public String toString(){
        String out="";
        for(char c:par)
            out+=c;
        return out;
    }
}
