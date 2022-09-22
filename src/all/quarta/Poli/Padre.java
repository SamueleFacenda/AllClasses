package all.quarta.Poli;

public class Padre {
    protected int pp;
    public Padre(){
        pp=0;
    }
    public Padre(int p){
        pp=p;
    }
    public int getPp(){
        return pp;
    }
    public void setPp(int in){
        pp=in;
    }
    public void incrementa(){
        pp++;
    }
    @Override
    public String toString() {
        return "Padre{" +
                "pp=" + pp +
                '}';
    }
}
