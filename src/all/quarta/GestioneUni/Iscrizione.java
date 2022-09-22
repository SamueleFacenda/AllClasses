package all.quarta.GestioneUni;

public class Iscrizione implements KeyValueClass{
    private Studente iscritto;
    private Universita scelta;
    private int matricola;
    public Iscrizione(Studente iscritto,Universita uni)throws Exception{
        if(iscritto==null)
            throw new NullPointerException();
        matricola= uni.generateNewMatricola();
        this.iscritto=iscritto;
        scelta=uni;
    }
    public int getMatricola(){
        return matricola;
    }
    public Studente getIscritto(){
        return iscritto;
    }
    public Universita getUniversita(){
        return scelta;
    }

    @Override
    public String getKeyValue() {
        //return scelta.getKeyValue()+iscritto.getKeyValue();
        return iscritto.getKeyValue();
    }
}
