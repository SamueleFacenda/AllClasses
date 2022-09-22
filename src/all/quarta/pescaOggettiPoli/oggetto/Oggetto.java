package all.quarta.pescaOggettiPoli.oggetto;

public class Oggetto {
    public Oggetto(){
    }
    public int getValore(){
        return 0;
    }
    @Override
    public String toString() {
        return "Oggetto, valore: "+getValore();
    }
}
