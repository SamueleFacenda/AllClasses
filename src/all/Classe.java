package all;

public class Classe {
    private String aula;
    private String nome;
    private Studente[] studente;

    public Classe(String aula, String nome, Studente[] studente) {
        this.aula = aula;
        this.nome = nome;
        this.studente = studente;
        System.arraycopy(studente,0,this.studente,0,studente.length);
    }

    @Override
    public String toString(){
        String out="";
        for(Studente st:studente) out+=st+"\n";
        return out;
    }
}
