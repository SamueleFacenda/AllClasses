package all.GestoineUni2;

/**
 * classe per la gestione delle iscrizioni degli studenti alle varie facolta di
 * un'universitá.
 * La classe contiene due array, uno di studenti, la cui iscrizione é riconducibile alla matricola, e uno
 * di facolta. Entrambi non ammettono duplicati(per gli studenti il controllo é sul codice fiscale).
 * L'array di studenti viene gestito come un'arraylist, quindi con la dimensione logica e lo shift all'eliminazione,
 * mentre quello di facolta puo' avere buchi, questo perché le matricole sono legate alla posizione nell'array della
 * facolta, shiftare le facolta nell'array vorrebbe dire cambiare tutte le matricole degli studenti iscritti alle facolta
 * spostate.
 * @author Samuele Facenda
 */
public class GestoreIscrizioniUni {
    private Studente[] arrStud;
    private Facolta[] arrFac;
    private int nStud;
    /**
     * numero fisso di studenti per ogni facolta
     */
    private static int STUDPERFAC= (int) 10e6;

    /**
     * costruttore vuoto, i componenti vengono aggiunti mano a mano
     */
    public GestoreIscrizioniUni(){
        arrStud=new Studente[1];
        arrFac =new Facolta[1];
        nStud=0;
    }

    /**
     * aggiunge una facolta alla gestione
     * @param nome nome della facolta da aggiungere
     * @throws Exception se la facolta é giá esistente
     */
    public void addFac(String nome)throws Exception{
        //controllo che non sia giá presente
        if(indexOfFac(nome)!=-1)
            throw new Exception("facolta gia esistente");
        int pos=getNullCellFac();
        //se l'array é pieno lo allungo, la posizione della prima cella vuota sará pari alla vecchia dimensione fisica
        if(pos==-1){
            pos=arrFac.length;
            resizeFac();
        }
        //le matriocole della facolta sono composte dalla posizione della faoclta nell'array seguita da 6 cifre, per lo studente(STUDPERFAC)
        arrFac[pos]=new Facolta(nome,pos*STUDPERFAC,pos*STUDPERFAC+STUDPERFAC);
    }

    /**
     * aggiunge uno studente alla gestione
     * @param in studente da aggiungere
     * @throws Exception se lo studente é giá presente(ricerca per codice fiscale)
     */
    public void addStud(Studente in)throws Exception{
        //controllo che non sia giá esistente
        if(indexOfStud(in.getCodiceFiscale())!=-1)
            throw new Exception("studente gia iscritto");
        //se é pieno lo allungo
        if(nStud==arrStud.length)
            resizeStud();
        arrStud[nStud]=in.getCopy();
        nStud++;
    }

    /**
     * rimosione dello studente dalla gestone
     * @param code codice fiscale dello studente da rimuovere
     * @throws Exception se lo studente non esiste
     */
    public void removeStud(String code)throws Exception {
        int pos=indexOfStud(code);
        //controllo che esista
        if(pos==-1)
            throw new Exception("studente insesistente");
        //shifto gli studenti a partire dalla posizione trovata
        for (int i = pos; i < nStud-1; i++)
            arrStud[i]=arrStud[i+1];
        nStud--;
    }

    /**
     * iscrive uno studente a una facolta
     * @param code codice fiscale dello studente
     * @param facolta faoclta a cui iscrivere lo studente
     * @throws Exception se la facolta non esiste, lo studente non esiste o se é giá iscritto
     */
    public void signInStudent(String code,String facolta)throws Exception{
        int posStud=indexOfStud(code),posFac=indexOfFac(facolta);
        //se la facolta non esiste
        if(posFac==-1)
            throw new Exception("universita inesistente");
        //se lo studente non esiste
        if(posStud==-1)
            throw new Exception("studente inesistente");
        //se lo studente é giá iscritto a qualche facolta
        if(arrStud[posStud].isIscritto())
            throw new Exception("studente gia iscritto");
        //genera una nuova matricola e la assegna allo studente
        arrStud[posStud].setMatricola(arrFac[posFac].generateNewMatricola());
    }

    /**
     * rimuove l'iscrizione dello studente alla facolta in cui é iscritto
     * @param code codice fiscale dello studente
     * @throws Exception se lo studente é inesistente o se non é iscritto a niente
     */
    public void unSignStudente(String code)throws Exception{
        int pos=indexOfStud(code);
        if(pos==-1)
            throw new Exception("studente inesistente");
        if(!arrStud[pos].isIscritto())
            throw new Exception("studente non iscritto a niente");
        arrStud[pos].unsign();
    }

    /**
     * rimuove una facolta dalla gestione
     * @param nome nome della facolta da rimuovere
     * @throws Exception se la facolta é inesistente
     */
    public void removeFac(String nome)throws Exception{
        int pos=indexOfFac(nome);
        if(pos==-1)
            throw new Exception("facolta inesistente");
        //rimosione senza shift
        //rimuove l'iscrizone a tutti gli studenti iscritti alla facolta
        for (int i = 0; i < nStud; i++) {
            if(arrStud[i].getMatricola()/STUDPERFAC==pos)
                arrStud[i].unsign();
        }
    }

    /**
     * cerca la facolta per nome
     * @param nome nome della facolta cercata
     * @return indice della facolá ricercata(-1 se non é presente)
     */
    public int indexOfFac(String nome){
        int i=0;
        //continua finché o la cella e vuota o non é la facoltá ricercata
        while(i< arrFac.length && (arrFac[i]==null || !nome.equals(arrFac[i].getNome())))
            i++;
        return i== arrFac.length ?-1:i;
    }

    /**
     * cerca uno studente per codice fiscale
     * @param code codice fiscale dello studente
     * @return indice di posizione dello studente(-1 se non é presente)
     */
    public int indexOfStud(String code){
        int i=0;
        //uso il costrutto n++ che restituisce un valore e poi lo incrementa
        while(i<nStud && !code.equals(arrStud[i++].getCodiceFiscale()));
        return i==nStud?-1:i;
    }
    private void resizeFac(){
        Facolta[] arr2=new Facolta[(int)Math.ceil(0.2* arrFac.length)];
        for (int i = 0; i < arrFac.length; i++)
            arr2[i]= arrFac[i];
        arrFac =arr2;
    }
    public int getNFac(){
        int out=0;
        for (Facolta current:
             arrFac) {
            if(current!=null)
                out++;
        }
        return out;
    }
    private int getNullCellFac(){
        int i=0;
        while(i<arrFac.length && arrFac[i++]!= null);
        return i==arrFac.length?-1:i;
    }
    private void resizeStud(){
        Studente[] arr2=new Studente[(int) Math.ceil(0.2*arrStud.length)];
        for (int i = 0; i < nStud; i++)
            arr2[i]=arrStud[i];
        arrStud=arr2;
    }
    public String getFacolta(int matricola)throws Exception{
        return searchFacFromMatr(matricola).getNome();
    }
    private Facolta searchFacFromMatr(int matricola)throws Exception{
        int pos=matricola/STUDPERFAC;
        if(pos> arrFac.length || arrFac[pos]==null)
            throw new Exception("matricola invalida");
        return arrFac[pos];
    }
}
