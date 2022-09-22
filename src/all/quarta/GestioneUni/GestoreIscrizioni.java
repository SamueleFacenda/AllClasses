package all.quarta.GestioneUni;

public class GestoreIscrizioni {
    private GestoreKeyValuateClasses<Studente> gestoreStudenti;
    private GestoreKeyValuateClasses<Universita> gestoreUniversita;
    private GestoreKeyValuateClasses<Iscrizione> gestoreIscrizoni;
    public GestoreIscrizioni(){
        gestoreIscrizoni=new GestoreKeyValuateClasses<>();
        gestoreStudenti=new GestoreKeyValuateClasses<>();
        gestoreUniversita=new GestoreKeyValuateClasses<>();
    }
    public void addUniversita(String nome, int numeroMassimoMatricole)throws Exception{
        int startN;
        try{
            startN=gestoreUniversita.getLast().getMaxMatricola();
        }catch (IndexOutOfBoundsException e){
            startN=0;
        }
        gestoreUniversita.add(new Universita(nome,startN,startN+numeroMassimoMatricole));
    }
    public void addStudente(Studente in)throws Exception{
        gestoreStudenti.add(in.getCopy());
    }
    public void nuovaIscrizione(Studente in,Universita uni)throws Exception{
        gestoreIscrizoni.add(new Iscrizione(in,uni));
    }
    public void nuovaIscrizione(String stud,String uni)throws Exception{
        try{
            gestoreIscrizoni.add(new Iscrizione(gestoreStudenti.get(stud), gestoreUniversita.get(uni)));
        }catch(NullPointerException e){
            throw new Exception("studente o universita non trovata");
        }
    }
    public String findUni(int matricola)throws Exception{
        int i=0;
        while(i<gestoreUniversita.getNumberOfElements() && gestoreUniversita.get(i).getMaxMatricola()>=matricola)
            ++i;
        if(i== gestoreUniversita.getNumberOfElements())
            throw new Exception("matricola errata");
        else
            return gestoreUniversita.get(i).getKeyValue();

    }
}
