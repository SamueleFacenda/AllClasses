package all.quarta.pescaoggetti;

/**
 * estensione della classe oggetto con un secondo valore detto malus
 * @author Samuele Facenda
 */
public class OggettoConMalus extends Oggetto{
    private int malus;
    public OggettoConMalus(int valore, String nome, int malus){
        super(valore,nome);
        this.malus=malus;
    }
    public int getMalus(){
        return malus;
    }
}
