package all.pescaOggettiPoli.oggetto;

public class Forbice extends Oggetto {
    public Forbice(){
        valore=1;
    }
    public int getMalus(){
        return 5;
    }

    @Override
    public String toString() {
        return "Forbice";
    }
}
