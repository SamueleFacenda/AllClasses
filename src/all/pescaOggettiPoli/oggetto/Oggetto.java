package all.pescaOggettiPoli.oggetto;

public class Oggetto {
    protected int valore;
    public Oggetto(){
        this.valore= 0;
    }
    public int getValore(){
        return valore;
    }
    @Override
    public String toString() {
        return "Oggetto, valore: "+valore;
    }
}
