package all.prove;

public class Studente {
    private String nome;
    private int ggNascita,mmNascita,aaNascita;

    public Studente(String nome, int ggNascita, int mmNascita, int aaNascita) {
        this.nome = nome;
        this.ggNascita = ggNascita;
        this.mmNascita = mmNascita;
        this.aaNascita = aaNascita;
    }

    @Override
    public String toString(){
        return "nome: "+nome+"\nnascita: "+ggNascita+"/"+mmNascita+"/"+aaNascita;
    }
    public Studente getCopy(){
        return new Studente(nome,ggNascita,mmNascita,aaNascita);
    }
}
