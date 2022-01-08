package all.pescaOggettiPoli.oggetto;

public class Gomma extends Oggetto{
    public Gomma(boolean isRossa){
        if(isRossa)
            valore=-1;
        else
            valore=2;
    }

    @Override
    public String toString() {
        return "Gomma"+(valore==2?"":" rossa");
    }
}
