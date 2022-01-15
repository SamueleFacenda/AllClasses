package all.pescaOggettiPoli.oggetto;

public class Forbice extends Oggetto {
    public Forbice(){
    }
    public int getMalus(){
        return 5;
    }
    @Override
    public int getValore(){
        return 1;
    }

    @Override
    public String toString() {
        return "Forbice";
    }
}
