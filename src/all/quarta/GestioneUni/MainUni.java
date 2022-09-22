package all.quarta.GestioneUni;

import all.quarta.persona.Data;

public class MainUni {
    public static void main(String[] args) {
        GestoreIscrizioni gi=new GestoreIscrizioni();
        try{
            gi.addUniversita("trnto",500);
            gi.addStudente(new Studente("io","cogn","FCNSML",new Data(25,9,2004)));
            gi.addStudente(new Studente("tu","cognome","FCNSML",new Data(25,9,2004)));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
