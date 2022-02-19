package all.comparable;


public class Piatto implements Comparable<Piatto> {
    //PASTA(10,"pasta",Portata.PRIMO),BISTECCHA(45,"bis_tecca",Portata.SECONDO),PATATE(12,"patate",Portata.CONTORNO);

    public enum Portata{
        PRIMO(1), SECONDO(2), CONTORNO(3);
        int n;
        private Portata(int n){
            this.n=n;
        }
        public int getN(){
            return n;
        }
    }

    private double costo;
    private String nome;
    private Portata po;

    Piatto(double costo, String nome, Portata po) {
        this.costo = costo;
        this.nome = nome;
        this.po=po;
    }
    public Portata getPortata(){
        return po;
    }

    public double getCosto() {
        return costo;
    }

    public String getNome() {
        return nome;
    }

    public Portata getPo() {
        return po;
    }
    public int compareTo(Piatto com){
        return po.getN()-com.getPortata().getN();
    }
}
