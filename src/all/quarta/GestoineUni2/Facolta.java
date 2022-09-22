package all.quarta.GestoineUni2;

/**
 * classe per la gestone di una facolta, caratterizzata dal nome e
 * dalle variabili per l'assegnazione di una nuova matricola a un nuovo studente,
 * newMatricola viene incrementato mano a mano che assegno nuove matricole, come fosse
 * una dimensione logica di un array.La massima matricola non é compresa tra quelle assegnabili
 * @author Samuele Facenda
 */
public class Facolta {
    private String nome;
    private int newMatricola,maxMatricola;//maxMatricola non è incluso

    /**
     * costruttore completo
     * @param nome nome della facolta
     * @param newMatricola prima matricola assegnabile
     * @param maxMatricola matricola massima assegnabile+1
     */
    public Facolta(String nome, int newMatricola, int maxMatricola) {
        this.nome = nome;
        this.newMatricola = newMatricola;
        this.maxMatricola = maxMatricola;
    }

    /**
     * autoesplicativo
     * @return nome della facolta
     */
    public String getNome(){
        return nome;
    }

    /**
     * se ci sono ancora matricole assegnabili
     * @return
     */
    public boolean areMatricoleEndend(){
        return newMatricola==maxMatricola;
    }

    /**
     * nuova matricola, con controllo
     * @return nuova matricola
     * @throws Exception se le matricole per la facolta sono finite
     */
    public int generateNewMatricola() throws Exception {
        if(areMatricoleEndend())
            throw new Exception("non ci sono piú matricole disponibili");
        //il costrutto n++ restituisce un valore e poi lo incrementa
        return newMatricola++;
    }

    /**
     * autoesplicativo
     * @return massima matrocila assegnabile +1
     */
    public int getMaxMatricola(){
        return maxMatricola;
    }

    /**
     * restituisce una nuova istanza della facolta
     * @return nuova istanza della classe
     */
    public Facolta getCopy(){
        return new Facolta(nome,newMatricola,maxMatricola);
    }
    @Override
    public String toString(){
        return "Facoltá: "+nome+"\nprossima matricola: "+newMatricola+"\nmassima matricola: "+maxMatricola;
    }
}
