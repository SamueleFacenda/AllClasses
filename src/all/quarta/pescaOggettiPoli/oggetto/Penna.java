package all.quarta.pescaOggettiPoli.oggetto;

public class Penna extends Oggetto{
    private boolean isStylo;
    public Penna(boolean isStylo){
        this.isStylo=isStylo;
    }
    @Override
    public int getValore(){
        return isStylo?5:4;
    }

    @Override
    public String toString() {
        return "Penna"+(isStylo?" stilografica":"");
    }
}
