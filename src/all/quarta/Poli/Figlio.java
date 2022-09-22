package all.quarta.Poli;

public class Figlio extends Padre {
    private String ff;
    public Figlio(){
        super();
        ff="figlio";
    }
    public Figlio(int in){
        super(in);
        ff="figlio";
    }
    public String getFf(){
        return ff;
    }
    @Override
    public void incrementa(){
        super.incrementa();
        ff="ho modificato il metodo del padre";
    }
    @Override
    public String toString() {
        return "Figlio{" +
                "ff='" + ff + '\'' +
                ", pp=" + pp +
                '}';
    }
}
