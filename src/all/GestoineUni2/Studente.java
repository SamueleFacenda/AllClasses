package all.GestoineUni2;

import all.Data;
import all.GestioneUni.KeyValueClass;

public class Studente{
    private String nome,cognome,codiceFiscale;
    private Data dataDiNascita;
    private int matricola;

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
    public Studente getCopy() throws Exception {
        return new Studente(nome,cognome,codiceFiscale,dataDiNascita);
    }
    public boolean isIscritto(){
        return matricola!=0;
    }
}
