package all.quarta.comparable;


import java.util.Objects;

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
    public int compareTo(Piatto com) {
        return po.getN() - com.getPortata().getN();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piatto piatto = (Piatto) o;
        return Double.compare(piatto.costo, costo) == 0 &&
                Objects.equals(nome, piatto.nome) &&
                po == piatto.po;
    }

    @Override
    public int hashCode() {
        return Objects.hash(costo, nome, po);
    }
}