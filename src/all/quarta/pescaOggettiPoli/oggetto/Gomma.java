package all.quarta.pescaOggettiPoli.oggetto;

public class Gomma extends Oggetto{
    private boolean isRossa;
    public Gomma(boolean isRossa){
        this.isRossa=isRossa;
    }
    @Override
    public int getValore(){
        return isRossa?-1:2;
    }

    @Override
    public String toString() {
        return "Gomma"+(isRossa?" rossa ":"");
    }
}
