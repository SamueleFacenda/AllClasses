package all.pescaOggettiPoli.oggetto;

public class Penna extends Oggetto{
    public Penna(boolean isStylo){
        if(isStylo)
            valore=5;
        else
            valore=4;
    }

    @Override
    public String toString() {
        return "Penna"+(valore==5?" stilografica":"");
    }
}
