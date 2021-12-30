package all.pescaoggetti;

/**
 * classe per il gioco pesca-oggetti. Regole, c'è un tabellone con delle celle nascoste, a turno chi gioca
 * sceglie una cella e ne controlla il contenuto, ci può essere un oggetto, normalmente questo oggetto
 * aumenta o riduce il punteggio di chi l'ha pescato, ma ci sono oggetti speciali che riducono il punteggio
 * anche degli altri giocatori.
 * Vengono inoltre salvati in ordine i nomi degli oggetti pescati, il turno non passa finchè non viene pescato un
 * oggetto, dopo che l'ho pescato lo tolgo dal tabellone.
 * Una cella con valore 0 vuol dire che è già stata controllata
 */
public class Partita {
    private int giocatoreCorrente,nOggettiMancanti;
    private int[] punteggio;
    private String[] pescate;
    private Oggetto[][] tabellone;
    private String messageAfterDraw;

    /**
     * unico costruttore con controllo dell'input, visto che il numero di oggetti è nRighe*4, l'area
     * della tabella deve essere maggiore.
     * @param nGiocatori numero di giocatori, deve essere uno o più
     * @param nRighe numero di righe(N)
     * @param nColonne numero di colonne(M)
     * @throws Exception se i valori non sono validi per una partita
     */
    public Partita(int nGiocatori, int nRighe, int nColonne)throws Exception{
        //controllo l'area della tabella
        if(nRighe*nColonne<nRighe*4)
            throw new Exception("valore di righe e colonne troppo basso");
        if(nGiocatori<1)
            throw new Exception("valore di giocatori invalido");
        //inizializzo tutto
        punteggio=new int[nGiocatori];
        //il numero massimo di pescate è uguale al numero di oggetti, creo già l'array di quella dimensione, così no va più ridimensionato
        pescate=new String[nRighe*4];
        tabellone=new Oggetto[nRighe][nColonne];
        giocatoreCorrente=0;
        nOggettiMancanti=nRighe*4;
        //aggiungo tutti gli oggetti al tabellone, in posizioni casuali
        for(int i=0;i<nRighe;i++){
            addInVoidPlace(new Oggetto(1,"matita"));
            addInVoidPlace(new OggettoConMalus(1,"forbice",5));
            //questi oggetti hanno un oggetto diverso(lo aggiungo una volta all'inizio
            if(i==0){
                addInVoidPlace(new Oggetto(-1,"gomma rossa"));
                addInVoidPlace(new Oggetto(5,"penna stilografica"));
            }else{
                addInVoidPlace(new Oggetto(2,"gomma"));
                addInVoidPlace(new Oggetto(4,"penna"));
            }
        }
    }

    /**
     * giocatore corrente(da 0 a nGiocaotri-1)
     * @return giocatore corrente
     */
    public int getGiocatoreCorrente(){
        return giocatoreCorrente;
    }

    /**
     * punteggio del giocaore indicato
     * @param giocatore numero del giocatore: 0->nGIocaotri-1
     * @return punteggio del giocaotore
     */
    public int getPunteggio(int giocatore){
        return punteggio[giocatore];
    }

    /**
     * controllo tramite il numero di oggetti mancanti se la partita è finita
     * @return se la partita è finita
     */
    public boolean isEnded(){
        return nOggettiMancanti==0;
    }

    /**
     * metodo privato per aggiungere il una posizione casuale l'oggetto inserito, non controlla se c'è spazio
     * @param in oggetto da inserire
     */
    private void addInVoidPlace(Oggetto in){
        int x,y;
        //continua finchè non trova una cella vuota
        do{
            x=(int)(Math.random()*tabellone[0].length);
            y=(int)(Math.random()* tabellone.length);
        }while(tabellone[y][x]!=null);
        tabellone[y][x]=in;
    }

    /**
     * metodo principale del gioco, il giocatore corrente controlla il contenuto della cella,
     * cambia giocatore solo se la cella era valida e piena
     * @param x colonna x della cella
     * @param y riga della cella
     * @return true se la pescata è andata a buon fine
     */
    public boolean pesca(int x,int y){
        boolean out=false;
        if (isEnded()) {
            messageAfterDraw="partita finita, non ci sono più oggetti da trovare";
        } else {
            //controllo input
            if (x < 0 || x >= tabellone[0].length || y < 0 || y >= tabellone.length) {
                messageAfterDraw="coordinate non valide";
            } else {
                Oggetto pescato = tabellone[y][x];
                //controllo se la cella era piena
                if (pescato == null) {
                    out=true;
                    messageAfterDraw="non hai pescato niente";
                } else {
                    //controllo che la cella non sia già stata pescata
                    if (pescato.getValore() == 0) {
                        messageAfterDraw = "cella già controllata";
                    } else {
                        //aggiungo alla lista delle pescate l'oggetto corrente, modifico tutti i valori da modificare
                        pescate[tabellone.length * 4 - nOggettiMancanti] = pescato.getNome();
                        nOggettiMancanti--;
                        punteggio[giocatoreCorrente] += pescato.getValore();
                        tabellone[y][x] = new Oggetto(0, "");//oggetto vuoto, vuol dire che la cella è già stata controllata
                        //controllo se la cella aveva un malus, se sì lo applico a tutti gli altri giocatori
                        if (pescato instanceof OggettoConMalus) {
                            for (int i = 0; i < punteggio.length; i++)
                                if (i != giocatoreCorrente) punteggio[i] -= ((OggettoConMalus) pescato).getMalus();
                        }
                        //passo al giocatore successivo, se il giro è finito lo reincomincio
                        giocatoreCorrente = (giocatoreCorrente + 1) % punteggio.length;
                        out = true;
                        messageAfterDraw = "pescata: " + pescato.getNome();
                    }
                }
            }
        }
        return out;
    }

    /**
     * ritorna un array con tutte le pescate in ordine, della dimensione giusta.
     * @return
     */
    public String[] getPescate(){
        String[] out=new String[tabellone.length*4-nOggettiMancanti];
        for (int i = 0; i < out.length; i++)
            out[i]=pescate[i];
        return out;
    }

    /**
     * ritorna il messaggio relativo all'ultima pescata
     * @return esito dell'ultima pescata
     */
    public String getMessageAfterDraw(){
        return messageAfterDraw;
    }
    public String toString(){
        String out="Punteggio giocatori: \n";
        for (int i = 0; i < punteggio.length; i++)
            out+= "giocatore "+i+": "+punteggio[i]+"\n";
        out+="giocatore corrente: "+giocatoreCorrente+"\n";
        if(isEnded()){
            out+="partita finita, pescate in ordine: \n";
            for (String pescata:
                 pescate) {
                out+=pescata+"\n";
            }
        }else{
            out+="partita in corso, numero di oggetti da trovare: "+nOggettiMancanti;
        }
        return out;
    }

    /**
     * controlla chi è il vincitore
     * @return numero del giocatore vincitore
     * @throws Exception se la partita non è finita
     */
    public int getVincitore() throws Exception{
        if(!isEnded())
            throw new Exception("partita non finita");
        int max=0;
        for (int i = 1; i < punteggio.length; i++)
            if(punteggio[i]>punteggio[max]) max=1;
        return max;
    }
}
