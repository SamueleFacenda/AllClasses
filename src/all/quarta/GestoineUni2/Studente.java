package all.quarta.GestoineUni2;

import all.quarta.persona.Data;

/**
 * classe che rappresenta uno studente con tutti i suoi dati anagrafici,
 * con la matricola, essa é -1 se lo studente non é iscritto a nessuna facolta
 * @author Samuele Facenda
 */
public class Studente{
    private String nome,cognome,codiceFiscale;
    private Data dataDiNascita;
    private int matricola;

    /**
     * costruttore con tutte le variabili d'istanza
     * @param nome nome dello studente
     * @param cognome cognome dello studente
     * @param codiceFiscale codice fiscale dello studente
     * @param dataDiNascita data, istanza della classe data implementata l'anno scorso
     * @throws Exception
     */
    public Studente(String nome, String cognome, String codiceFiscale, Data dataDiNascita) throws Exception {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.dataDiNascita = dataDiNascita.getCopy();
        matricola=-1;
    }

    /**
     * autoesplicativo
     * @return nome dello studente
     */
    public String getNome() {
        return nome;
    }

    /**
     * autoesplicativo
     * @return cognome dello studente
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * autoesplicativo
     * @return codice fiscale dello studente
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * autoesplicativo
     * @return nuova istanza della data di nascita
     */
    public Data getDataDiNascita(){
        //non occorre lanciare eccezioni, la data viene controllata giá all'assegnazione
        try {
            return dataDiNascita.getCopy();
        }catch (Exception e){
            return null;
        }
    }

    /**
     * restituisce una nuova istanza della classe
     * @return oggetto studente uguale
     */
    public Studente getCopy() throws Exception {
        //non richiamerá mai eccezioni essendo giá stata controllata la data
        try{
            return new Studente(nome,cognome,codiceFiscale,dataDiNascita);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * autoesplicativo
     * @return se lo studente é iscritto a qualche facolta
     */
    public boolean isIscritto(){
        return matricola!=-1;
    }

    /**
     * autoesplicativo
     * @param matr nuova matricola
     */
    public void setMatricola(int matr){
        matricola=matr;
    }

    /**
     * autoesplicativo
     * @return la matricola dello studente
     */
    public int getMatricola(){
        return matricola;
    }

    /**
     * annulla l'iscrizione dello studente(resetta la matricola)
     */
    public void unsign(){
        matricola=-1;
    }
    @Override
    public String toString(){
        return "nome: "+nome+" "+codiceFiscale+"\ncodice fiscale: "+codiceFiscale+"\ndata di nascita: "+dataDiNascita.stampa()+
                (matricola==-1?"\nnon iscritto a nessuna facolta":("\nmatricola: "+matricola));
    }
}
