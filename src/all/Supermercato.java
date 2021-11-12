package all;

/**
 * classe Supermercato, caratterizzata dal catalogo di prodotti,
 * gestito come un'arraylist(con dimL), l'utilizzatore non deve essere a conoscenza di come
 * questo è gestito, basta che funzioni in qualunque caso, per questo non sono
 * presenti i metodi getDimF() e isFull(). Classe contenitore(rapporto di composizione) della
 * classe prodotto
 */
public class Supermercato {
    Prodotto[] catalogo;
    int dimL;

    /**
     * costruttore da lista di prodotti,
     * viene fatto il new dei prodotti inseriti, per garantire
     * la sicurezza della classe
     * @param in la lista da cui estrapolare il catalogo
     */
    public Supermercato(Prodotto[] in){
        dimL=in.length;
        catalogo=new Prodotto[dimL];
        for (int i = 0; i < dimL; i++)
            catalogo[i]=new Prodotto(in[i]);
    }

    /**
     * @return il numero di prodotti del catalogo
     */
    public int getNProdotti(){
        return dimL;
    }

    /**
     * restituisce una copia del prodotto in posizione i(start=0), se presente
     * @param i posizione in cui cercare il prodotto
     * @return null se l'indice è invalido, se no una copia del prodotto cercato
     */
    public Prodotto get(int i){
        try{
            return catalogo[i];
        }catch (Exception e){
            return null;
        }
    }
    private void resize(){
        Prodotto[] cambio=new Prodotto[catalogo.length==0?1: (int) Math.ceil(catalogo.length * 1.10)];
        for (int i = 0; i < catalogo.length; i++)
            cambio[i]=catalogo[i];
        catalogo=cambio;
    }
    private void shift(int i){
        while(i<dimL-1){
            catalogo[i]=catalogo[i+1];
            i++;
        }
    }

    /**
     * aggiunge un prodotto al catalogo, controlla che non ne sia
     * già presente uno con il medesimo codice a barre, se l'array é pieno
     * viene operato un resize
     * @param in
     */
    public void add(Prodotto in){
        if(indexOf(in)==-1){
            if(dimL== catalogo.length)
                resize();
            catalogo[dimL]=new Prodotto(in);
            dimL++;
        }
    }

    /**
     * cerca la posizione del prodotto inserito da parametro nel catalogo, tramite il codice a barre,
     * se non é presente ritorna -1
     * @param in il prodotto da cercare ne catalogo
     * @return la poszione del prodotto(start=0)
     */
    public int indexOf(Prodotto in){
        int i=0;
        while(i<dimL&&!catalogo[i].getCodiceABarre().equals(in.getCodiceABarre()))
            i++;
        return i==dimL?-1:i;
    }

    /**
     * rimuove il prodotto nella posizone inserita da parametro,
     * se questa esiste
     * @param index start=0
     */
    public void remove(int index){
        if(index<dimL){
            shift(index);
            dimL--;
        }
    }

    /**
     * metodo per la modifica del peso di un prodotto del catalogo
     * @param index posizione(start=0) del prodotto da modificare
     * @param newPeso nuovo peso da assegnare al prodotto
     */
    public void modificaPeso(int index,double newPeso){
        if(index<dimL)
            catalogo[index].setPeso(newPeso);
    }
    /**
     * metodo per la modifica del costo di un prodotto del catalogo
     * @param index posizione(start=0) del prodotto da modificare
     * @param newCosto nuovo costo da assegnare al prodotto
     */
    public void modificaCosto(int index,double newCosto){
        if(index<dimL)
            catalogo[index].setCosto(newCosto);
    }

    @Override
    public String toString() {
        String out="Supermercato: \n";
        for (int i = 0; i < dimL; i++)
            out+=i+": "+catalogo[i]+"/n";
        return out;
    }
}
