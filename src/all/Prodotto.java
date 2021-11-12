package all;

/**
 * classe per la memorizzazione di un prodotto vendibile,
 * caratterizzata dal codice a barre del prodotto(identificativo univoco dello stesso,
 * il cambio del codice a barre significa la creazoine di un nuovo prodotto, per questo
 * non é presente il metodo set), dal costo e dal peso per unità
 * @author Samuele Facenda
 */
public class Prodotto {

    private String codiceABarre;
    private double peso,costo;

    /**
     * costruttore completo
     * @param codiceABarre il ocdice del prodotto
     * @param peso il peso del prodotto
     * @param costo il costo per unitá del prodotto
     */
    public Prodotto(String codiceABarre, double peso, double costo) {
        this.codiceABarre = codiceABarre;
        this.peso = peso;
        this.costo = costo;
    }

    /**
     * costruttore copia di Prodotto, usato nei metodi set delle classi
     * contenitore per garantire la sicurezza della classe
     * @param in prodotto da cui copiare i dati
     */
    public Prodotto(Prodotto in) {
        this.codiceABarre = in.codiceABarre;
        this.peso = in.peso;
        this.costo = in.costo;
    }

    /**
     * autoesplicativo
     * @return il codice a barre del prodotto
     */
    public String getCodiceABarre() {
        return codiceABarre;
    }

    /**
     * autoesplicativo
     * @return il peso del prodotto
     */
    public double getPeso() {
        return peso;
    }

    /**
     * autoesplicativo
     * @return il costo per unità del prodotto
     */
    public double getCosto() {
        return costo;
    }

    /**
     * autoesplicativo
     * @param peso il nuovo peso da assegnare al prodotto
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * autoesplicativo
     * @param costo il nuovo costo da assegnare al prodotto
     */
    public void setCosto(double costo) {
        this.costo = costo;
    }

    @Override
    public String toString(){
        return "prodotto:\ncodice a barre: "+codiceABarre+"\npeso: "+peso+"\ncosto: "+costo;
    }
}
