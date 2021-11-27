package all.GestoineUni2;

import all.GestioneUni.KeyValueClass;

public class Universita{
    private String nome;
    private int newMatricola,maxMatricola;//maxMatricola non è incluso

    public Universita(String nome, int newMatricola, int maxMatricola) {
        this.nome = nome;
        this.newMatricola = newMatricola;
        this.maxMatricola = maxMatricola;
    }

    public String getNome(){
        return nome;
    }
    public boolean areMatricoleEndend(){
        return newMatricola==maxMatricola;
    }
    public int generateNewMatricola() throws Exception {
        if(areMatricoleEndend())
            throw new Exception("non ci sono piú matricole disponibili");
        return newMatricola++;
    }
    public int getMaxMatricola(){
        return getMaxMatricola();
    }

    public Universita getCopy(){
        return new Universita(nome,newMatricola,maxMatricola);
    }
}
