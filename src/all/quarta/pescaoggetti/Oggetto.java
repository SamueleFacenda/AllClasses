package all.quarta.pescaoggetti;

/**
 * classe che rappresenta un oggetto con un punteggio. C'è anche il nome dell'oggetto.
 * @author Samuele Facenda
 */
public class Oggetto {
    private int valore;
    private String nome;
    public Oggetto(int valore, String nome){
        this.valore=valore;
        this.nome=nome;
    }
    public String getNome(){
        return nome;
    }
    public int getValore(){
        return valore;
    }
}
