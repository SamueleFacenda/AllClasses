package all.prove;

import all.prove.Studente;

public class Classe {
    private String aula;
    private String nome;
    private Studente[] studente;

    public Classe(String aula, String nome, Studente[] studente) {
        this.aula = aula;
        this.nome = nome;
        this.studente = studente;
        this.studente=new Studente[studente.length];
        for(int i=0;i<studente.length;i++)
            this.studente[i]=studente[i].getCopy();
    }

    @Override
    public String toString(){
        String out="";
        for(Studente st:studente) out+=st+"\n";
        return out;
    }
}
