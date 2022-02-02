package all.GestioneUni;

import all.persona.Data;

public class Studente implements KeyValueClass{
    private String nome,cognome,codiceFiscale;
    private Data dataDiNascita;

    public Studente(String nome, String cognome, String codiceFiscale, Data dataDiNascita) throws Exception {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.dataDiNascita = dataDiNascita.getCopy();
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public Data getDataDiNascita() {
        return dataDiNascita;
    }

    @Override
    public String getKeyValue() {
        return codiceFiscale;
    }
    public Studente getCopy() throws Exception {
        return new Studente(nome,cognome,codiceFiscale,dataDiNascita);
    }
}
